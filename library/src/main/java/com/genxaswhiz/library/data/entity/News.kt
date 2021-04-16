package com.genxaswhiz.library.data.entity

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data") val newsDataResponse: List<News>
)

data class News(
    @SerializedName("news_url") val newsUrl: String = "",
    @SerializedName("image_url") val imageUrl: String = "",
    @SerializedName("title") val title: String = "",
    @SerializedName("text") val text: String = "",
    @SerializedName("source_name") val sourceName: String = "",
    @SerializedName("date") val date: String = ""
) {


    companion object {
        fun createNewsList(): List<News> {
            return arrayListOf(
                News(
                    newsUrl = "https://u.today/tetragon-to-pay-ripple-34-million-in-legal-fees-after-failed-lawsuit",
                    imageUrl = "https://crypto.snapi.dev/images/v1/1/z/52392f29fe-19943.jpg",
                    title = "Bitcoin, Ethereum and Altcoins Eye Additional Gains",
                    text = "After losing its lawsuit against Ripple, Tetragon will have to shell out \$3.4 million in legal fees",
                    sourceName = "UToday",
                    date = "Tue, 13 Apr 2021 02:15:00 -0400"
                ),
                News(
                    imageUrl = "https://crypto.snapi.dev/images/v1/x/j/why-coinbase-going-public-is-important-for-investors-and-the-crypto-market-19885.jpg",
                    title = "Why Coinbase going public is important for investors and the crypto market"
                ),
                News(
                    imageUrl = "https://crypto.snapi.dev/images/v1/y/b/what-are-altcoins-and-what-makes-them-different-from-bitcoin-defined-forbes-19911.jpg",
                    title = "What Are Altcoins And What Makes Them Different From Bitcoin? | Defined | Forbes, Bitcoin, Ethereum and Altcoins Eye Additional Gains"
                ),
                News(
                    imageUrl = "https://crypto.snapi.dev/images/v1/b/i/binance-crypto-exchange-1-19828.jpg",
                    title = "Breaking: Binance Launches Zero-Commission Tradable Tesla Stock Token"
                ),
                News(
                    imageUrl = "https://crypto.snapi.dev/images/v1/j/g/what-coinbases-ipo-means-for-the-crypto-market-plus-bitcoin-nears-record-19866.jpg",
                    title = "What Coinbase's IPO means for the crypto market, plus Bitcoin nears record"
                )
            )
        }
    }
}


