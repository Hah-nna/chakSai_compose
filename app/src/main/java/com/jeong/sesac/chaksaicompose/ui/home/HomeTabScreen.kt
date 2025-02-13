package com.jeong.sesac.chaksaicompose.ui.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockNotes
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.home.LazyColUI
import com.jeong.sesac.chaksaicompose.component.home.TitleUi
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.NoteWithUser


@Composable
fun HomeTabScreen(
    navController: NavController
) {
    HomeTabContent()
}


@Composable
private fun HomeTabContent() {
    val mockData = mockNotes
    val composeCtx = LocalContext.current
    val onNoteClick = { noteData: NoteWithUser ->
        Toast.makeText(composeCtx, "${noteData.title}", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CommonSpacer(24)

        Text(
            stringResource(R.string.main_header, "하겐다즈"),
            style = AppTheme.typography.titleLarge
        )
        CommonSpacer(24)

        TitleUi(R.string.main_weekly_popular_note_title)

        LazyColUI(mockData, onClick = onNoteClick)

        CommonSpacer(24)

        TitleUi(R.string.main_recently_new_note_title)

        LazyColUI(mockData, onClick = onNoteClick)

    }

}


@Preview(showBackground = true)
@Composable
private fun HomeTabScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme{
    HomeTabScreen(navController = previewNavController)
    }
}