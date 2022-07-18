package com.example.eunsong_site.dao;

import com.example.eunsong_site.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName : com.example.eunsi.dao
 * fileName : UserDao
 * author : Eunsong LEE
 * date : 2022-06-21
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-21    Eunsong LEE        최초 생성
 */
@Mapper
public interface UserDao {
    //    id로 유저 가져오기 메소드
    User findById(String id);

    //    name으로 유저 가져오기 메소드
    User findByName(String name);

    //    회원정보(user) insert 메소드
    int insertUser(User user);

    //    회원정보(user) delete 메소드
    int deleteUser(String name);
}
