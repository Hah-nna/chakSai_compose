package com.jeong.sesac.chaksaicompose.ui.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.ui.theme.AppTypography

val cardBG = listOf(
    R.color.note_bg_1,
    R.color.note_bg_2,
    R.color.note_bg_3,
    R.color.note_bg_4,
    R.color.note_bg_5,
    R.color.note_bg_6
)

@Composable
fun LibraryWriteNoteScreen(
    navController: NavController
) {
    LibraryWriteNoteContainer()
}

@Composable
fun LibraryWriteNoteContainer() {
    CommonTopAppBar("", stringResource(R.string.desc_top_app_bar)) { innerPadding ->
        LibraryWriteNoteContent(innerPadding)
    }
}


@Composable
fun LibraryWriteNoteContent(padding: PaddingValues) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(stringResource(R.string.note_writing_header), style = AppTheme.typography.titleMedium)
        CommonSpacer(20)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.cardPadding)
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cardBG) { item ->
                BGListUI(item)

            }
        }

        Text(
            stringResource(R.string.note_writing_title),
            style = AppTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.Start).padding(vertical = 8.dp)
        )

        TextField(
            value = text,
            onValueChange = { text = it },
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(
                    stringResource(R.string.tf_title_place_holder),
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
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.25f),
            elevation = CardDefaults.cardElevation(4.dp),
        )
        {
            TextField(
                value = text,
                onValueChange = { newText: String -> text = newText },
                modifier = Modifier.fillMaxSize(),
                singleLine = false,
                placeholder = { Text("내용을 직성해주세요", style = AppTheme.typography.bodyMedium) },
                textStyle = AppTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        CommonSpacer(20)
        Text(
            stringResource(R.string.note_writing_img), style = AppTheme.typography.titleSmall,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = 12.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(Color.Transparent, Color.Black, Color.LightGray, Color.Black),
            border = BorderStroke(0.2.dp, Color.Black)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_gallery),
                tint = Color.Black,
                contentDescription = "upload image",
                modifier = Modifier
                    .width(24.dp)
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonColors(
                MaterialTheme.colorScheme.primaryContainer,
                Color.Black,
                Color.LightGray,
                Color.White
            )
        ) {
            Text("등록", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun BGListUI(bg: Int) {
    Card (
        modifier = Modifier.padding(8.dp).width(38.dp).height(38.dp).clip(CircleShape),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(bg)
        )
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun LibraryWriteNoteScreenPreview() {
    val previewNavController = rememberNavController()
    LibraryWriteNoteScreen(previewNavController)
}