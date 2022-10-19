package com.example.myapplication.util

import java.net.URLEncoder

fun String.title() = URLEncoder.encode(this, "GB2312")