package com.eve.ricknmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eve.domain.Resource
import com.eve.ricknmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacter()
    }

    private fun getCharacter() {
        viewModel.allCharacter.observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.tvTest.text = getString(R.string.loading_text)
                }
                is Resource.Success -> {
                    val data = response.data?.size
                    binding.tvTest.text = data.toString()
                }
                is Resource.Error -> {
                    binding.tvTest.text = response.message.toString()
                }
            }
        }
    }
}