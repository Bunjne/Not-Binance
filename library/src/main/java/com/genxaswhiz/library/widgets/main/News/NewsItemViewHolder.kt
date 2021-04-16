package com.genxaswhiz.library.widgets.main.News

import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.databinding.ViewNewsItemBinding
import com.genxaswhiz.library.extension.show

class NewsItemViewHolder(private val binding: ViewNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun init(news: News, onItemClicked: (News) -> Unit) {
        with(news) {
            binding.ivIconNews.show(imageUrl)
            binding.tvTitle.text = title
        }

        binding.root.setOnClickListener {
            onItemClicked.invoke(news)
        }
    }
}