package com.jeong.sesac.chaksaicompose.component.myPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier, color: Color = Color.Gray) {
    Text(
        text,
        style = AppTheme.typography.titleSmall,
        color = color,
        modifier = modifier.padding(vertical = AppTheme.size.normal)
    )
}

@Composable
fun SwitchItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = AppTheme.typography.bodyLarge)
        Switch(onCheckedChange = {}, checked = false)
    }
}

@Composable
fun ETCItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text,
            style = AppTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = AppTheme.size.normal)
        )
        Icon(
            painter = painterResource(R.drawable.ic_right_arrow),
            contentDescription = "go_to_setting_page",
            modifier = Modifier
                .size(AppTheme.size.large),
            tint = Color.LightGray
        )
    }
}

@Composable
fun ProfileUI() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_default_profile),
            contentDescription = "profile_img",
            modifier = Modifier.size(AppTheme.size.xl),
            tint = Color.DarkGray
        )
    }
}