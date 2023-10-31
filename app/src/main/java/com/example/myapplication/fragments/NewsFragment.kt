package com.example.myapplication.fragments

import android.os.Debug
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.models.News
import com.example.myapplication.utils.formatDate
import java.util.UUID
import android.util.Log

@Composable
fun NewsFragment (
    news: News,
    onCheckboxClick: (UUID, Boolean) -> Unit
) {
    Log.e("NewsFragment ${news.id}",news.toString())
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(news.title, style = TextStyle(fontSize = 20.sp))
                Text(formatDate(news.dateCreated), style = TextStyle(fontSize = 15.sp))
            }
            Column() {
                Checkbox(checked = news.checked, onCheckedChange = { onCheckboxClick(news.id, it) })
            }
        }
    }

}