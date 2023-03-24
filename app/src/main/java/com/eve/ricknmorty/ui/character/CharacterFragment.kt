package com.eve.ricknmorty.ui.character

import android.view.LayoutInflater
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    override fun initializationLayout(inflater: LayoutInflater): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(inflater)
    }

    override fun setupView() {

    }

    override fun initialization() {

    }

}