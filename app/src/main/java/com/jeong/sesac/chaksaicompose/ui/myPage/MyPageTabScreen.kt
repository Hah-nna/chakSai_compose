package com.jeong.sesac.chaksaicompose.ui.myPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.component.myPage.ETCItem
import com.jeong.sesac.chaksaicompose.component.myPage.ProfileUI
import com.jeong.sesac.chaksaicompose.component.myPage.SwitchItem
import com.jeong.sesac.chaksaicompose.component.myPage.TitleText
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun MyPageTabScreen(navController: NavController) {
    MyPageTabContent()
}

@Composable
fun MyPageTabContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(R.dimen.largePadding)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.profileLage))
                        .height(dimensionResource(R.dimen.profileLage))
                        .clip(CircleShape),
                ) {
                    ProfileUI()
                }
                Text(
                    "닉네임",
                    style = AppTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = AppTheme.size.small)
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "go_to_setting_page",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(AppTheme.size.large),
                tint = Color.LightGray
            )
        }

        TitleText(stringResource(R.string.darkMode), Modifier.align(Alignment.Start))
        SwitchItem(stringResource(R.string.applyDarkMode))

        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = AppTheme.size.normal))

        TitleText(stringResource(R.string.push), Modifier.align(Alignment.Start))

        SwitchItem(stringResource(R.string.pushLikes))

        SwitchItem(stringResource(R.string.pushComment))

        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = AppTheme.size.normal))

        ETCItem(stringResource(R.string.contact))
        HorizontalDivider(thickness = 0.5.dp, modifier = Modifier.padding(vertical = AppTheme.size.normal))

        ETCItem(stringResource(R.string.policies))

    }
}


@Preview(showBackground = true)
@Composable
private fun MyPageTabScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
    MyPageTabScreen(previewNavController)
    }
}
