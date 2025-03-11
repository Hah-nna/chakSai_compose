# 도서관 커뮤니티 APP - 책사이 README
## 목차
1. [프로젝트 소개](#1-프로젝트-소개)
2. [개발 인원](#2-개발-인원)
3. [프로젝트 기간](#3-프로젝트-기간)
4. [개발 환경](#4-개발-환경)
5. [기능](#5-기능)
6. [트러블 슈팅](#6-트러블-슈팅)

## 1. 프로젝트 소개
도서관 이용자들의 소통 및 교류를 통해 도서관의 실질적인 이용률을 높일 수 있는 지역 도서관 기반 커뮤니티 APP


## 2. 개발 인원

|포지션|이름|
|---|---|
|기획,디자인,개발|정하나|

<br>

## 3. 프로젝트 기간 

25.01.24 ~ 25.02.24

Jetpack Compose Migration : 25.03.10 ~ 

<br>

## 4. 개발 환경

|분류|스택|
|---|---|
|Language|Kotlin (2.1.0)| 
|MinSDK|24| 
|TargetSDK​|35|
|VersionControl|Git|
|IDE|Android Studio|
|Architecture|MVVM, Multi-Module|
|Network|Retrofit2, Moshi|
|Storage|Firebase Database, Firebase Storage|
|Image Cache​|Coil|
|Asynchronous|Coroutine, Corbind|
|API, Libraries​|서지정보 OPEN API, CameraX, ML Kit, TedPermission|

<br>

## 5. 기능

**<ins>(1) Login Screen </ins>**
<div align="center">
<img src="https://github.com/user-attachments/assets/00ee40af-1c5a-4fad-9497-7107925fb2ae" width="200" height="400"/>
</div>

간단하게 닉네임으로 회원가입을 하는 화면
1. 닉네임이 존재하면 메인화면으로 이동, 아니라면 닉네임을 입력해 회원가입을 할 수 있게 함
2. 닉네임을 입력 시 실시간 유효성 검사를 실시함
3. 사용자가 닉네임을 다 입력 후 디바운스를 사용해 0.3초후에 닉네임 중복검사를 실시함

**<ins>(2) Main Screen </ins>**  

<div align="center">
<img src="https://github.com/user-attachments/assets/b247c26f-3a0f-468e-9609-11ae999942f6" width="200" height="400"/>
</div>

인기순 포스팅과 최근에 작성된 포스팅을 볼 수 있음
1. 더보기를 누르면 인기순 혹은 최근 일주일 내에 작성된 포스팅 리스트로 이동함
2. 이미지를 누르면 바로 포스팅 디테일 화면으로 이동함

**<ins>(3) Book Connect Screen </ins>**  

<div align="center">
<img src="https://github.com/user-attachments/assets/55387071-b696-4570-bd9d-b60e11cc837a" width="200" height="400"/>
</div>

- Book Connect
사용자가 도서관까지 이동하여 해당 도서관의 포스팅과 서평에 접근할 수 있음 

1. 사용자의 위치를 기준으로 300m 내에 도서관 목록을 label로 보여줌(300m 내에 없다면 500m씩 늘려서 최대 10km까지 범위를 넓혀 도서관 목록을 호출함)
2. 현재 사용자의 위치에서 가장 가까운 도서관까지의 거리를 사용자에게 보여줌
3. 사용자가 도서관으로 이동해 도서관과의 거리가 100m 이하로 차이가 나는 경우 해당 도서관의 포스팅, 서평에 접근할 수 있음
4. 사용자가 처음 도서관 목록을 찾은 범위의 90% 이상을 이동해야 새롭게 도서관 목록을 받아오게 함
(ex: 초기에는 사용자의 위치에서 300m내에 있는 도서관을 찾아 label로 보여줌 -> 사용자가 300m의 90%인 270m 이상 이동해야 새로운 도서관 목록을 호출해 label로 보여줌)
 
<div align="center">
<img src="https://github.com/user-attachments/assets/f8db5733-624d-4973-ba6d-6f6309c6dc5c" width="200" height="400"/>
<img src="https://github.com/user-attachments/assets/7ddf6e76-6656-4d71-9598-9e521a06a578" width="200" height="400"/>
</div>

- Posting Screen
해당 도서관의 포스팅들을 보고, 작성할 수 있는 화면
또한 댓글을 작성할 수 있음

1. 포스팅 내용을 도서관에 관한 자유로운 내용이 될 수 있음
2. 포스팅을 작성할 때 CameraX를 사용해서 간단하게 사진 촬영을 하여 포스팅에 첨부할 수 있음
3. 해당 포스팅에 댓글을 작성 및 수정, 삭제를 할 수 있음

<div align="center">
<img src="https://github.com/user-attachments/assets/7dda213e-f8c5-4ba0-8e50-5dc6d7581893" width="200" height="400"/>
</div>

- Book Review Screen
해당 도서관의 서평들을 볼 수 있고, 책을 등록할 수 있음
서평들의 책 이미지를 누르면 책정보와 해당 책에 대한 다른 유저들의 서평들을 볼 수 있음

1. 처음 보이는 것은 책 리스트이나 그 안에 해당 책에 대한 서평과 별점을 남길 수 있음
2. 처음에는 책을 등록하고, 동시에 서평을 등록할 수 있음
3. 책을 등록할 때 CameraX와 ML Kit(Barcode Scanner)를 사용해서 책 뒷면의 ISBN Barcode를 스캔하면 책의 타이틀을 화면에 보여줌
4. 책이 등록된 이후로는 서평만 등록할 수 있음

**<ins>(4) My Record Screen </ins>**

<div align="center">
<img src="https://github.com/user-attachments/assets/d7997ac2-6363-4a34-a086-54088a7f6613" width="200" height="400"/>
</div>

내가 작성한 포스팅과 좋아요를 누른 포스팅을 한 번에 볼 수 있음
1. viewpager2 + tabScreen을 사용해 처음에는 내가 작성한 포스팅들을 볼 수 있음
2. 오른쪽으로 스와이핑하면 내가 좋아요를 누른 포스팅들을 볼 수 있음 
<br>

## 6. 트러블 슈팅
추가 예정
