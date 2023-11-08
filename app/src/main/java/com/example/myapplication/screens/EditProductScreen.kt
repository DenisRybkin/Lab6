package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.fragments.HeaderFragment
import com.example.myapplication.models.Product
import com.example.myapplication.pages
import java.util.Date
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    listState: MutableState<List<Product>>,
    navController: NavHostController,
    activeProductId: MutableState<UUID?>
) {
    val currentProduct = listState.value.find { it.id == activeProductId.value } ?: return;

    var title by rememberSaveable { mutableStateOf(currentProduct.title) }
    var price by rememberSaveable { mutableStateOf(currentProduct.price) }

    fun handleSaveProductChanges () {
        val transform: (Product) -> Product = { if(it.id == activeProductId.value)
            it.copy(title = title, price = price, dateCreated = Date())
        else it
        }
        listState.value = listState.value.map(transform)
        navController.navigate(pages[0])
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 0.dp, 0.dp, 10.dp)
    ) {
        HeaderFragment(
            currentProduct.title,
            "save".takeIf { title.isNotEmpty() && title != currentProduct.title
                    && price > 0 && price != currentProduct.price },
            ::handleSaveProductChanges,
            Icons.Filled.Done
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 15.dp, 15.dp, 15.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") }
            )
            OutlinedTextField(
                value = price.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = { price = it.toInt() },
                maxLines = 1,
                label = { Text("Price") }
            )
        }
    }
}