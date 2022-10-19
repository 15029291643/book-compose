package com.example.myapplication.model

import com.example.myapplication.util.JsoupUtils
import com.example.myapplication.network.RequestUtils
import com.example.myapplication.util.title

object BookRepository {
    private const val SELECT_URL = "https://www.xbiquge.so/modules/article/search.php?searchkey="

    fun search(title: String) = JsoupUtils.search(RequestUtils.html(SELECT_URL + title.title()))

    fun info(infoUrl: String) = JsoupUtils.info(RequestUtils.html(infoUrl))

    fun chapter(chapterUrl: String) = JsoupUtils.chapter(RequestUtils.html(chapterUrl))
}