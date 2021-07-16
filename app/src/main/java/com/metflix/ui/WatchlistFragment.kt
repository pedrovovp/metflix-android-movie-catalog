package com.metflix.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.metflix.R
import com.metflix.adapter.WatchlistAdapter
import com.metflix.common.BindingFragment
import com.metflix.databinding.FragmentWatchlistBinding
import com.metflix.presentation.ViewState
import com.metflix.presentation.WatchlistViewModel
import org.koin.android.ext.android.inject

class WatchlistFragment : BindingFragment<FragmentWatchlistBinding>() {
    override fun getLayoutResId() = R.layout.fragment_watchlist

    private val viewModel: WatchlistViewModel by inject()
    lateinit var adapter: WatchlistAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)

        setRecyclerView()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setRecyclerView() {
        adapter = WatchlistAdapter(viewModel)
        binding.recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner, {
            when (it.status) {
                ViewState.Status.LOADING -> binding.progressBar.visibility = VISIBLE
                ViewState.Status.SUCCESS -> {
                    binding.progressBar.visibility = GONE
                    adapter.submitList(it.data)
                    adapter.notifyDataSetChanged()
                }
                ViewState.Status.ERROR -> {
                    binding.progressBar.visibility = GONE
                    binding.retryButton.visibility = VISIBLE
                    Toast.makeText(context, it.error?.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}