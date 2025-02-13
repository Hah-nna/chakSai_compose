package com.jeong.sesac.chaksaicompose.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun CustomButtonLarge(
    label: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colorScheme.primaryContainer,
            contentColor = Color.White,
            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.DarkGray
    ), shape = AppTheme.shape.button
    ) {
    Text(label, modifier = Modifier.padding(AppTheme.size.small),
        style = AppTheme.typography.labelSmall
        )
    }
}

@Composable
fun CustomButtonSmall(
    label: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = AppTheme.shape.button,
        modifier = Modifier.height(dimensionResource(R.dimen.labelSmallHeight))
    ) {
        Text(label)
    }
}

@Composable
fun OutlineButton(icon: Int, desc: Int, text: Int, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(desc),
            modifier = Modifier.size(AppTheme.size.large),
            tint = Color.Black
        )
        Text(stringResource(text), style = AppTheme.typography.labelSmall, color = Color.Black, modifier = Modifier.padding(start = 8.dp) )
    }
}


// previews
@Preview(name = "Light Mode")
@Composable
fun ButtonPreviewLight() {
    AppTheme(darkTheme = false) {
        CustomButtonLarge("라이트 모드 버튼", modifier = Modifier, onClick = {})
    }
}

@Preview(name = "Dark Mode")
@Composable
fun ButtonPreviewDark() {
    AppTheme(darkTheme = true) {
        CustomButtonLarge("다크 모드 버튼", modifier = Modifier, onClick = {})
    }
}

@Preview(name = "Light Mode")
@Composable
fun ButtonPreviewSmallL() {
    AppTheme(darkTheme = false) {
        CustomButtonSmall("라이트 모드 버튼", onClick = {})
    }
}

@Preview(name = "Dark Mode")
@Composable
fun ButtonPreviewSmallD() {
    AppTheme(darkTheme = true) {
        CustomButtonSmall("다크 모드 버튼", onClick = {})
    }
}

@Preview(name = "Light Mode")
@Composable
fun OutlineButtonPreviewL() {
    AppTheme(darkTheme = false) {
        OutlineButton(R.drawable.ic_gallery, R.string.albumBtnDesc, R.string.albumBtn, onClick = {})
    }
}

@Preview(name = "Dark Mode")
@Composable
fun OutlineButtonPreviewD() {
    AppTheme(darkTheme = true) {
        OutlineButton(R.drawable.ic_gallery, R.string.albumBtnDesc, R.string.albumBtn, onClick = {})
    }
}

