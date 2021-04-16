package com.genxaswhiz.notbiance.presentation.main

import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.view_model.CoinDescriptionViewModel
import com.genxaswhiz.library.extension.makeTextLinkable
import com.genxaswhiz.library.extension.showSVG
import com.genxaswhiz.notbiance.R
import com.genxaswhiz.notbiance.databinding.ActivityCoinDescriptionBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_coin_description.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text


class CoinDescriptionActivity : AppCompatActivity() {

    private val viewModel: CoinDescriptionViewModel by viewModel()
    private val binding: ActivityCoinDescriptionBinding by lazy {
        ActivityCoinDescriptionBinding.inflate(layoutInflater)
    }
    private val loadingDialog by lazy {
        indeterminateProgressDialog("Please wait...")
    }
    private var coinId = ""
    private var red = 0
    private var green = 0
    private lateinit var memu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.tlCoin)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        red = ContextCompat.getColor(this, R.color.red)
        green = ContextCompat.getColor(this, R.color.green)
        coinId = intent.getStringExtra("id").toString()

        initView()
        observeView()
        observeData()
        observeError()

        viewModel.getCoinDescription(coinId)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            this.memu = it
            menuInflater.inflate(R.menu.menu_coin_description_scrolling, it)

        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.action_info) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        binding.tvCoinNameToolbar.isVisible = false
        binding.tvPriceToolbar.isVisible = false

        binding.abCoin.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                with(binding) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.totalScrollRange
                    }
                    if (scrollRange + verticalOffset == 0) {
                        isShow = true
                        showTitle(tvCoinNameToolbar, tvPriceToolbar)
                    } else if (isShow) {
                        isShow = false
                        hideTitle(tvCoinNameToolbar, tvPriceToolbar)
                    }
                }
            }
        })
    }

    private fun observeView() {
        viewModel.state.coinLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()

            }
        })
    }

    private fun observeData() {
        viewModel.coin.observe(this, Observer {
            it?.let { coin ->
                println(coin)
                with(binding) {
                    ivCoinIcon.showSVG(this@CoinDescriptionActivity, coin.iconUrl)
                    binding.tvCoinNameToolbar.text = coin.symbol
                    binding.tvPriceToolbar.text = "US${coin.price}"
                    tvDescription.makeTextLinkable(coin.description)
                    tvCoinName.text = coin.fullCoinName
                    tvPrice.text = "US$${coin.price}"
                    tvPercentChange.text = coin.change
                    vDescription1.init("Market Cap", "24h volume", coin.marketCap, coin.volume)
                    vDescription2.init(
                        "Circulating",
                        "Total Supply",
                        coin.circulating,
                        coin.totalSupply
                    )
                    vDescription3.init("Ranking", "", coin.rank, "")

                    if (isPositive(coin._change.toDouble())) {
                        tvPercentChange.setTextColor(green)
                    } else {
                        tvPercentChange.setTextColor(red)
                    }
                }
            }
        })
    }

    private fun observeError() {

    }

    private fun hideTitle(tvName: TextView, tvPrice: TextView) {
        tvName.isVisible = false
        tvPrice.isVisible = false
    }

    private fun showTitle(tvName: TextView, tvPrice: TextView) {
        tvName.isVisible = true
        tvPrice.isVisible = true
    }

    private fun isPositive(number: Double): Boolean {
        return (number >= 0.0)
    }
}