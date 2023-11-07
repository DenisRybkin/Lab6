package com.example.myapplication.fragments

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
import com.example.myapplication.models.Product
import com.example.myapplication.utils.formatDate
import java.util.UUID
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFragment (
    product: Product,
    onCheckboxClick: (UUID, Boolean) -> Unit,
    onCardClick: (UUID) -> Unit,
    cardClickable: Boolean? = null
) {
    Card(
        onClick =  { if(cardClickable == true) onCheckboxClick(product.id, !product.checked) else onCardClick(product.id) },
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
                Text(product.title, style = TextStyle(fontSize = 20.sp))
                Text("${product.price}$", style = TextStyle(fontSize = 17.sp))
                Text(formatDate(product.dateCreated), style = TextStyle(fontSize = 15.sp))
            }
            Column() {
                Checkbox(checked = product.checked, onCheckedChange = { onCheckboxClick(product.id, it) })
            }
        }
    }

}