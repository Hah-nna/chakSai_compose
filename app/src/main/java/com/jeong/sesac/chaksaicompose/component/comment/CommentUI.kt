package com.jeong.sesac.chaksaicompose.component.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jeong.sesac.chaksaicompose.component.img.ProfileSmall
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.Comment

@Composable
fun CommentUI(itemData: Comment, img: Int) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = AppTheme.size.small)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            ProfileSmall(img.toString())
            Text("mockNickname", modifier = Modifier.padding(horizontal = AppTheme.size.small))
            Text("5분전", style = AppTheme.typography.labelSmall, color = Color.DarkGray)
        }
        Text(itemData.content, modifier = Modifier.padding(vertical = AppTheme.size.small))
        HorizontalDivider()
    }
}
