package com.example.myapplication.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.bean.Book
import com.example.myapplication.bean.Chapter
import com.example.myapplication.util.Nav
import com.example.myapplication.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    val bookList by viewModel.search.observeAsState(initial = mutableListOf())
    val book by viewModel.info.observeAsState(initial = Book())
    val chapter by viewModel.chapter.observeAsState(initial = Chapter())

    Scaffold(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = Nav.SEARCH) {
            composable(Nav.SEARCH) {
                Search(bookList = bookList, search = {
                    viewModel.search(it)
                }, info = {
                    viewModel.info(bookList[it].infoUrl)
                    navController.navigate(Nav.INFO)
                })
            }
            composable(Nav.INFO) {
                Info(chapterList = book.chapterList) {
                    viewModel.chapter(it)
                    navController.navigate(Nav.READ)
                }
            }
            composable(Nav.READ) {
                Read(chapter = chapter)
            }
        }
    }
}
