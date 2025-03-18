package com.jeong.sesac.chaksaicompose.nav_graph

import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.chaksaicompose.ui.map.LibraryMapTabScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryPostListScreen
import com.jeong.sesac.chaksaicompose.ui.map.LibraryWritePostScreen
import kotlinx.serialization.Serializable

@Serializable
data object LibraryMapBaseRoute

@Serializable
data object LibraryMapScreen

@Serializable
data class LibraryPostList(val libraryName: String)

@Serializable
data class LibraryWritePost(val libraryName: String)

@Serializable
data class LibraryBookReviewList(val libraryName: String)

@Serializable
data class LibraryWriteBook(val libraryName: String)

@Serializable
data class LibraryBookReviewDetail(val bookId: String)

@Serializable
data class LibraryEditBookReview(val bookReviewId: String)


fun NavGraphBuilder.libraryMapGraph(
    onNavigationUp: () -> Unit,
    onNavigationToPosts: () -> Unit,
    onNavigationToWritePost: () -> Unit,
    onNavigationToDetailPost: (postId: String) -> Unit,
    onNavigationToBookReviews: () -> Unit,
    onNavigationToCreateBook: () -> Unit,
    onNavigationToEditBookReview: () -> Unit
) {
    navigation<LibraryMapBaseRoute>(
        startDestination = LibraryMapScreen,
    ) {

        /**
         * 해당 탭에 접근시 보이는 초기 화면
         * */
        composable<LibraryMapScreen> {
            LibraryMapTabScreen()
        }

        /**
         * 특정 도서관에 도착하면 접근할 수 있는 해당 도서관의 포스트 리스트 스크린
         * */
        composable<LibraryPostList>
         {backStackEntry ->
             val libraryName = backStackEntry.arguments?.getString(stringResource(R.string.arg_libraryName)) ?: return@composable
                 LibraryPostListScreen(libraryName = libraryName, onBackClick = onNavigationUp)
        }
        /**
         * 해당 도서관에 포스팅을 할 수 있는 스크린
         * */
        composable<LibraryWritePost> { backStackEntry ->
            val libraryName = backStackEntry.arguments?.getString(stringResource(R.string.arg_libraryName)) ?: return@composable
            LibraryWritePostScreen(
                libraryName = libraryName,
                onBackClick = onNavigationUp
            )
        }

        /**
         * 해당 도서관의 서평 리스트 스크린
         * */
        composable<LibraryBookReviewList>{ backStackEntry ->
            val libraryName = backStackEntry.arguments?.getString(stringResource(R.string.arg_libraryName)) ?: return@composable
//            LibraryBookReviewListScreen(
            //            libraryName = libraryName,
            //        onBackClick = onNavigationUp
            //            )
            // 위 스크린 ui 만들깋ㅎㅎㅎㅎㅎㅎㅎ
        }

        /**
         * 해당 도서관에 도서등록과 동시에 서평을 남길 수 있는 스크린
         * */
        composable<LibraryWriteBook>{ backStackEntry ->
            val libraryName = backStackEntry.arguments?.getString(stringResource(R.string.arg_libraryName)) ?: return@composable
//            LibraryWriteBookReviewScreen()
        }
        /**
         * 책 + 서평 디테일 스크린
         * */
        composable<LibraryBookReviewDetail>{ backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("backStackEntry") ?: return@composable
            //   LibraryDetailBookReviewScreen(
            //          bookId = bookId,
//                  onBackClick = onNavigationUp
            //   )
        }

        /**
         * 서평 수정 스크린
         * */
        composable<LibraryEditBookReview>{ backStackEntry ->
            val bookReviewId = backStackEntry.arguments?.getString("bookReviewId") ?: return@composable
            // LibraryEditBookReviewScreen(
            // bookReviewId = bookReviewId,
            // onBackClick = onNavigationUp
            //)
        }
    }
}