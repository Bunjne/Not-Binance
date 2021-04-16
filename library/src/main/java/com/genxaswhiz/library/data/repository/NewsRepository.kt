package com.genxaswhiz.library.data.repository

import androidx.lifecycle.LiveData
import com.genxaswhiz.library.data.entity.DataWrapper
import com.genxaswhiz.library.data.entity.News

interface NewsRepository {
    fun getNews(section: String, itemNumber: Int): LiveData<DataWrapper<List<News>>>
}