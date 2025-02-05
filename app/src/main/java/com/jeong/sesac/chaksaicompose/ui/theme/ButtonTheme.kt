package com.jeong.sesac.chaksaicompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object ButtonTheme {
    val buttonColor = ButtonColors(
        containerColor = Yellow300,
        contentColor = Color.White,
        disabledContentColor = Color.LightGray,
        disabledContainerColor = Color.DarkGray
    )

    val defaultButtonShape = RoundedCornerShape(8.dp)
}