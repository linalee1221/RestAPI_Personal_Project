package com.example.eunsong_site.dao;

import com.example.eunsong_site.model.Complain;
import com.example.eunsong_site.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.dao
 * fileName : CustomerDao
 * author : Eunsong LEE
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07    Eunsong LEE        최초 생성
 */

@Mapper

public interface ComplainDao {

    List<Complain> findAll(Criteria criteria);

    List<Complain> findByTitleContaining(Criteria criteria);
    int selectTotalCount(String title);

    Optional<Complain> findById(Long id);

    long insertComplain(Complain complain);

    long updateComplain(Complain complain);

    int deleteComplain(Long id);

    int deleteAll();
}
