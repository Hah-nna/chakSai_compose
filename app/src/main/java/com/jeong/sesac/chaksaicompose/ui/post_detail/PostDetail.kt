package com.jeong.sesac.chaksaicompose.ui.post_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.AppPreferenceManager
import com.jeong.sesac.chaksaicompose.common.coil.CoilImgRequest
import com.jeong.sesac.chaksaicompose.common.toTimeConverter
import com.jeong.sesac.chaksaicompose.component.BasicTopAppBar
import com.jeong.sesac.chaksaicompose.component.CommonSpacer
import com.jeong.sesac.chaksaicompose.component.button.CustomButtonSmall
import com.jeong.sesac.chaksaicompose.component.img.ProfileSmall
import com.jeong.sesac.chaksaicompose.component.comment.CommentUI
import com.jeong.sesac.chaksaicompose.component.custom_text_field.TextFieldNormal
import com.jeong.sesac.chaksaicompose.model.UiState
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.viewmodel.CommentViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.PostViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.viewmodel_factory.appViewModelFactory
import com.jeong.sesac.feature.model.PostWithUser

@Composable
fun PostDetailScreen(
    preference: AppPreferenceManager,
    postId: String,
    onBackClick: () -> Unit,
    onEditPostClick: (postId: String) -> Unit,
    onEditCommentClick: (commentId: String, content: String) -> Unit,
    postViewModel: PostViewModel = viewModel(factory = appViewModelFactory),
    commentViewModel: CommentViewModel = viewModel(factory = appViewModelFactory)
) {

    var commentText by remember { mutableStateOf("") }

    val post = postViewModel.selectedPostState.collectAsState()
    val comments = commentViewModel.commentListState.collectAsState()

    fun deleteComment(commentId: String) {
        commentViewModel.deleteComment(preference.userId, postId, commentId)
    }

    LaunchedEffect(key1 = Unit, key2 = comments) {
        postViewModel.selectPost(postId)
        commentViewModel.getComments(preference.userId, postId)
    }


    BasicTopAppBar(stringResource(R.string.blank), null, onBackClick) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {

            when (val postContent = post.value) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    item {
                        AsyncImage(
                            modifier = Modifier.height(250.dp),
                            model = CoilImgRequest.getImgRequest(postContent.data.image),
                            contentScale = ContentScale.Crop,
                            contentDescription = stringResource(R.string.post_img_desc),
                            onState = { state -> CoilImgRequest.coilCallbackLog(postContent.data.image, state) }
                        )
                        CommonSpacer(20)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProfileSmall(postContent.data.userInfo.profile)
                            Spacer(Modifier.width(AppTheme.size.small))
                            Text(
                                postContent.data.userInfo.nickName.ifEmpty { stringResource(R.string.deleted_account) },
                                style = AppTheme.typography.bodySmall
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                        )
                        Text(
                            postContent.data.title,
                            style = AppTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        CommonSpacer(10)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                postContent.data.libraryName,
                                style = AppTheme.typography.titleSmall,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                postContent.data.createdAt.toTimeConverter(),
                                style = AppTheme.typography.titleSmall,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        CommonSpacer(20)

                        Text(postContent.data.content, style = AppTheme.typography.bodyMedium)

                        CommonSpacer(30)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.small)
                        ) {

                            TextFieldNormal(commentText,
                                stringResource(R.string.tf_placeHolder),
                                modifier = Modifier
                                    .weight(1f),
                                onValueChange = { text -> commentText = text })

                            CustomButtonSmall(stringResource(R.string.register), onClick = {
                                commentViewModel.createComment(preference.userId, postId, commentText)
                            })
                        }

                        CommonSpacer(30)

                    }

                }
                is UiState.Error -> {}
            }

            when (val commentList = comments.value) {
                is UiState.Loading -> {
                    item {
                        CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center))
                    }
                }
                is UiState.Success -> {
                    if (commentList.data.isEmpty()) {
                        item {
                            Text("댓글이 없습니다.", modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp))
                        }
                    } else {
                        items(commentList.data) { item ->
                            CommentUI(itemData = item, onEditClick={ onEditCommentClick(item.id, item.content) }, onDeleteClick={ deleteComment(item.id)}, userId = preference.userId)
                        }
                    }
                }
                is UiState.Error -> {
                    item {
                        Text("err", color = Color.Red)
                    }
                }
            }


        }
    }
}