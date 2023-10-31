package com.example.myapplication.models
import java.util.Date
import java.util.UUID

data class News(
    val title: String,
    val dateCreated: Date,
    var checked: Boolean = false,
    val id: UUID = UUID.randomUUID()
)
