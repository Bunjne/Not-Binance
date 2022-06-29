package com.genxaswhiz.library.widgets.main.coin

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genxaswhiz.library.R
import com.genxaswhiz.library.data.entity.Coin
import com.genxaswhiz.library.databinding.ViewCoinListBinding
import com.genxaswhiz.library.extension.inflate
import com.genxaswhiz.library.utility.CustomDividerItemDecoration
import kotlinx.coroutines.flow.collectLatest

class CoinListView : ConstraintLayout {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)

    private var binding: ViewCoinListBinding =
        ViewCoinListBinding.inflate(LayoutInflater.from(context), this, true)

    var coinAdapter: CoinItemAdapter? = null

    suspend fun init(onCoinItemClicked: (Coin) -> Unit, onItemValueClicked: () -> Unit) {

        coinAdapter = CoinItemAdapter(context, onCoinItemClicked, onItemValueClicked)

        with(binding.rvCoin) {
            if (itemDecorationCount == 0) {
                addItemDecoration(
                    CustomDividerItemDecoration(
                        divider = ContextCompat.getDrawable(
                            context,
                            R.drawable.divider_item_vertical
                        )!!,
                        dividerViewType = 0
                    )
                )
            }

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = coinAdapter!!.withLoadStateHeaderAndFooter(
                header = CoinLoadStateAdapter { coinAdapter!!.retry() },
                footer = CoinLoadStateAdapter { coinAdapter!!.retry() }
            )
        }

        coinAdapter!!.loadStateFlow.collectLatest {
            binding.apply {
                if (it.refresh is LoadState.NotLoading) {
                    ivRecordState.isVisible = coinAdapter!!.itemCount < 1
                    tvRecordState.isVisible = coinAdapter!!.itemCount < 1
                }
            }
        }
    }

    fun upDateView() {
        coinAdapter!!.viewObserver.postValue(true)
    }

//    fun renderData(packages: List<Coin>) {
//        val transformedItems = transformData(packages)
//        with(coinsLocal) {
//            clear()
//            addAll(transformedItems)
//        }
//
//        with(coinsFull) {
//            if (coinsFull.isNullOrEmpty()) {
//                clear()
//                addAll(transformedItems)
//            }
//        }
//
//        binding.rvCoin.adapter?.notifyDataSetChanged()
//    }
//
//    private fun transformData(packages: List<Coin>): List<Coin> {
//        val transformedItems = mutableListOf<Coin>()
//        packages.forEach { item ->
//            with(transformedItems) {
//                add(item)
//            }
//        }
//
//        return transformedItems
//    }

//    fun getFilter(): android.widget.Filter {
//        return object : android.widget.Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val filteredList: MutableList<Coin> = ArrayList()
//                if (constraint == null || constraint.isEmpty()) {
//                    filteredList.addAll(coinsFull)
//                } else {
//                    val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
//                    for (item in coinsFull) {
//                        if ((item.name + item.symbol).toLowerCase().contains(filterPattern)) {
//                            filteredList.add(item)
//                        }
//                    }
//                }
//                val results = FilterResults()
//                results.values = filteredList
//                return results
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
//                renderData(results.values as List<Coin>)
//            }
//        }
//    }
}