package com.example.eunsong_site.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName : com.example.eunsi.controller
 * fileName : WebConfig
 * author : Eunsong LEE
 * date : 2022-06-14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-14    Eunsong LEE        최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @CrossOrigin 어노테이션과 동일함

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // addMapping : springboot 서버로 들어오는 모든 요청
        registry.addMapping("/**")
                // 아래 주소로 들어오는 요청은 보안 허용
                .allowedOrigins("http://localhost:8080");

        registry.addMapping("/**")
//                아래 url 허용
                .allowedOrigins("http://localhost:8080")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name()
                );
    }
}
