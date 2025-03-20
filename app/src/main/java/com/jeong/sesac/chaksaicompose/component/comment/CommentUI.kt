package com.jeong.sesac.chaksaicompose.component.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.common.toTimeConverter
import com.jeong.sesac.chaksaicompose.component.bottom_sheet.BottomSheetMenu
import com.jeong.sesac.chaksaicompose.component.bottom_sheet.hideBottomSheetWithAnimation
import com.jeong.sesac.chaksaicompose.component.img.ProfileSmall
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.CommentWithUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentUI(
    itemData: CommentWithUser,
    userId: String,
    onEditClick: (commentId: String) -> Unit,
    onDeleteClick: (commentId: String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = AppTheme.size.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ProfileSmall(itemData.userInfo.profile)
                Text(
                    text = itemData.userInfo.nickName.ifEmpty { "Unknown User" },
                    modifier = Modifier.padding(horizontal = AppTheme.size.small)
                )
                Text(
                    itemData.createdAt.toTimeConverter(),
                    style = AppTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = stringResource(R.string.comment_menu),
                modifier = Modifier
                    .height(AppTheme.size.large)
                    .height(AppTheme.size.large)
                    .clickable {
                        showBottomSheet = true
                    }
            )
        }
        Text(itemData.content, modifier = Modifier.padding(vertical = AppTheme.size.small))
        HorizontalDivider()
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(itemData.userInfo.id != userId ) {
                BottomSheetMenu(stringResource(R.string.edit_menu), {
                    hideBottomSheetWithAnimation(scope, sheetState) { showBottomSheet = false }
                    onEditClick(itemData.id)
                }
                )
                BottomSheetMenu(stringResource(R.string.delete_menu), {
                    hideBottomSheetWithAnimation(scope, sheetState) { showBottomSheet = false }
                    onDeleteClick(itemData.id)
                })
                } else {
                    BottomSheetMenu(stringResource(R.string.report_menu), {
                        hideBottomSheetWithAnimation(scope, sheetState) { showBottomSheet = false }
                        // 신고...기능...
                    })
                }
                BottomSheetMenu(stringResource(R.string.close_menu),
                    { hideBottomSheetWithAnimation(scope, sheetState) { showBottomSheet = false } }
                )
            }
        }
    }
}