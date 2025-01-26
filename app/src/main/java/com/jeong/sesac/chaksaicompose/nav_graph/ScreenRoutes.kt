package com.jeong.sesac.chaksaicompose.nav_graph

sealed class ScreenRoutes(val routeName : String) {
    /**
     * bototm tab routes
     * */
    data object HomeTab : ScreenRoutes("Home")
    data object LibraryMapTab : ScreenRoutes("Map")
    data object RecordTab : ScreenRoutes("Record")
    data object ChattingTab : ScreenRoutes("Chatting")
    data object MyPageTab : ScreenRoutes("MyPage")

    /**
     * 하위 스크린
     * */
    sealed interface HomeTabScreenGroup {
        // 이번주 새로운 쪽지 리스트 스크린
        data object NewNotes : ScreenRoutes("new_notes")
        // 이번 주 인기 쪽지 리스트 스크린
        data object WeeklyNotes : ScreenRoutes("weekly_notes")
    }

    sealed interface LibraryMapTabScreenGroup {
        // 도서관을 선택하고 해당 도서관에 쪽지를 작성하는 스크린
        data object LibraryWriteNote : ScreenRoutes("library_write_note")
        // 내가 선택한 도서관 및 다른 도서관에 작성된 쪽지들 리스트들을 볼 수 있는 스크린
        data object LibraryNoteList : ScreenRoutes("library_note_list")
        // LibraryMap 스크린에서 검색창을 누르면 나오는 프래그먼트
        data object MapSearch : ScreenRoutes("map_search")
    }

    sealed interface MyPageScreenGroup {
        data object MyProfile : ScreenRoutes("my_profile")
    }

    sealed interface CommonScreenGroup {
        // 쪽지 디테일 스크린
        data object LibraryNoteDetail : ScreenRoutes("library_note_detail")
    }
}