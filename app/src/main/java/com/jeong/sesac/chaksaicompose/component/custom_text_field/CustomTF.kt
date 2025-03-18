package com.jeong.sesac.chaksaicompose.component.custom_text_field

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

@Composable
fun TextFieldNormal(text: String, placeHolder: String, modifier: Modifier, isSingleLine: Boolean = true, isBorder: Boolean = true, onValueChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        singleLine = isSingleLine,
        shape = RoundedCornerShape(AppTheme.size.small),
        modifier =
        if(isBorder) {
        modifier.padding(vertical = dimensionResource(R.dimen.normalPadding))
            .fillMaxWidth().border(0.5.dp, Color.Gray, shape = RoundedCornerShape(AppTheme.size.small))
        } else {
            modifier.padding(vertical = dimensionResource(R.dimen.normalPadding))
                .fillMaxWidth()
        },
        placeholder = {
            Text(
                placeHolder,
                style = TextStyle(fontSize = dimensionResource(R.dimen.fontNormal).value.sp)
            )
        },
        textStyle = TextStyle(
            fontSize = dimensionResource(R.dimen.fontNormal).value.sp,
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = AppTheme.colorScheme.outline,
            unfocusedTextColor = Color.Gray,
            focusedIndicatorColor = if(isBorder) AppTheme.colorScheme.primary else Color.Transparent,
            unfocusedIndicatorColor = if(isBorder) Color.White else Color.Transparent
        )
    )
}
