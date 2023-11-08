package com.example.myapplication.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HeaderFragment(
    text: String,
    extraText: String? = null,
    extraOnClick: (() -> Unit)? = null,
    extraIcon: ImageVector?
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black),
        )
        if(extraText != null) Button(
            onClick = extraOnClick ?: {},
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.errorContainer, containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(extraIcon ?: Icons.Filled.Delete, "Floating action button.")
            Text(text = "$extraText", style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black))
        }
    }
}