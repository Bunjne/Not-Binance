package com.genxaswhiz.library.widgets.main

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.genxaswhiz.library.R
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.extension.inflate
import com.genxaswhiz.library.extension.showSVG
import kotlinx.android.synthetic.main.view_coin_item.view.*

class CoinItemView : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(R.layout.view_coin_item)
    }

    fun init(coin: Coin,
             onItemClicked: (Coin) -> Unit) {
        with(coin) {
            ivCoinIcon.showSVG(context, iconUrl)
            tvCoinName.text = name
        }

        setOnClickListener {
            onItemClicked(coin)
        }
    }
}