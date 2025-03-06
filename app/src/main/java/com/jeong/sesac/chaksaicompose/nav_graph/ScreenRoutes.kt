package com.jeong.sesac.chaksaicompose.nav_graph

sealed class ScreenRoutes(val routeName : String) {
    /**
     * bottom tab routes
     * */
    data object HomeTab : ScreenRoutes("Home")
    data object LibraryMapTab : ScreenRoutes("Map")
    data object RecordTab : ScreenRoutes("Record")
    data object MyPageTab : ScreenRoutes("My")

    /**
     * 하위 스크린
     * */
    sealed interface HomeTabScreenGroup {
        // 이번주 새로운 post 리스트 스크린
        data object NewNotes : ScreenRoutes("new_posts")
        // 이번 주 인기 post 리스트 스크린
        data object WeeklyNotes : ScreenRoutes("weekly_posts")
    }

    sealed interface LibraryMapTabScreenGroup {
        // 도서관에 작성된 post 리스트들을 볼 수 있는 스크린
        data object LibraryPostList : ScreenRoutes("library_post_list")
        // 해당 도서관에 post를 작성하는 스크린
        data object LibraryWritePost : ScreenRoutes("library_write_post")
        // 해당 도서관에 등록된 북 리스트 스크린
        data object LibraryBook : ScreenRoutes("library_book_list")
        // 북 디테일 페이지
        data object LibraryBookDetail : ScreenRoutes("library_book_detail")
        // 북+서평 등록 페이지
        data object LibraryWriteBook : ScreenRoutes("library_write_book")
        // 서평 수정 페이지
        data object LibraryEditBookReview : ScreenRoutes("edit_bookReview")
    }

    sealed interface MyPageScreenGroup {
        // 메인 마이페이지 스크린
        data object MyProfile : ScreenRoutes("my_profile")
        // 닉네임, 프로필 수정 페이지
        data object EditMyInfo : ScreenRoutes("edit_myInfo")
        // 문의 페이지
        data object Contact: ScreenRoutes("contact")
    }

    sealed interface PostDetailScreenGroup {
        // 포스트 디테일 스크린
        data object LibraryPostDetail : ScreenRoutes("library_post_detail")
        // 포스트 수정 스크린
        data object EditPostScreen : ScreenRoutes("edit_post")
        // comment 수정 스크린
        data object EditCommentScreen : ScreenRoutes("edit_comment")
    }
}