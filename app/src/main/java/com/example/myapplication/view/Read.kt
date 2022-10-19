package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.bean.Book
import com.example.myapplication.bean.Chapter

@Composable
fun Read(chapter: Chapter) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        Text(text = chapter.title,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp))
        Spacer(modifier = Modifier.padding(top = 50.dp))
        chapter.content.forEach {
            Text(text = it,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp))
        }
    }
}