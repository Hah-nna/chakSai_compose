package com.jeong.sesac.chaksaicompose.ui.post_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.chaksaicompose.viewmodel.CommentViewModel
import com.jeong.sesac.chaksaicompose.viewmodel.viewmodel_factory.appViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCommentScreen(
    commentId: String,
    postId: String,
    userId: String,
    content: String,
    onBackClick: () -> Unit,
    viewModel: CommentViewModel = viewModel(factory = appViewModelFactory)
) {
    var newCommentText by remember { mutableStateOf(content) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    ),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(stringResource(R.string.edit_comment_top_app_bar), style = AppTheme.typography.titleMedium)
                            Text(stringResource(R.string.completed_btn), style = AppTheme.typography.titleMedium.copy(color = Color.Gray),
                                modifier = Modifier.clickable {
                                    viewModel.updateComment(userId, postId, commentId, newCommentText )
                                    onBackClick()
                                })
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {onBackClick()}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.desc_top_app_bar)
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                TextField(
                    newCommentText, onValueChange = { text ->
                        newCommentText = text
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            viewModel.updateComment(userId, postId, commentId, newCommentText )
                        }
                )
            }

        }
    }
}

