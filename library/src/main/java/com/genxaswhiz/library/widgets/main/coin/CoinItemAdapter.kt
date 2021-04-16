package com.genxaswhiz.library.widgets.main.coin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.databinding.ViewCoinItemBinding

class CoinItemAdapter(
    private val context: Context,
    private val onItemClicked: (Coin) -> Unit,
    private val onItemValueClicked: () -> Unit) : PagingDataAdapter<Coin, CoinItemViewHolder>(DataDifferntiator) {

    val viewObserver = MutableLiveData<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinItemViewHolder {
        val binding = ViewCoinItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinItemViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: CoinItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.init(item, onItemClicked, onItemValueClicked)

            viewObserver.observeForever {
                holder.updateView(item)
            }
        }
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Coin>() {

        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }
}