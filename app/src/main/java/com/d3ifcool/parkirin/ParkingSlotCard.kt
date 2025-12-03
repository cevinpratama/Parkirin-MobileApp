package com.d3ifcool.parkirin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.d3ifcool.parkirin.ui.theme.BiruJ

@Composable
fun ParkingSlotCard(
    slotName: String,
    status: String,
    size: Dp,
    shape: Shape,
    fontSize: TextUnit
) {
    val backgroundColor = if (status.equals("Kosong", ignoreCase = true)) {
        BiruJ.copy(alpha = 0.59f)
    } else {
        Color.Gray.copy(alpha = 0.59f)
    }

    Box(
        modifier = Modifier
            .size(size)
            .shadow(
                elevation = 10.dp,
                shape = shape
            )
            .background(Color.White, shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = slotName,
            fontSize = fontSize,
            color = Color.White
        )
    }
}