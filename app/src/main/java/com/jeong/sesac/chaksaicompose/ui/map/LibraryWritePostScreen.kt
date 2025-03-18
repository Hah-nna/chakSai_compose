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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.component.BasicTopAppBar
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonLarge
import com.jeong.sesac.chaksaicompose.component.my_page.TitleText
import com.jeong.sesac.chaksaicompose.component.custom_text_field.TextFieldNormal
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme

val cardBG = listOf(
    R.color.note_bg_1,
    R.color.note_bg_2,
    R.color.note_bg_3,
    R.color.note_bg_4,
    R.color.note_bg_5,
    R.color.note_bg_6
)

@Composable
fun LibraryWritePostScreen(libraryName: String, onBackClick: () -> Unit) {
    LibraryWritePostContainer(libraryName, onBackClick)
}

@Composable
fun LibraryWritePostContainer(libraryName: String, onBackClick: () -> Unit) {
    BasicTopAppBar("", stringResource(R.string.desc_top_app_bar), onBackClick) { innerPadding ->
        LibraryWritePostContent(innerPadding)
    }
}


@Composable
fun LibraryWritePostContent(padding: PaddingValues) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = AppTheme.size.medium),
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
            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            items(cardBG) { item ->
                BGListUI(item)

            }
        }

        TitleText(
            stringResource(R.string.note_writing_title),
            modifier = Modifier.align(Alignment.Start).padding(vertical = AppTheme.size.normal),
            Color.Black

        )

        TextFieldNormal(text, stringResource(R.string.tf_title_place_holder), isBorder = false, modifier = Modifier, onValueChange = {})

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.25f),
            elevation = CardDefaults.cardElevation(4.dp),
        )
        {
            TextFieldNormal(text, stringResource(R.string.tf_write_content), isSingleLine = false, isBorder = false, modifier = Modifier, onValueChange = {})
        }

        CommonSpacer(20)
        TitleText(
            stringResource(R.string.note_writing_img),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(vertical = AppTheme.size.normal),
            Color.Black
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
                contentDescription = stringResource(R.string.uploadImageDesc),
                modifier = Modifier
                    .width(24.dp)
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        CustomButtonLarge(stringResource(R.string.register), modifier = Modifier.padding(vertical = AppTheme.size.normal)) { }

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
private fun LibraryWritePostScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
        LibraryWritePostScreen("", {})
    }
}