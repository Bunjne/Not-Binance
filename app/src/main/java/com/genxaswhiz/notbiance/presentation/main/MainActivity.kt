package com.genxaswhiz.notbiance.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import com.genxaswhiz.library.data.entity.News
import com.genxaswhiz.library.data.view_model.CoinViewModel
import com.genxaswhiz.library.data.view_model.NewsViewModel
import com.genxaswhiz.library.extension.toJson
import com.genxaswhiz.notbiance.R
import com.genxaswhiz.notbiance.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val coinViewModel: CoinViewModel by viewModel()
    private val newsViewModel: NewsViewModel by viewModel()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val news = mutableListOf<News>()
    private var getCoinJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        initView()
        observeView()
        observeData()
        observeError()

        getCoins()
        newsViewModel.getNews(SECTION_TYPE, NUM_PAGES)
    }

    private fun initView() {
        with(binding) {
            lifecycleScope.launch {
                vCoin.init({ coin ->
                    startActivity<CoinDescriptionActivity>("id" to coin.id)
                    Toast.makeText(this@MainActivity, coin.name, Toast.LENGTH_SHORT).show()
                }, {
                    binding.vCoin.upDateView()
                })
            }

            vNews.init(news) {
                startActivity<NewsDescriptionActivity>("news" to it.toJson())
            }

            srlMain.setOnRefreshListener {
                newsViewModel.getNews(SECTION_TYPE, NUM_PAGES)
                getCoins()
            }
        }
    }

    private fun observeView() {
        coinViewModel.state.coinLoading.observe(this, Observer { isLoading ->
            srlMain.isRefreshing = isLoading
        })

        newsViewModel.state.newsLoading.observe(this, Observer { isLoading ->
            srlMain.isRefreshing = isLoading
        })
    }

    private fun observeData() {
        coinViewModel.coinResponse.observe(this, Observer {
            binding.vCoin.coinAdapter!!.submitData(lifecycle, it)
        })

        newsViewModel.newsResponse.observe(this, Observer {
            it?.let { news ->
                binding.vNews.renderData(news)
            }
        })
    }

    private fun observeError() {
        newsViewModel.newsErrorResponse.observe(this, Observer {
        })
    }

    private fun getCoins(sortType: String = "desc", search: String = "") {
        getCoinJob?.cancel()
        getCoinJob = lifecycleScope.launch {
            coinViewModel.getCoins(sortType, search)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_seach_item, menu)
        val searchItem = menu.findItem(R.id.search_view)

        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                getCoins("desc", newText.trim())
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private const val NUM_PAGES = 10
        private const val SECTION_TYPE = "general"
    }
}