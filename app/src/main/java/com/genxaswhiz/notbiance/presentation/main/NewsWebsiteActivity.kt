package com.genxaswhiz.notbiance.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.genxaswhiz.notbiance.databinding.ActivityNewsWebsiteBinding

class NewsWebsiteActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNewsWebsiteBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")

        url?.let{
            binding.wvNews.loadUrl(it)
        }
    }
}