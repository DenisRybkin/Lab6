package com.example.myapplication.models
import java.util.Date
import java.util.UUID

data class Product(
    val title: String,
    val price: Int,
    val dateCreated: Date,
    var checked: Boolean = false,
    val id: UUID = UUID.randomUUID()
)
