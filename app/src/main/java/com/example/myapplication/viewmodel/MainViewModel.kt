package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.bean.Book
import com.example.myapplication.bean.Chapter
import com.example.myapplication.model.BookRepository

class MainViewModel : ViewModel() {
    private val _search = MutableLiveData<List<Book>>()
    val search: LiveData<List<Book>> = _search

    private val _info = MutableLiveData<Book>()
    val info: LiveData<Book> = _info

    private val _chapter = MutableLiveData<Chapter>()
    val chapter: LiveData<Chapter> = _chapter

    fun search(title: String) {
        Thread {
            BookRepository.search(title).also {
                _search.postValue(it)
            }
        }.start()
    }

    fun info(url: String) {
        Thread {
            BookRepository.info(url).also {
                _info.postValue(it)
            }
        }.start()
    }

    fun chapter(url: String) {
        Thread {
            BookRepository.chapter(url).also {
                _chapter.postValue(it)
            }
        }.start()
    }
}