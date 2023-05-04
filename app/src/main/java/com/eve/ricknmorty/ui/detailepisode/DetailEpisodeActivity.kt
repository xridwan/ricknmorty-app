package com.eve.ricknmorty.ui.detailepisode

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Episode
import com.eve.ricknmorty.base.BaseActivity
import com.eve.ricknmorty.databinding.ActivityDetailEpisodeBinding
import com.eve.ricknmorty.ui.character.CharacterAdapter
import com.eve.ricknmorty.ui.detailcharacter.DetailCharacterActivity
import com.eve.ricknmorty.utils.Utils.parcelable
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailEpisodeActivity : BaseActivity<ActivityDetailEpisodeBinding>(),
    CharacterAdapter.Listener {

    private var id: Int? = 0

    private val viewModel: DetailEpisodeViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter(this) }

    override fun initializationLayout(inflater: LayoutInflater): ActivityDetailEpisodeBinding {
        return ActivityDetailEpisodeBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun initialization() {
        getData()
        getEpisode()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            viewModel.getCharacter()
        }
    }

    private fun getData() {
        val episode = intent.parcelable<Episode>(EXTRA_DATA)
        id = episode?.id
        Log.d(TAG, "getData: $episode")
    }

    private fun getEpisode() {
        viewModel.getEpisode(id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "LOADING: Loading....")
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        setupViewEpisode(response.data)
                        viewModel.setEpisode(response.data)
                        Log.d(TAG, "SUCCESS: ${response.data}")
                    }
                }
                is Resource.Error -> {
                    showToast(response.data.toString())
                    Log.d(TAG, "ERROR: ${response.message.toString()}")
                }
            }
        }

        viewModel.characterList.observe(this) { itemList ->
            characterAdapter.differ.submitList(itemList)
            Log.d(TAG, "getCharacters: $itemList")
        }

    }

    private fun setupViewEpisode(data: Episode?) {
        binding.detailEpisodeLayout.apply {
            txtName.text = data?.name
            txtType.text = data?.air_date
            txtDimension.text = data?.episode
            txtTitle.text = "Character"
        }
    }

    private fun setupRecyclerView() {
        binding.detailEpisodeLayout.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }

    override fun onClickListener(data: Character) {
        val intent = Intent(this, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    companion object {
        val TAG = DetailEpisodeActivity::class.java.simpleName
    }

}