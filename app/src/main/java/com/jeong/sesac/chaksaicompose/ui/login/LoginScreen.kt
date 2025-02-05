package com.jeong.sesac.chaksaicompose.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.ButtonTheme

@Composable
@Preview(showBackground = true)
fun LoginScreen() {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(stringResource(R.string.login_title), style = MaterialTheme.typography.titleLarge)
        Text(
            stringResource(R.string.login_sub_title),
            style = MaterialTheme.typography.titleMedium,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(80.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
            placeholder = {
                Text(
                    stringResource(R.string.login_tf_placeHolder),
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            textStyle = TextStyle(
                fontSize = 12.sp,
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.outline,
                unfocusedTextColor = Color.Gray,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.White
            )
        )

        Spacer(Modifier.height(80.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("등록", modifier = Modifier.padding(8.dp))
        }
    }
}



