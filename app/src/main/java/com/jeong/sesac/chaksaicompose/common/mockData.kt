package com.jeong.sesac.chaksaicompose.common

import com.jeong.sesac.chaksaicompose.R
import com.jeong.sesac.feature.model.Comment
import com.jeong.sesac.feature.model.PostWithUser
import com.jeong.sesac.feature.model.UserInfo
import java.util.Date


// Mock 데이터
val mockUserInfo = UserInfo(
    id = "user123",
    profile = "https://example.com/profile.jpg",
    nickName = "하겐다즈"
)

val mockNotes = listOf(
    PostWithUser(
        id = "note1",
        userInfo = mockUserInfo,
        image = R.drawable.ic_launcher_background.toString(),
        title = "첫 번째 쪽지",
        createdAt = System.currentTimeMillis(),
        libraryName = "평화나루도서관",
        content = "오늘 도서관에서 좋은 책을 읽었어요",
        likes = emptyList()
    ),
    PostWithUser(
        id = "note2",
        userInfo = mockUserInfo,
        image = R.drawable.ic_launcher_background.toString(),
        title = "두 번째 쪽지",
        createdAt = System.currentTimeMillis(),
        libraryName = "광진정보도서관",
        content = "조용한 도서관에서의 하루",
        likes = emptyList()
    ),
    PostWithUser(
        id = "note3",
        userInfo = UserInfo(
            id = "user456",
            profile = "https://example.com/profile2.jpg",
            nickName = "벤앤제리"
        ),
        image = R.drawable.ic_launcher_background.toString(),
        title = "세 번째 쪽지",
        createdAt = System.currentTimeMillis(),
        libraryName = "구의도서관",
        content = "새로 나온 책 추천합니다",
        likes = emptyList()
    )
)

val mockComments = listOf(
    Comment(
        id = "comment1",
        userId = "user123",
        noteId = "note1",
        content = "저도 그 책 읽어보고 싶네요. 제목이 무엇인가요?",
        createdAt = System.currentTimeMillis(),
    ),
    Comment(
        id = "comment2",
        userId = "user456",
        noteId = "note1",
        content = "평화나루도서관은 자주 가시나요? 저도 한번 가보고 싶어요.",
        createdAt = System.currentTimeMillis(),

    ),
    Comment(
        id = "comment3",
        userId = "user123",
        noteId = "note2",
        content = "광진정보도서관은 정말 조용하죠. 저도 자주 가는 곳입니다.",
        createdAt = System.currentTimeMillis(),
    ),
    Comment(
        id = "comment4",
        userId = "user456",
        noteId = "note3",
        content = "어떤 책인지 궁금합니다! 추천해주신 책 꼭 읽어보고 싶어요.",
        createdAt = System.currentTimeMillis(),

    ),
    Comment(
        id = "comment5",
        userId = "user123",
        noteId = "note3",
        content = "저도 구의도서관에서 봤던 책인데, 정말 좋았어요!",
        createdAt = System.currentTimeMillis(),

    )
)