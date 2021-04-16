package com.genxaswhiz.library.widgets.main.coin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.databinding.ViewCoinLoadStateFooterBinding

class CoinLoadStateAdapter(
    private val retry: () -> Unit) : LoadStateAdapter<CoinLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ViewCoinLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ViewCoinLoadStateFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bindState(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                btRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}