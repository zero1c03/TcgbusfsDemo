package com.weber.tcgbusfsdemo.data

import com.google.gson.annotations.SerializedName


data class Tcgbus(
    val updateTime: String,
    @SerializedName("News")
    val news: MutableList<NewsDetail>
)

data class NewsDetail(
    val chtmessage: String,
    val engmessage: String,
    val starttime: String,
    val endtime: String,
    val updatetime: String,
    val content: String,
    val url: String
)