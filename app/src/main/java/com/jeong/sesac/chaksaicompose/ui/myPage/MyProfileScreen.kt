package com.jeong.sesac.chaksaicompose.ui.myPage

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.chaksaicompose.ui.theme.AppTypography

@Composable
fun MyProfileScreen(navContoller: NavController) {
    CommonTopAppBar("", "myprofile screen") { innerPadding ->
        MyProfileContent(innerPadding)
    }

}

@Composable
fun MyProfileContent(innerPadding: PaddingValues) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        Text("프로필 사진", style = AppTypography.titleSmall, color = Color.Gray, modifier = Modifier.align(Alignment.Start).padding(vertical = 8.dp))
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_default_profile),
                    contentDescription = "profile_img",
                    modifier = Modifier.size(32.dp),
                    tint = Color.DarkGray
                )
            }
        }

        Row ( modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {

            OutlinedButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_gallery),
                    contentDescription = "pick photo from an album",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
                Text("앨범에서 선택", style = AppTypography.labelSmall, color = Color.Black, modifier = Modifier.padding(start = 8.dp) )
            }

            OutlinedButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_camera),
                    contentDescription = "pick photo from an album",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
                Text("앨범에서 선택", style = AppTypography.labelSmall, color = Color.Black, modifier = Modifier.padding(start = 8.dp) )
            }
        }

        Text("닉네임", style = AppTypography.titleSmall, color = Color.Gray, modifier = Modifier.align(Alignment.Start).padding(vertical = 12.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth().border(0.5.dp, Color.Gray, shape = RoundedCornerShape(12.dp)),
            placeholder = {
                Text(
                    "2~8글자, 영어 대소문자, 한글, 숫자만 가능",
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            textStyle = TextStyle(
                fontSize = 12.sp,
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                ),
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth().padding(vertical = 16.dp),
            colors = ButtonColors(
                MaterialTheme.colorScheme.primaryContainer,
                Color.Black,
                Color.LightGray,
                Color.White
            )
        ) {
            Text("확인", style = AppTypography.labelMedium, modifier = Modifier.padding(vertical = 8.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MyProfileScreenPreview() {
    val previewNavController = rememberNavController()
    MyProfileScreen(previewNavController)
}