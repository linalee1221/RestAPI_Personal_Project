package com.example.eunsong_site.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.eunsi.model
 * fileName : MessageResponse
 * author : Eunsong LEE
 * date : 2022-06-21
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21    Eunsong LEE        최초 생성
 */
@Setter
@Getter
@ToString
public class MessageResponse {
    private String message;

    //    생성자
    public MessageResponse(String message) {
        this.message = message;
    }
}
