package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.fragments.HeaderFragment
import com.example.myapplication.fragments.ProductListFragment
import com.example.myapplication.models.Product
import com.example.myapplication.pages
import java.util.UUID

@Composable
fun ProductListScreen(
    listState: MutableState<List<Product>>,
    navController: NavHostController,
    activeProductId: MutableState<UUID?>
) {
    fun handleChaneCheckedNews(id: UUID, value: Boolean) {
        val transform: (Product) -> Product = { if(it.id == id) it.copy(checked = value) else it }
        listState.value = listState.value.map(transform)
    }

    fun handleDeleteChecked() {
        listState.value = listState.value.filter { !it.checked }
    }

    fun handleOpenProductScreen(id: UUID) {
        val foundProduct = listState.value.find { it.id == id } ?: return
        activeProductId.value = foundProduct.id
        navController.navigate(pages[1])
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 0.dp, 0.dp, 10.dp)
    ) {
        HeaderFragment(
            "List of products",
            listState.value.count { it.checked }.takeIf { it > 0 }?.toString(),
            ::handleDeleteChecked,
            Icons.Filled.Delete
        )
        ProductListFragment(
            defaultValues = listState.value,
            onCheckboxClick = ::handleChaneCheckedNews,
            cardClickable = listState.value.count { it.checked } > 0,
            onCardClick = ::handleOpenProductScreen
        )
    }
}