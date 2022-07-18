package com.example.eunsong_site.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.customerspring.model
 * fileName : Customer
 * author : Eunsong LEE
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07    Eunsong LEE        최초 생성
 */
@Getter
@Setter
@ToString

// Long(객체), long(일반자료형)
public class Complain {
    private Long id;            // 순번
    private String title;       // 컴플레인 제목
    private String content;     // 컴플레인 내용
    private String writer;      // 작성자
    private String targetPage;  // 적용하는 페이지
    private String deleteYn;    // 삭제여부
    private String insertTime;  // 생성날짜
    private String updateTime;  // 수정날짜
    private String deleteTime;  // 삭제날짜
}
