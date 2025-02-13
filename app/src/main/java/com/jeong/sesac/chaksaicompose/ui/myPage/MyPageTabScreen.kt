package com.jeong.sesac.chaksaicompose.ui.myPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.ui.theme.AppTypography

@Composable
fun MyPageTabScreen(navController: NavController) {
    MyPageTabContent()
}

@Composable
fun MyPageTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 18.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),  // Box 내에서 중앙 정렬
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .clip(CircleShape),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_default_profile),
                            contentDescription = "profile_img",
                            modifier = Modifier.size(24.dp),
                            tint = Color.DarkGray
                        )
                    }
                }
                Text(
                    "닉네임",
                    style = AppTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "go_to_setting_page",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp),
                tint = Color.LightGray
            )
        }


        Text("다크모드", style = AppTheme.typography.titleSmall, color = Color.Gray, modifier = Modifier.align(Alignment.Start).padding(vertical = 8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
        Text("다크모드 적용", style = AppTheme.typography.bodyLarge)
        Switch(onCheckedChange = {}, checked = false)
        }

        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 12.dp))


        Text("PUSH", style = AppTheme.typography.titleSmall, color = Color.Gray, modifier = Modifier.align(Alignment.Start).padding(vertical = 8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("내쪽지에 좋아요가 눌릴 때 알림받기", style = AppTheme.typography.bodyLarge)
            Switch(onCheckedChange = {}, checked = false)
        }

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("내쪽지에 댓글이 달릴 때 알림받기", style = AppTheme.typography.bodyLarge)
            Switch(onCheckedChange = {}, checked = false)
        }

        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 12.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
        Text("문의하기", style = AppTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 12.dp))
            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "go_to_setting_page",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.LightGray
            )
        }
        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = 12.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("이용약관 및 개인정보 방침", style = AppTheme.typography.bodyLarge, modifier = Modifier.padding(vertical = 12.dp))
            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "go_to_setting_page",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.LightGray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MyPageTabScreenPreview() {
    val previewNavController = rememberNavController()
    MyPageTabScreen(previewNavController)
}
