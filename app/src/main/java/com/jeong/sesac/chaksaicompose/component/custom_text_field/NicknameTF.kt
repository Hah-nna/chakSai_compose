package com.jeong.sesac.chaksaicompose.component.custom_text_field

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun NicknameTF(text: String, modifier: Modifier, validComment: String, isValid: Boolean, onValueChange: (String) -> Unit) {
    val borderColor = when {
        text.isEmpty() -> Color.Gray
        isValid -> colorResource(R.color.valid)
        else -> colorResource(R.color.invalid)
    }

    Column {
    TextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(AppTheme.size.small),
        modifier =
            modifier.padding(vertical = dimensionResource(R.dimen.normalPadding))
                .fillMaxWidth().border(0.5.dp, borderColor, shape = RoundedCornerShape(AppTheme.size.small)),
        placeholder = {
            Text(
                stringResource(R.string.login_tf_placeHolder),
                style = TextStyle(fontSize = dimensionResource(R.dimen.fontNormal).value.sp)
            )
        },
        textStyle = TextStyle(
            fontSize = dimensionResource(R.dimen.fontNormal).value.sp,
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = AppTheme.colorScheme.outline,
            unfocusedTextColor = AppTheme.colorScheme.outline,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
        Text(validComment, color = colorResource(if (isValid) R.color.valid else R.color.invalid))
    }
}