package com.jeong.sesac.chaksaicompose.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommonSpacer(int: Int) {
    Spacer(Modifier.height(int.dp))
}