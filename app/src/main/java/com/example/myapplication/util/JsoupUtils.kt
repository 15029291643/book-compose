package com.example.myapplication.util

import android.util.Log
import com.example.myapplication.bean.Book
import com.example.myapplication.bean.Chapter
import com.example.myapplication.network.RequestUtils
import org.jsoup.Jsoup

object JsoupUtils {
    private const val TAG = "JsoupUtils"
    fun search(html: String) = mutableListOf<Book>().apply {
        Jsoup.parse(html).select("#main > div.novelslistss > li").forEach { element ->
            Book().apply {
                type = element.selectFirst("span.s1")!!.text()
                infoUrl = element.selectFirst("span.s2 > a")!!.attr("href")
                title = element.selectFirst("span.s2 > a")!!.text()
                newestChapterHref = element.selectFirst("span.s3 > a")!!.attr("href")
                newestChapterTitle = element.selectFirst("span.s4")!!.text()
                author = element.selectFirst("span.s4")!!.text()
                updateTime = element.selectFirst("span.s5")!!.text()
            }.also {
                add(it)
            }
        }
    }

    fun info(html: String) = Book().apply {
        Jsoup.parse(html).also { it ->
            infoUrl = it.selectFirst("head > link:nth-child(5)")!!.attr("href")
            imgUrl = it.selectFirst("#fmimg > img")!!.attr("src")
            title = it.selectFirst("#fmimg > img")!!.attr("alt")
            authorHref = it.selectFirst("#info > p:nth-child(2) > a")!!.attr("href")
            author = it.selectFirst("#info > p:nth-child(2) > a")!!.text()
            val text =
                it.selectFirst("#info > p:nth-child(4)")!!.text().replace("[", "").replace("]", "")
                    .split(" ")
            updateTime = text[0] + ", " + text[1]
            words = text[2]
            newestChapterHref =
                infoUrl + it.selectFirst("#info > p:nth-child(5) > a")!!.attr("href")
            newestChapterTitle = it.selectFirst("#info > p:nth-child(5) > a")!!.attr("title")
            intro = it.selectFirst("#intro")!!.text()
            recommendList = mutableListOf<Book>().apply {
                it.select("#maininfo > div.tjlist > a").forEach { element ->
                    Book().apply {
                        infoUrl = element.attr("href")
                        title = element.text()
                    }.also {
                        add(it)
                    }
                }
            }
            chapterList = mutableListOf<Chapter>().apply {
                it.select("#list > dl > dd").forEach { it ->
                    Chapter().apply {
                        href = infoUrl + it.select("a").attr("href")
                        title = it.select("a").text()
                    }.also {
                        add(it)
                    }
                }
            }
        }
    }

    fun chapter(html: String) =
        Chapter().apply {
            Jsoup.parse(html).also { it ->
                title = it.selectFirst("#box_con > div.bookname > h1")!!.text()
                content = mutableListOf<String>().apply {
                    it.selectFirst("#content")!!.text().split(" ").forEach {
                        add(it)
                    }
                }
            }
        }

    fun test() {
        chapter(RequestUtils.html("https://www.xbiquge.so/book/5372/3433991.html")).also { it ->
            it.content.forEach {
                Log.e(TAG, "test: $it")
            }
        }
    }
}