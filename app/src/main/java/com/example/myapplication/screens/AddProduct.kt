package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun AddProduct(
    listState: MutableState<List<Product>>,
    navController: NavHostController,
) {
    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(10) }

    fun handleSaveProductChanges () {
        val newProduct = Product(title, price, Date())
        listState.value = listState.value.plus(newProduct)
        navController.navigate(pages[0])
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 0.dp, 0.dp, 10.dp)
    ) {
        HeaderFragment(
            "Adding product",
            "Add".takeIf { title.isNotEmpty() && price > 0 },
            ::handleSaveProductChanges,
            Icons.Filled.Add
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