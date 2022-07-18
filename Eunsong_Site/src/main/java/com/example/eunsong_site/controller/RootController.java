package com.example.eunsong_site.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.example.eunsi.controller
 * fileName : RootController
 * author : Eunsong LEE
 * date : 2022-06-14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-14    Eunsong LEE        최초 생성
 */

@Controller
public class RootController implements ErrorController {

    // 에러(Not Found)가 발생하면 강제적으로 index.html 주소로 변경(redirect)
    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }
}
