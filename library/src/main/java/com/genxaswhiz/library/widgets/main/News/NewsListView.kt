package com.genxaswhiz.library.widgets.main.News

import ZoomOutPageTransformer
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.genxaswhiz.library.R
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.data.remote.impl.NewsApiImpl
import com.genxaswhiz.library.databinding.ViewNewsListBinding
import com.genxaswhiz.library.extension.inflate
import com.google.android.material.tabs.TabLayoutMediator

class NewsListView : ConstraintLayout {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)

    private var binding : ViewNewsListBinding =
        ViewNewsListBinding.inflate(LayoutInflater.from(context), this, true)

    private val localNewsList = mutableListOf<News>()

    fun init(newsList: List<News>, onItemClicked: (News) -> Unit) {
        with(binding) {
            with(vpNews) {
                adapter = NewsAdapter(
                    context = context,
                    newsList = localNewsList,
                    onItemClicked = onItemClicked)

                setPageTransformer(ZoomOutPageTransformer())
            }

            tlIndicator.setViewPager2(vpNews)
        }

        this.renderData(newsList)
    }

    fun renderData(packages: List<News>) {
        val transformedItems = transformData(packages)
        with(localNewsList) {
            clear()
            addAll(transformedItems)
        }

        binding.vpNews.adapter?.notifyDataSetChanged()
    }

    private fun transformData(packages: List<News>): List<News> {
        val transformedItems = mutableListOf<News>()
        packages.forEach { item ->
            with(transformedItems) {
                add(item)
            }
        }

        return transformedItems
    }
}