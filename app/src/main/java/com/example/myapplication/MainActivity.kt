package com.example.myapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.fragments.HeaderFragment
import com.example.myapplication.fragments.NewsListFragment
import com.example.myapplication.mock.NewsList
import com.example.myapplication.models.News
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val listState = remember {
                    mutableStateOf<List<News>>(NewsList)
                }

                fun handleChaneCheckedNews(id: UUID, value: Boolean) {
                    val transform: (News) -> News = { if(it.id == id) it.copy(checked = value) else it }
                    listState.value = listState.value.map(transform)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,0.dp, 10.dp)) {
                        HeaderFragment("List of news", listState.value.count { it.checked }.takeIf { it > 0 }?.toString())
                        NewsListFragment(listState.value, ::handleChaneCheckedNews)
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