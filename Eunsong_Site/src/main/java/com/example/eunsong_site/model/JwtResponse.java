package com.example.eunsong_site.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * packageName : com.example.eunsi.model
 * fileName : JwtResponse
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
public class JwtResponse {

    private String token; // 웹토큰
    private String type = "Bearer"; // JWT(자바웹토큰)를 보낼때 사용될 타입
    private String id;
    private String username;
    private String email;
    private List<String> roles; // 역할 ( ROLE_ADMIN, ROLE_USER 등등 )

    // type을 제외한 모든 매개변수가 있는 생성자 정의
    public JwtResponse(String token, String id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
