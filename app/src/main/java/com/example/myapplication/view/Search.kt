package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.myapplication.bean.Book

@Composable
fun Search(
    bookList: List<Book>,
    search: (title: String) -> Unit,
    info: (Int) -> Unit,
) {
    Column {
        SearchText(search)
        SearchList(bookList, info)
    }
}

@Composable
fun SearchList(bookList: List<Book>, info: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(bookList) { index, book ->
            BookItem(book = book, modifier = Modifier.clickable {
                info(index)
            })
        }
    }
}

@Composable
fun BookItem(book: Book, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        val (l, b, i, t1, t2, t3, t4) = createRefs()
        Image(
            painter = painterResource(id = com.example.myapplication.R.drawable.img),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(i) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .height(120.dp)
                .width(75.dp)
        )
        Text(text = book.title,
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(t1) {
                    top.linkTo(i.top)
                    bottom.linkTo(t2.top)
                    start.linkTo(i.end)
                }
        )
        Text(text = book.type,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(t2) {
                    top.linkTo(t1.bottom)
                    bottom.linkTo(t3.top)
                    start.linkTo(i.end)
                }
        )
        Text(text = book.author,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(t3) {
                    top.linkTo(t2.bottom)
                    bottom.linkTo(t4.top)
                    start.linkTo(i.end)
                }
        )
        Text(
            text = book.intro,
            fontSize = 12.sp,
            maxLines = 2,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .constrainAs(t4) {
                    top.linkTo(t3.bottom)
                    bottom.linkTo(i.bottom)
                    start.linkTo(i.end)
                    end.linkTo(b.start)
                    width = Dimension.fillToConstraints
                }
        )
        Image(
            painter = painterResource(id = com.example.myapplication.R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .constrainAs(b) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .size(width = 40.dp, height = 40.dp)
                .clickable {

                }

        )
        Text(text = "",
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .constrainAs(l) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .height(1.dp)
                .background(color = Color.Red)

        )
    }
}

@Composable
fun SearchText(search: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        var title by remember {
            mutableStateOf("斗罗大陆")
        }
        TextField(value = title, onValueChange = {
            title = it
        })
        Button(onClick = {
            search(title)
        }) {
            Text(text = "搜索")
        }
    }
}

