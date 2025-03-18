package com.jeong.sesac.chaksaicompose.ui.post_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.coil.CoilImgRequest
import com.jeong.sesac.chaksaicompose.common.mockComments
import com.jeong.sesac.chaksaicompose.common.toTimeConverter
import com.jeong.sesac.chaksaicompose.component.BasicTopAppBar
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonSmall
import com.jeong.sesac.chaksaicompose.component.img.ProfileSmall
import com.jeong.sesac.chaksaicompose.component.comment.CommentUI
import com.jeong.sesac.chaksaicompose.component.custom_text_field.TextFieldNormal
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.viewModel.PostViewModel
import com.jeong.sesac.chaksaicompose.viewModel.appViewModelFactory
import com.jeong.sesac.feature.model.PostWithUser

@Composable
fun PostDetailScreen(
    postId: String,
    onBackClick: () -> Unit,
    onEditPostClick: (postId: String) -> Unit,
    onEditCommentClick: (commentId: String) -> Unit,
    viewModel: PostViewModel = viewModel(factory = appViewModelFactory)
) {

    val post = viewModel.selectedPostState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.selectPost(postId)
    }
    when (val state = post.value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            DetailContent(state.data, onBackClick, {}, {})
        }

        is UiState.Error -> {}
    }
}

@Composable
fun DetailContent(
    post: PostWithUser,
    onBackClick: () -> Unit,
    onEditPostClick: (postId: String) -> Unit,
    onEditCommentClick: (commentId: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    BasicTopAppBar(stringResource(R.string.blank), post.image, onBackClick) { innerPadding ->

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {

        item {
            AsyncImage(
                modifier = Modifier.height(250.dp),
                model = CoilImgRequest.getImgRequest(post.image),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.post_img_desc),
                onState = { state -> CoilImgRequest.coilCallbackLog(post.image, state) }
            )
            CommonSpacer(20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileSmall(post.userInfo.profile)
                Spacer(Modifier.width(AppTheme.size.small))
                Text(
                    post.userInfo.nickName.ifEmpty { stringResource(R.string.deleted_account) },
                    style = AppTheme.typography.bodySmall
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Text(
                post.title,
                style = AppTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            CommonSpacer(10)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    post.libraryName,
                    style = AppTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    post.createdAt.toTimeConverter(),
                    style = AppTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
            }

            CommonSpacer(20)

            Text(post.content, style = AppTheme.typography.bodyMedium)

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
        }

            items(mockComments) { item ->
                CommentUI(itemData = item, R.drawable.ic_launcher_background)
            }

        }

}
}


@Preview(showBackground = true)
@Composable
private fun PostDetailScreenPreview() {
    AppTheme {
        PostDetailScreen("1", {}, {}, {})
    }
}