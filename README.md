# 🌵홈가드닝 쇼핑몰 프로젝트


## 프로젝트 PPT
___
[Gardening 프로젝트 -초안-.pptx](https://github.com/angpang2/Gardening/files/10531446/Gardening.-.-.pptx)


## 💭 기획의도
___
- Spring Boot를 기반으로 한 식물 판매 쇼핑몰 웹사이트 구축
- 코로나 이후 반려식물을 키우는 사람들이 증가하면서 홈가드닝 산업 매출이 상승 중인 부분을 고려해 기획
- 회원가입, 구매, 장바구니 등 고객 측면의 기본적인 기능과 운영자를 위한 관리자페이지 기능 설계

## ✔ 주요기능
___
![index](https://user-images.githubusercontent.com/115771656/215362358-2ae2f83e-6c7c-474b-9028-efa3e4f1c787.png)

***회원 관련 기능***
- 회원가입: 아이디(중복확인, 이메일인증), 비밀번호, 비밀번호 확인, 이메일, 이름, 휴대전화
- 로그인: 아이디, 카카오계정 로그인, 비밀번호 찾기
- 로그아웃
- 상품 주문·조회·검색·취소, 장바구니 담기·조회·삭제, 리뷰 작성·수정·삭제, Q&A 작성

<div align="center">
<img width="60%" src="https://user-images.githubusercontent.com/115771656/215362639-ae4be96b-562e-4965-9238-63d0793db1c6.png"/>
<img width="60%" src="https://user-images.githubusercontent.com/115771656/215362712-731996d8-87aa-45cf-981b-150b97b5dd47.png"/>
</div>

***상품 관련 기능***
- 상품 등록: 이미지, 상품명, 가격, 수량, 카테고리, 상품설명
- 상품 수정
- 상품 삭제
- 상품 조회: 최신순/낮은가격순/판매순, 5/10/20개씩 조회
- 상품 상세조회: 다중이미지, 상품명, 가격, 구매수량, 총상품금액, 설명, 장바구니, 구매하기, 리뷰&별평점
- 상품 검색: 상품이름
- 최근 본 상품

<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215395250-4d27d86f-8f58-4887-bc33-ee4006bf2e8b.png"/>
<img src="https://user-images.githubusercontent.com/115771656/215365427-23ad1289-d1b6-4148-8d43-97c31884323c.png"/>
</div>

***주문 관련 기능***
- 바로 주문 & 장바구니에서 주문
- 주문 신청 창: 이름, 전화번호, 이메일, 주소 입력, 결제(카카오페이), 주문 취소
- 주문 내역 조회

<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215365601-e4fe65a8-4558-4919-834e-6d82ce07491b.png"/>
<img src="https://user-images.githubusercontent.com/115771656/215364854-c91aa5c0-8dae-469b-a668-4eb756c9de60.png"/>
</div>

***장바구니 관련 기능***
- 장바구니 담기
- 장바구니 조회: 상품, 수량, 가격, 전체 주문 금액, 계속 쇼핑하기, 구매하기
- 장바구니 수정: 수량, 상품
- 장바구니 삭제
- 장바구니 상품 주문

<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215364385-253ce668-4e6b-4852-a263-755c02d79bf8.png"/>
</div>

***마이 페이지 관련 기능***
- 주문 확인/배송조회: 주문번호, 상품명, 배송상태(주문완료/배송준비/배송완료), 리뷰작성(배송완료시)
- 내정보: 비밀번호 확인, 비밀번호·이름·전화번호·이메일 변경

>주문확인/배송조회
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215365348-10d6ae3b-4bff-4012-8b88-c064571283eb.png"/>
<img src="https://user-images.githubusercontent.com/115771656/215370424-a395c87c-040f-4552-9e1b-ef17b3729768.png"/>
</div>

>내정보
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215365777-84209559-2f4b-4c96-8a49-d1769d62c1f2.png"/>
<img src="https://user-images.githubusercontent.com/115771656/215365817-6b513aa6-a6f0-40c3-ae77-14e3c7269487.png"/>
</div>

***관리자 페이지 관련 기능***
- 상품 관리 (페이징)
- 주문 관리 (페이징): 주문번호, 상품명, 주문날짜, 배송상태(주문완료/배송준비/배송완료), 구매자
- 회원 관리 (페이징): 회원 리스트, 회원 상세 정보
- 고객센터 관리 (페이징)
>상품 관리
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215370086-b9be4255-98f9-41dc-9928-20f6fa6c74c6.png"/>
<img src="https://user-images.githubusercontent.com/115771656/215363642-0b71d1ea-53b5-4f2e-87e7-0630e7700a9f.png"/>
</div>

>주문 관리
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215370963-fd9802a6-7420-42e4-a62f-7c0c34ba5c6a.png"/>
</div>

>회원 관리
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215370137-4b080b07-4874-414c-96dc-82ab9933c972.png"/>
</div>

>고객센터 관리
<div align="center">
<img src="https://user-images.githubusercontent.com/115771656/215370190-1a47be9b-a80a-4863-9ff6-73ea92957ddc.png"/>
</div>

***고객센터 관련 기능***
- Q&A 작성: 작성자, 제목, 내용
- Q&A 조회: 번호, 제목, 작성자, 날짜, 답변여부(N/Y), 작성자와 관리자만 볼 수 있도록 비밀글 적용
- Q&A 검색: 제목/작성자/내용
- Q&A 수정
- Q&A 삭제

<div align="center">
<img width="70%" src="https://user-images.githubusercontent.com/115771656/215372235-1f12a938-a30a-4d66-b7de-7bf3af7e0bd4.png"/>
</div>

***header***
- 소형식물/중형식물/대형식물/전체상품
- 로그인&로그아웃, 회원가입, 마이페이지, 고객센터, 주문하기&장바구니, 관리자페이지

***etc.***
- 오픈API를 통해 꽃이름, 꽃말 검색
- 매일 바뀌는 오늘의 꽃 모달창 
<div align="center">
<img width="60%" src="https://user-images.githubusercontent.com/115771656/215372069-f20fb124-3fe3-4cb5-90ee-9b6b4704c49f.png"/>
</div>

## 💻프로젝트 기간
___
2022.12.20 ~ 2023.01.30

 ## ⚙ 개발 환경
___

- IDE: Intellij IDEA 
- Spring Boot
- JDK 11
- Spring Data JPA
- Thymeleaf
- MySQL 
- OS: Window 10
