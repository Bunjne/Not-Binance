package com.genxaswhiz.library.widgets.main.coin

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.R
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.databinding.ViewCoinItemBinding
import com.genxaswhiz.library.extension.showSVG
import io.reactivex.disposables.Disposable
import java.util.*

class CoinItemViewHolder(
    private val context: Context,
    private val binding: ViewCoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val red by lazy {
        ContextCompat.getColor(context, R.color.red)
    }

    private val green by lazy {
        ContextCompat.getColor(context, R.color.green)
    }

    private var currentValueType = VOLUME_TYPE
//    private val searchObservable = Observer<String>()

    fun init(
        coin: Coin,
        onItemClicked: (Coin) -> Unit,
        onItemValueClicked: () -> Unit
    ) {
        binding.apply {
            with(coin) {
                ivCoinIcon.showSVG(context, iconUrl)
                tvCoinName.text = name
                tvSymbol.text = symbol
                tvPrice.text = price
                tvVolume.text = "Vol $volume"
                tvPercentChange.text = change
                tvRank.text = rank

                if (!_change.isNullOrEmpty()) {
                    if (isPositive(_change.toDouble())) {
                        tvPercentChange.setTextColor(green)
                    } else {
                        tvPercentChange.setTextColor(red)
                    }
                }

                itemView.setOnClickListener {
                    onItemClicked(coin)
                }

                clPrice.setOnClickListener {
                    onItemValueClicked()
                }
            }
        }
    }

    fun updateView(coin: Coin) {
        binding.apply {
            with(coin) {
                when (currentValueType) {
                    VOLUME_TYPE -> {
                        tvVolume.text = "MCap $marketCap"
                        currentValueType = MCAP_TYPE
                    }
                    MCAP_TYPE -> {
                        tvVolume.text = "Vol $volume"
                        currentValueType = VOLUME_TYPE
                    }
                }
            }
        }
    }

    private fun isPositive(number: Double): Boolean {
        return (number >= 0.0)
    }

    companion object {
        private const val VOLUME_TYPE = "V"
        private const val SUPPLY_TYPE = "S"
        private const val MCAP_TYPE = "M"
    }
}