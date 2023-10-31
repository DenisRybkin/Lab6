package com.example.myapplication.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("dd.MM.yyyy")
fun formatDate(date: Date) = dateFormat.format(date)