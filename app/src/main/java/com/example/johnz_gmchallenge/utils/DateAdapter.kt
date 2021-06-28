package com.example.johnz_gmchallenge.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

object DateAdapter {

    @FromJson
    fun stringToDate(timeStamp: String) =
            SimpleDateFormat(SERVER_TIMESTAMP, Locale.getDefault()).parse(timeStamp)

    @ToJson
    fun dateToString(date: Date) =
            SimpleDateFormat(SERVER_TIMESTAMP, Locale.getDefault()).format(date)
}