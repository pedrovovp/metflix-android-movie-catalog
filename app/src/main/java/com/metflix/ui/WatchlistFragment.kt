package com.metflix.ui

import android.os.Bundle
import android.view.View
import com.metflix.R
import com.metflix.common.BindingFragment
import com.metflix.databinding.FragmentWatchlistBinding

class WatchlistFragment : BindingFragment<FragmentWatchlistBinding>() {
    override fun getLayoutResId() = R.layout.fragment_watchlist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}