package com.eve.ricknmorty.ui.detaillocation

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.eve.data.utils.Constants.EXTRA_DATA
import com.eve.domain.Resource
import com.eve.domain.model.Character
import com.eve.domain.model.Location
import com.eve.ricknmorty.base.BaseActivity
import com.eve.ricknmorty.databinding.ActivityDetailLocationBinding
import com.eve.ricknmorty.ui.character.CharacterAdapter
import com.eve.ricknmorty.ui.detailcharacter.DetailCharacterActivity
import com.eve.ricknmorty.utils.Utils.parcelable
import com.eve.ricknmorty.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailLocationActivity : BaseActivity<ActivityDetailLocationBinding>(),
    CharacterAdapter.Listener {

    private var id: Int? = 0

    private val viewModel: DetailLocationViewModel by viewModels()
    private val characterAdapter: CharacterAdapter by lazy { CharacterAdapter(this) }

    override fun initializationLayout(inflater: LayoutInflater): ActivityDetailLocationBinding {
        return ActivityDetailLocationBinding.inflate(inflater)
    }

    override fun setupView() {
        setupRecyclerView()
    }

    override fun initialization() {
        getData()
        getLocation()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            delay(1500)
            viewModel.getResidents()
        }
    }

    override fun onClickListener(data: Character) {
        val intent = Intent(this, DetailCharacterActivity::class.java)
        intent.putExtra(EXTRA_DATA, data)
        startActivity(intent)
    }

    private fun getData() {
        val location = intent.parcelable<Location>(EXTRA_DATA)
        id = location?.id
        Log.d(TAG, "getData: $id")
    }

    private fun getLocation() {
        viewModel.getLocation(id).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "getLocation: Loading...")
                }
                is Resource.Success -> {
                    if (response.data != null) {
                        setupViewLocation(response.data)
                        viewModel.setResidents(response.data)
                        Log.d(TAG, "getLocation: SUKSES ${response.data}")
                    }
                }
                is Resource.Error -> {
                    showToast(response.data.toString())
                    Log.d(TAG, "getLocation: ${response.message.toString()}")
                }
            }
        }

        viewModel.residentsList.observe(this) { itemList ->
            characterAdapter.differ.submitList(itemList)
        }

    }

    private fun setupViewLocation(data: Location?) {
        binding.detailLocationLayout.apply {
            txtName.text = data?.name
            txtType.text = data?.type
            txtDimension.text = data?.dimension
            txtTitle.text = "Residents"
        }
    }

    private fun setupRecyclerView() {
        binding.detailLocationLayout.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }

    companion object {
        val TAG = DetailLocationActivity::class.java.simpleName
    }

}