package com.example.myapplication.models
import androidx.compose.runtime.saveable.listSaver
import java.util.Date
import java.util.UUID


data class Product(
    val title: String,
    val price: Int,
    val dateCreated: Date,
    var checked: Boolean = false,
    val id: UUID = UUID.randomUUID()
)

val ProductListSaver = listSaver<List<Product>, List<Any>>(
    save = { it.map { product -> listOf(
        product.title,
        product.price,
        product.dateCreated,
        product.checked,
        product.id)
        }},
    restore = { it.map { product -> Product(
            product[0] as String,
            product[1] as Int,
            product[2] as Date,
            product[3] as Boolean,
            product[4] as UUID)
        }
    }
)