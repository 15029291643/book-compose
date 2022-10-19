package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.bean.Book
import com.example.myapplication.bean.Chapter

@Composable
fun Info(chapterList: List<Chapter>, read: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Intro(Book())
        CatalogList(chapterList = chapterList, read = read, modifier = Modifier.weight(1F))
        Button(onClick = {
            read(chapterList[0].href)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "开始阅读")
        }
    }
}

@Composable
fun Intro(book: Book) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)) {
        val (i, t1, t2, t3, t4, t5) = createRefs()
        Image(painter = painterResource(id = com.example.myapplication.R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(i) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .size(120.dp, 160.dp))
        Text(text = "十三黄姐", modifier = Modifier.constrainAs(t1) {
            start.linkTo(i.end)
            top.linkTo(i.top)
            bottom.linkTo(t2.top)
        })
        Text(text = "捉着", modifier = Modifier.constrainAs(t2) {
            start.linkTo(i.end)
            top.linkTo(t1.bottom)
            bottom.linkTo(t3.top)
        })
        Text(text = "最新", modifier = Modifier.constrainAs(t3) {
            start.linkTo(i.end)
            top.linkTo(t2.bottom)
            bottom.linkTo(t4.top)
        })
        Text(text = "更新", modifier = Modifier.constrainAs(t4) {
            start.linkTo(i.end)
            top.linkTo(t3.bottom)
            bottom.linkTo(i.bottom)
        })
    }
}

@Composable
fun CatalogList(modifier: Modifier = Modifier, chapterList: List<Chapter>, read: (String) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(chapterList) { chapter ->
            CatalogItem(chapter = chapter, modifier = Modifier.clickable {
                read(chapter.href)
            })
        }
    }
}

@Composable
fun CatalogItem(modifier: Modifier = Modifier, chapter: Chapter) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = chapter.title, modifier.padding(vertical = 20.dp, horizontal = 10.dp))
    }
}
