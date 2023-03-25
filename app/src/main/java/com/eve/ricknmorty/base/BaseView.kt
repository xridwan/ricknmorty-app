package com.eve.ricknmorty.base

import com.facebook.shimmer.ShimmerFrameLayout

interface BaseView {
    fun showShimmer(shimmer: ShimmerFrameLayout)
    fun dismissShimmer(shimmer: ShimmerFrameLayout)
}