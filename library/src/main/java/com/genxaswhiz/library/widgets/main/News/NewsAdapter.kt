package com.genxaswhiz.library.widgets.main.News

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.databinding.ViewNewsItemBinding

class NewsAdapter(
    private val context: Context,
    private val newsList: List<News>,
    private val onItemClicked: (News) -> Unit) : RecyclerView.Adapter<NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ViewNewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = newsList.getOrNull(position)
        item?.let {
            holder.init(item, onItemClicked)
        }
    }

    override fun getItemCount() = newsList.size
}