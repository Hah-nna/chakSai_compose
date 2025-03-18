package com.jeong.sesac.chaksaicompose.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonLarge
import com.jeong.sesac.chaksaicompose.component.custom_text_field.NicknameTF
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.validation.checkNicknameValid
import com.jeong.sesac.chaksaicompose.viewModel.UserViewModel
import com.jeong.sesac.chaksaicompose.viewModel.appViewModelFactory
import kotlinx.coroutines.delay


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: UserViewModel = viewModel(factory = appViewModelFactory)
) {
    var nickname by remember { mutableStateOf("") }
    var validComment by remember { mutableStateOf("") }
    var isValidNickname by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val preference = AppPreferenceManager.getInstance(context)

    val duplicateState by viewModel.duplicateState.collectAsState()
    val userCreateState by viewModel.userCreateState.collectAsState()
//    Log.d("nickname", "$nickname")


    fun validNickname(input: String): Boolean {
        Log.d("input", "$input")
        return when {
            input.isEmpty() -> {
                validComment = context.getString(R.string.blank)
                false
            }
            input.length < 2 || input.length > 8 -> {
                validComment = context.getString(R.string.validComment1)
                isValidNickname = false
                false
            }

            !checkNicknameValid(input) -> {
                validComment = context.getString(R.string.validComment2)
                isValidNickname = false
                false
            }

            else -> {
                validComment = context.getString(R.string.validComment3)
                isValidNickname = true
                true
            }
        }
    }

    LaunchedEffect(key1 = nickname) {
        if(nickname.isNotEmpty() && validNickname(nickname)) {
            delay(300L)
            viewModel.checkDuplicatedNickname(nickname)
        }
    }

    LaunchedEffect(key1 = duplicateState, key2 = userCreateState) {
       when(duplicateState) {
          is UiState.Loading -> {
              validComment = " "
          }
          is UiState.Success -> {
               validComment = "사용가능한 닉네임입니다"
           }
          is UiState.Error -> {
               validComment = "중복되는 닉네임입니다"

           }
       }

        when(userCreateState) {
           is UiState.Loading -> {}
           is UiState.Success -> {
               val state = userCreateState as UiState.Success<String>
               Log.d("유저생성 성공?", "${state.data}")
               preference.apply {
                   nickName = nickname
                   userId = state.data
               }
               onLoginSuccess()
            }
           is UiState.Error -> {
           Log.d("err", "회원가입에러")
           // snack bar ㄱㄱ
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.size.medium)
            .background(Color.White),
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
        NicknameTF(nickname, modifier = Modifier, validComment, isValidNickname) {
            nickname = it
            validNickname(nickname)
        }

        Spacer(Modifier.height(80.dp))

        CustomButtonLarge("등록", modifier = Modifier, onClick = {
            viewModel.setUserInfo(nickname)
        })

    }
}

@Preview(name = "Light Mode")
@Composable
fun LoginScreenLightPreview() {
    AppTheme(darkTheme = false) {
        LoginScreen({})
    }
}

@Preview(name = "Dark Mode")
@Composable
fun LoginScreenDarkPreview() {
    AppTheme(darkTheme = true) {
        LoginScreen({})
    }
}

