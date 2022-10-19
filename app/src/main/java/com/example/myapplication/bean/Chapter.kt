package com.example.myapplication.bean

data class Chapter(
    var href:String = "",
    var title: String = "",
    var content: List<String> = mutableListOf()
) {
    override fun toString(): String {
        return "\nChapter(\nhref=$href,\n title=$title\n, content=$content\n)\n"
    }
}