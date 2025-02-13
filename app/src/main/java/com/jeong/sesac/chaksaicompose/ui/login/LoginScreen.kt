package com.jeong.sesac.chaksaicompose.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonLarge
import com.jeong.sesac.chaksaicompose.component.textField.TextFieldNormal
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun LoginScreen() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preference = AppPreferenceManager.getInstance(context).nickName

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = AppTheme.size.medium).background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(stringResource(R.string.login_title), style = AppTheme.typography.titleLarge)
        Text(
            stringResource(R.string.login_sub_title),
            style = AppTheme.typography.titleMedium,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(80.dp))
        TextFieldNormal(text, stringResource(R.string.login_tf_placeHolder), modifier = Modifier ) {
            text = it
        }

        Spacer(Modifier.height(80.dp))

        CustomButtonLarge("등록", modifier = Modifier, onClick = {})
    }
}

@Preview(name = "Light Mode")
@Composable
fun LoginScreenLightPreview() {
    AppTheme(darkTheme = false) {
        LoginScreen()
    }
}

@Preview(name = "Dark Mode")
@Composable
fun LoginScreenDarkPreview() {
    AppTheme(darkTheme = true) {
        LoginScreen()
    }
}

