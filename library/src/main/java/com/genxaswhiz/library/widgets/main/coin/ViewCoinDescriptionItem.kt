package com.genxaswhiz.library.widgets.main.coin

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.genxaswhiz.library.databinding.ViewCoinDescriptionItemBinding
import kotlinx.android.synthetic.main.view_coin_description_item.view.*

class ViewCoinDescriptionItem : ConstraintLayout {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)

    private val binding : ViewCoinDescriptionItemBinding by lazy {
        ViewCoinDescriptionItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun init(titleName1: String, titleName2:String, value1:String, value2:String) {
        with(binding) {
            tvTitle1.text = titleName1
            tvValue1.text = value1
            tvTitle2.text = titleName2
            tvValue2.text = value2
        }

    }
}