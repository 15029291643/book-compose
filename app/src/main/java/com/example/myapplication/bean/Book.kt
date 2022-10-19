package com.example.myapplication.bean

data class Book(
    var infoUrl: String = "",
    var type: String = "",
    var title: String = "",
    var newestChapterHref: String = "",
    var newestChapterTitle: String = "",
    var author: String = "",
    var updateTime: String = "",
    var imgUrl: String = "",
    var intro: String = "",
    var authorHref: String = "",
    var recommendList: List<Book> = mutableListOf(),
    var chapterList: List<Chapter> = mutableListOf(),
    var words: String = "",
) {
    override fun toString(): String {
        return "\n Book(\n type=$type,\n title=$title,\n infoUrl=$infoUrl,\n newestChapterHref=$newestChapterHref,\n newestChapterTitle=$newestChapterTitle,\n author=$author,\n updateTime=$updateTime,\n imgUrl=$imgUrl,\n intro=$intro,\n authorHref=$authorHref,\n recommendList=$recommendList,\n chapterList=$chapterList \n)\n"
    }
}

