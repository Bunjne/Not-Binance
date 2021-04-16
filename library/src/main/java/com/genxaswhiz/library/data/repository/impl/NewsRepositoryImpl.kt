package com.genxaswhiz.library.data.repository.impl

import androidx.lifecycle.LiveData
import com.genxaswhiz.library.data.entity.DATASOURCE
import com.genxaswhiz.library.data.entity.DataWrapper
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.data.entity.NewsResponse
import com.genxaswhiz.library.data.remote.impl.NewsApiImpl
import com.genxaswhiz.library.data.repository.NewsRepository
import com.genxaswhiz.library.extension.toObject
import java.util.*

class NewsRepositoryImpl(private val remoteSource: NewsApiImpl) : NewsRepository {
    override fun getNews(section: String, itemNumber: Int): LiveData<DataWrapper<List<News>>> {
        return object : LiveData<DataWrapper<List<News>>>() {
            init {
                remoteSource.getNews(section, itemNumber).subscribe({ apiResponse ->
                    val newsResponse = apiResponse.data.toObject<NewsResponse>()
                    newsResponse?.let {
                        val coins = newsResponse.newsDataResponse
                        value = DataWrapper(
                            data = coins,
                            error = apiResponse.error,
                            latestDateTime = Date(),
                            isNetworkPreferred = true,
                            dataSource = DATASOURCE.NETWORK
                        )
                    }
                }, { })
            }
        }
    }
}