package com.eve.ricknmorty.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.eve.ricknmorty.utils.gone
import com.eve.ricknmorty.utils.show
import com.facebook.shimmer.ShimmerFrameLayout

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseView {

    protected lateinit var binding: VB
    protected abstract fun initializationLayout(inflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return getInflatedLayout(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initialization()
    }

    abstract fun setupView()

    abstract fun initialization()

    private fun getInflatedLayout(layoutInflater: LayoutInflater): View {
        binding = initializationLayout(layoutInflater)
        return binding.root
    }

    override fun showShimmer(shimmer: ShimmerFrameLayout) {
        shimmer.show()
        shimmer.startShimmer()
    }

    override fun dismissShimmer(shimmer: ShimmerFrameLayout) {
        shimmer.stopShimmer()
        shimmer.gone()
    }

}