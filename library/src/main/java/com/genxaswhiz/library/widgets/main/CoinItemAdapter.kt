package com.genxaswhiz.library.widgets.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.data.entity.Coin

class CoinItemAdapter(private val context: Context,
                      private val coins: List<Coin>,
                      private val onItemClicked: (Coin) -> Unit) : RecyclerView.Adapter<CoinItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CoinItemView(context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = coins.getOrNull(position)
        item?.let {
            (holder.itemView as? CoinItemView)?.init(it, onItemClicked)
        }
    }

    override fun getItemCount() = coins.size
}