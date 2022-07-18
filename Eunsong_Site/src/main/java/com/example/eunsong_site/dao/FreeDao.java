package com.example.eunsong_site.dao;

import com.example.eunsong_site.model.Free;
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

public interface FreeDao {

    List<Free> findAll(Criteria criteria);

    List<Free> findByTitleContaining(Criteria criteria);
    int selectTotalCount(String title);

    Optional<Free> findById(Long id);

    long createFree(Free free);

    long updateFree(Free free);

    int deleteFree(Long id);

    int deleteAll();
}
