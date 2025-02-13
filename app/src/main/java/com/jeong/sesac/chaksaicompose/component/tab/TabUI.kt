package com.jeong.sesac.chaksaicompose.component.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.theme.AppTheme
import com.jeong.sesac.feature.model.NoteWithUser
import kotlinx.coroutines.launch


@Composable
fun TabContentUI(
    pagerState: PagerState,
    pages: List<@Composable ()-> Unit>
) {
    HorizontalPager(state = pagerState) { idx ->
        pages[idx]()
    }
}

@Composable
fun TabLayoutUI(
    tabItem : List<String>,
    pagerState: PagerState,
    modifier: Modifier,
    onTabSelected: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = {tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = AppTheme.colorScheme.primary,
                height = 3.dp
            )
        },
        modifier = modifier
    ) {
        tabItem.forEachIndexed { idx, data ->
            Tab(
                selectedContentColor = AppTheme.colorScheme.primary,
                unselectedContentColor = AppTheme.colorScheme.outline,
                selected = pagerState.currentPage == idx,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(idx)
                        onTabSelected(idx)
                    }
                },
                text = { Text(text = tabItem[idx], style = AppTheme.typography.bodySmall) }
            )
        }
    }
}

@Composable
fun GridNoteList(
    notes: List<NoteWithUser>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(AppTheme.size.small)
    ) {
        items(notes) {note ->
            ListItem(note)
        }
    }
}

@Composable
fun ListItem(data: NoteWithUser) {
    Card(
        modifier = Modifier
            .width(dimensionResource(R.dimen.cardMedium))
            .height(dimensionResource(R.dimen.cardMedium))
            .padding(dimensionResource(R.dimen.cardPadding)),
        shape = RoundedCornerShape(AppTheme.size.normal),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.size.small,
            pressedElevation = AppTheme.size.xs
        )
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            painter = painterResource(data.image),
            contentDescription = "note_image",
            contentScale = ContentScale.Crop
        )
    }
}