package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.fragments.FloatingAddButtonFragment
import com.example.myapplication.mock.ProductList
import com.example.myapplication.models.Product
import com.example.myapplication.models.ProductListSaver
import com.example.myapplication.screens.AddProductScreen
import com.example.myapplication.screens.EditProductScreen
import com.example.myapplication.screens.ProductListScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.UUID


val pages = listOf<String>("PRODUCT_LIST", "EDIT_PRODUCT", "ADD_PRODUCT")

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                val listState = rememberSaveable(stateSaver = ProductListSaver) {
                    mutableStateOf<List<Product>>(ProductList)
                }
                val activeProductId = rememberSaveable {
                    mutableStateOf<UUID?>(null);
                }

                Scaffold(
                    floatingActionButton = {
                        if(navController.currentBackStackEntryAsState().value?.destination?.route == "PRODUCT_LIST")
                            FloatingAddButtonFragment(onClick = { navController.navigate(pages[2]) }) })
                {
                    NavHost(navController = navController, startDestination = pages[0]) {
                        composable(pages[0]) {
                            ProductListScreen(listState, navController, activeProductId)
                        }
                        composable(pages[1]) {
                            EditProductScreen(listState, navController, activeProductId)
                        }
                        composable(pages[2]) {
                            AddProductScreen(listState, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("My owner")
    }
}