package com.eve.ricknmorty.ui.detailcharacter

import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.ricknmorty.base.BaseActivity
import com.eve.ricknmorty.databinding.ActivityDetailCharacterBinding
import com.eve.ricknmorty.utils.Utils.parcelable
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity<ActivityDetailCharacterBinding>() {

    private val viewModel: DetailCharacterViewModel by viewModels()
    private var id: Int? = 0

    override fun initializationLayout(inflater: LayoutInflater): ActivityDetailCharacterBinding {
        return ActivityDetailCharacterBinding.inflate(inflater)
    }

    override fun setupView() {

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
            showToast(itemList.size.toString() + " Episode")
            // set recycler episode item
        }
    }

    private fun setupViewCharacter(data: Character?) {

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            viewModel.getEpisode()
        }
    }

    companion object {
        private val TAG = DetailCharacterActivity::class.java.simpleName
    }
}