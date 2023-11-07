package com.example.myapplication.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.Product
import java.util.UUID

@Composable
fun ProductListFragment(
    defaultValues: List<Product> = listOf(),
    onCheckboxClick: (UUID, Boolean) -> Unit,
    onCardClick: (UUID) -> Unit,
    cardClickable: Boolean? = null
)
{
    LazyColumn(
        modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(defaultValues) {
            item: Product ->
            ProductFragment(
                item,
                onCheckboxClick,
                onCardClick,
                cardClickable
            )
        }
    }
}