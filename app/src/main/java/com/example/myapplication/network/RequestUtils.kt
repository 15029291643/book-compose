package com.example.myapplication.network

import android.util.Log
import okhttp3.OkHttpClient

object RequestUtils {
    private val SELECT_URL = "https://www.xbiquge.so/modules/article/search.php?searchkey="
    private const val TAG = "Request"

    private val client by lazy { OkHttpClient() }

    fun html(url: String): String {
        Log.e(TAG, "html: $url")
        val request = okhttp3.Request.Builder().url(url).build()
        val l = System.currentTimeMillis()
        val response = client.newCall(request).execute()
        val l1 = System.currentTimeMillis()
        Log.e(TAG, "request: ${l1 - l}")
        return response.body()!!.string()
    }
}