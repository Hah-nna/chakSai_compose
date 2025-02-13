package com.jeong.sesac.chaksaicompose.ui.noteDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockComments
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.CommonTopAppBar
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonSmall
import com.jeong.sesac.chaksaicompose.component.ProfileSmall
import com.jeong.sesac.chaksaicompose.component.comment.CommentUI
import com.jeong.sesac.chaksaicompose.component.textField.TextFieldNormal
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.NoteWithUser
import com.jeong.sesac.feature.model.UserInfo
import java.util.Date

private fun tempNoteDate(): NoteWithUser {

    return NoteWithUser(
        id = "note3",
        userInfo = UserInfo(
            id = "user456",
            profile = "https://example.com/profile2.jpg",
            nickName = "벤앤제리"
        ),
        image = R.drawable.ic_launcher_background,
        title = "세 번째 쪽지",
        createdAt = Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000),  // 2일 전
        libraryName = "구의도서관",
        content = "새로 나온 책 추천합니다",
        likes = 15
    )
}

@Composable
fun NoteDetailScreen(
    noteId: String, note: NoteWithUser,
    onBackClick: () -> Unit
) {
    DetailContent(note) {}
}

@Composable
fun DetailContent(note: NoteWithUser, onBackClick: () -> Unit) {
    var text by remember { mutableStateOf("") }
    CommonTopAppBar("", null) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(note.image),
                contentDescription = "note_img",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            CommonSpacer(20)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileSmall(R.drawable.ic_launcher_background)
                Spacer(Modifier.width(AppTheme.size.small))
                Text(note.userInfo.nickName, style = AppTheme.typography.bodySmall)
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Text(
                note.title,
                style = AppTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            CommonSpacer(10)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    note.libraryName,
                    style = AppTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    note.createdAt.toString(),
                    style = AppTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
            }

            CommonSpacer(20)

            Text(note.content, style = AppTheme.typography.bodyMedium)

            CommonSpacer(30)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
            ) {

                TextFieldNormal(text, stringResource(R.string.tf_placeHolder), modifier = Modifier
                    .weight(1f), onValueChange = {})

                CustomButtonSmall(stringResource(R.string.register), onClick = {})
            }

            CommonSpacer(30)

            Text(stringResource(R.string.comment_count, mockComments.size))

            LazyColumn {
                items(mockComments) { item ->
                    CommentUI(itemData = item, R.drawable.ic_launcher_background)
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
private fun NoteDetailScreenPreview() {
    val previewNavController = rememberNavController()
    AppTheme {
        NoteDetailScreen("1", tempNoteDate(), onBackClick = {})
    }
}