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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.mockComments
import com.jeong.sesac.chaksaicompose.ui.theme.ButtonTheme
import com.jeong.sesac.feature.model.Comment
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

    DetailContent(note){}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(note: NoteWithUser, function: () -> Unit) {
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Image(
                painter = painterResource(note.image),
                contentDescription = "note_img",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "profile_img",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(8.dp))
                Text(note.userInfo.nickName, style = MaterialTheme.typography.bodySmall)
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )


            Text(
                note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    note.libraryName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    note.createdAt.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(Modifier.height(20.dp))

            Text(note.content, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    placeholder = {
                        Text(
                            stringResource(R.string.tf_placeHolder),
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
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    )

                )

                Button(
                    onClick = {},
                    shape = ButtonTheme.defaultButtonShape,
                    modifier = Modifier.height(50.dp)
                ) {
                    Text("등록")
                }
            }

            Spacer(Modifier.height(30.dp))

            Text(stringResource(R.string.comment_count, mockComments.size))

            LazyColumn {
                items(mockComments) {
                    item ->
                    CommentUI(itemData = item)
                }


            }

        }
    }
}



@Composable
fun CommentUI(itemData: Comment) {
    Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "profile_img",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .clip(CircleShape)
            )
            Text(itemData.userInfo.nickName, modifier = Modifier.padding(horizontal = 8.dp))
            Text(itemData.createdAt, style = MaterialTheme.typography.labelSmall, color = Color.DarkGray)
        }
        Text(itemData.content, modifier = Modifier.padding(vertical = 8.dp))
        HorizontalDivider()
    }
}


@Preview(showBackground = true)
@Composable
private fun NoteDetailScreenPreview() {
    val previewNavController = rememberNavController()
    NoteDetailScreen("1", tempNoteDate(), onBackClick = {})
}