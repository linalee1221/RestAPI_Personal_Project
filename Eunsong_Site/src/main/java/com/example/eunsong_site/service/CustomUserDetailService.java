package com.example.eunsong_site.service;

import com.example.eunsong_site.dao.UserDao;
import com.example.eunsong_site.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName : com.example.eunsi.service
 * fileName : CustomUserDetailService
 * author : Eunsong LEE
 * date : 2022-06-21
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21    Eunsong LEE        최초 생성
 */
// @Service : springboot에 객체로 생성 ( ex) @Component, @Mapper )
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao; // 객체 정의( null => 스프링객체 받기)

    @Override
    public UserDetails loadUserByUsername(String id)
            throws UsernameNotFoundException {
        return userDao.findById(id);
    }

    public UserDetails findByName(String name) {
        return userDao.findByName(name);
    }

    //    유저객체가 null이면 insert하고(유저생성), 아니면 -1 반환하는 메소드
    public int signInUser(User user) {
        if(userDao.findByName(user.getUsername()) == null) {
            return userDao.insertUser(user);
        } else {
            return -1;
        }
    }
}
