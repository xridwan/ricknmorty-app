package com.eve.ricknmorty.ui.detailcharacter

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.ricknmorty.base.BaseActivity
import com.eve.ricknmorty.databinding.ActivityDetailCharacterBinding
import com.eve.ricknmorty.ui.detailepisode.DetailEpisodeActivity
import com.eve.ricknmorty.ui.episode.EpisodeAdapter
import com.eve.ricknmorty.utils.Utils.parcelable
import com.eve.ricknmorty.utils.show
import com.eve.ricknmorty.utils.showToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity<ActivityDetailCharacterBinding>(),
    EpisodeAdapter.Listener {

    private val viewModel: DetailCharacterViewModel by viewModels()
    private val episodeAdapter: EpisodeAdapter by lazy { EpisodeAdapter(this) }

    private var id: Int? = 0

    override fun initializationLayout(inflater: LayoutInflater): ActivityDetailCharacterBinding {
        return ActivityDetailCharacterBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerview()
    }

    override fun initialization() {
        getData()
        getCharacter()
    }

    private fun getData() {
        val character = intent.parcelable<Character>(EXTRA_DATA)
        id = character?.id
        Log.d(TAG, "initialization: $character")
    }

    private fun getCharacter() {
        Log.d(TAG, "getCharacter: $id")
        viewModel.getCharacter(id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "getCharacter: Loading ...")
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        setupViewCharacter(response.data)
                        viewModel.setCharacter(response.data)
                        Log.d(TAG, "getCharacter: ${response.data}")
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }

        viewModel.episodeList.observe(this) { itemList ->
            Log.d(TAG, "epsd: $itemList")
            binding.rvEpisode.show()
            episodeAdapter.differ.submitList(itemList)
            showToast(itemList.size.toString() + " Episode")
            // set recycler episode item
        }
    }

    private fun setupViewCharacter(data: Character?) {
        Picasso.get().load(data?.image).into(binding.imgProfile)
        binding.apply {
            txtName.text = data?.name
            txtStatus.text = data?.status
            txtSpecies.text = data?.species
            when (data?.status) {
                "Alive" -> {
                    status.setCardBackgroundColor(Color.GREEN)
                }
                "unknown" -> {
                    status.setCardBackgroundColor(Color.GRAY)
                }
                "Dead" -> {
                    status.setCardBackgroundColor(Color.RED)
                }
            }
        }
    }

    private fun setupRecyclerview() {
        binding.rvEpisode.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
            mLayoutManager.stackFromEnd = true
            layoutManager = mLayoutManager
            adapter = episodeAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            viewModel.getEpisode()
        }
    }

    override fun onClickListener(data: Episode) {
        val intent = Intent(this, DetailEpisodeActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    companion object {
        private val TAG = DetailCharacterActivity::class.java.simpleName
    }


}