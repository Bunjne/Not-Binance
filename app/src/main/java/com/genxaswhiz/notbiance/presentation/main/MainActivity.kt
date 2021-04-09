package com.genxaswhiz.notbiance.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.data.view_model.CoinViewModel
import com.genxaswhiz.library.widgets.main.CoinItemAdapter
import com.genxaswhiz.notbiance.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CoinViewModel by viewModel()
    private val coins = mutableListOf<Coin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        observeView()
        observeData()
        observeError()

        viewModel.getCoins()
    }

    private fun initView() {
        with(rvCoins) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CoinItemAdapter(this@MainActivity, coins) {
                Toast.makeText(this@MainActivity, it.name, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeView() {
        // TODO: Observe loading state
    }

    private fun observeData() {
        viewModel.coins.observe(this, Observer {
            it?.let {
                with(coins) {
                    clear()
                    addAll(it)
                }
                rvCoins.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun observeError() {
        viewModel.coinsErrorResponse.observe(this, Observer {

        })
    }
}