package com.genxaswhiz.notbiance.presentation.main

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.startActivity
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.extension.showSVG
import com.genxaswhiz.library.extension.toDate
import com.genxaswhiz.library.extension.toObject
import com.genxaswhiz.library.extension.toTransactionDateFormat
import com.genxaswhiz.notbiance.databinding.ActivityNewsDescriptionBinding

class NewsDescriptionActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsDescriptionBinding.inflate(layoutInflater)
    }

    private var news = News()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.tlNews)

        val newsJson = intent.getStringExtra("news").toString()
        news = newsJson.toObject<News>()!!

        setToolbar()
        initView()

    }

    private fun initView() {
        binding.apply {
            with(news) {
                tvTitle.text = title
                tvSourceName.text = sourceName
                tvDate.text = date.toDate().toTransactionDateFormat()
                tvDescription.text = text
                ivNews.showSVG(this@NewsDescriptionActivity, imageUrl)

                tvNewsUrl.apply {
                    text = newsUrl
                    paintFlags = Paint.UNDERLINE_TEXT_FLAG
                    setTextColor(Color.BLUE)

                    setOnClickListener {
                        startActivity<NewsWebsiteActivity>("url" to newsUrl)
                    }
                }

            }
        }
    }

    private fun setToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.tlNews.setNavigationOnClickListener { onBackPressed() }
    }
}