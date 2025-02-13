package com.jeong.sesac.chaksaicompose.ui.myPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonLarge
import com.jeong.sesac.chaksaicompose.component.button.OutlineButton
import com.jeong.sesac.chaksaicompose.component.myPage.ProfileUI
import com.jeong.sesac.chaksaicompose.component.myPage.TitleText
import com.jeong.sesac.chaksaicompose.component.textField.TextFieldNormal
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun MyProfileScreen(navContoller: NavController) {
    CommonTopAppBar("", null) { innerPadding ->
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
        TitleText(stringResource(R.string.profile), Modifier.align(Alignment.Start))
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
        ) {
            ProfileUI()
        }

        Row ( modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {


            OutlineButton(R.drawable.ic_gallery, R.string.albumBtnDesc, R.string.albumBtn, onClick = {} )

            OutlineButton(R.drawable.ic_camera, R.string.cameraBtnDesc, R.string.cameraBtn, onClick = {} )
            
        }
        TitleText(stringResource(R.string.nickname), Modifier.align(Alignment.Start))
        TextFieldNormal(text, stringResource(R.string.validNickname), modifier = Modifier.fillMaxWidth(), onValueChange = {} )
        Spacer(Modifier.weight(1f))
        CustomButtonLarge(stringResource(R.string.confirm), modifier = Modifier.padding(vertical = AppTheme.size.normal)) { }

    }

}

@Preview(showBackground = true)
@Composable
private fun MyProfileScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
    MyProfileScreen(previewNavController)
    }
}