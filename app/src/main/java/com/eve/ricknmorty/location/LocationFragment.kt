package com.eve.ricknmorty.location

import android.view.LayoutInflater
import com.eve.ricknmorty.base.BaseFragment
import com.eve.ricknmorty.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<FragmentLocationBinding>() {


    override fun initializationLayout(inflater: LayoutInflater): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(inflater)
    }

    override fun setupView() {

    }

    override fun initialization() {

    }

}