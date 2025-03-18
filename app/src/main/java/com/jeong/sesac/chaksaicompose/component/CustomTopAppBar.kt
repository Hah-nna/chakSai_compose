package com.jeong.sesac.chaksaicompose.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jeong.sesac.chaksaicompose.R

/**
 * 기본 top app bar
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTopAppBar(
    title: String?,
    description: String?,
    onBackClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text(title ?: stringResource(R.string.blank), fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = description
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}

// CollapsingToolbar 구현 중...
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLargeTopAppBar(
    imageUrl: String,
    onBackClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { stringResource(R.string.blank) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.goToBack)
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
                modifier = Modifier.fillMaxHeight(fraction = 0.5f)
            )
        },
    ) { paddingValues ->
        content(paddingValues)
    }
}