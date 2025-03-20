package com.jeong.sesac.chaksaicompose.component.bottom_sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomSheetMenu(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(text, modifier = modifier
        .padding(vertical = AppTheme.size.medium)
        .fillMaxWidth()
        .clickable {
            onClick()
        }, textAlign = TextAlign.Center)
}

@OptIn(ExperimentalMaterial3Api::class)
fun hideBottomSheetWithAnimation(
    scope: CoroutineScope,
    sheetState: SheetState,
    onComplete: () -> Unit
) {
    scope.launch {
        sheetState.hide()
    }.invokeOnCompletion {
        if(!sheetState.isVisible) onComplete()
    }
}