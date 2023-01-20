package com.example.shoppingmall.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodayFlowerListDTO {

    private int resultCnt; // 결과 갯수
    private int pageNo; // 페이지 번호
    private int numOfRows; // 한 페이지 결과 수

    private int dataNo; // 오늘의 꽃 index
    private int fMonth; //월
    private int fDay; //일

    private String flowNm; // 꽃 이름

    private String flowLang; // 꽃말



    private String fileName1; // 이미지 원본파일명
    private String fileName2;
    private String fileName3;


    private String imgUrl1; //이미지URL
    private String imgUrl2;
    private String imgUrl3;
}
