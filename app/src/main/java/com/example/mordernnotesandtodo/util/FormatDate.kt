package com.example.mordernnotesandtodo.util

import  java.text.DateFormat
import java.util.*


class FormatDate {
    val date: String

    init {
        val currentTime = Calendar.getInstance().time
        date = DateFormat.getDateTimeInstance().format(currentTime)
    }
}