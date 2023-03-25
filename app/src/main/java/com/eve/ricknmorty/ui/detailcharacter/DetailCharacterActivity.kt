package com.eve.ricknmorty.ui.detailcharacter

import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.ricknmorty.base.BaseActivity
import com.eve.ricknmorty.databinding.ActivityDetailCharacterBinding
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity<ActivityDetailCharacterBinding>() {

    private val viewModel: DetailCharacterViewModel by viewModels()
    var id: Int? = 0

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
        val character = intent.getParcelableExtra<Character>("CHARACTER")
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
                        Log.d(TAG, "getCharacter: ${response.data}")
                    }
                }
                is Resource.Error -> {
                    showToast(response.message.toString())
                }
            }
        }
    }

    private fun setupViewCharacter(data: Character?) {

    }

    companion object {
        private val TAG = DetailCharacterActivity::class.java.simpleName
    }

}