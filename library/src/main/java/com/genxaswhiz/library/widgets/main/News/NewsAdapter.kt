package com.genxaswhiz.library.widgets.main.News

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.databinding.ViewNewsItemBinding

class NewsAdapter(
    private val context: Context,
    private val newsList: MutableList<News>,
    private val onItemClicked: (News) -> Unit) : RecyclerView.Adapter<NewsItemViewHolder>(), Filterable {

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryText = constraint.toString()
                val result = FilterResults()
                if (queryText.isEmpty()) {
                    result.values = newsList
                } else {
                    result.values = newsList.filter { news ->
                        news.title.toLowerCase().contains(queryText)
                    }
                }
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                with(newsList) {
                    clear()
                    addAll(results?.values as List<News>)
                }

                notifyDataSetChanged()
            }
        }
    }
}