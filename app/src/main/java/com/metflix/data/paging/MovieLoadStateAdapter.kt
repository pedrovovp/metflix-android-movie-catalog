package com.metflix.data.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.metflix.R
import com.metflix.databinding.LoadingStatePagingFooterBinding

class MovieLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding?.let {
            it.retryButton.setOnClickListener {
                retry.invoke()
            }

            it.progressBar.isVisible = loadState is LoadState.Loading
            it.retryButton.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_state_paging_footer, parent, false)
        return LoadStateViewHolder(view)
    }

    inner class LoadStateViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<LoadingStatePagingFooterBinding>(view)
    }
}
