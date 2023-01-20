package com.example.shoppingmall.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodayFlowerDTO {

    private String dataNo; // 오늘의 꽃 index
    private int fMonth; //월
    private int fDay; //일
    private String flowNm; // 꽃 이름
    private String fSctNm; //학명
    private String fEngNm; //영문명
    private String flowLang; // 꽃말
    private String fContent; //내용

    private String fUse; //이용
    private String fGrow; //기르기
    private String fType; //자생지

    private String fileName1; // 이미지 원본파일명
    private String fileName2;
    private String fileName3;


    private String imgUrl1; //이미지URL
    private String imgUrl2;
    private String imgUrl3;


}
