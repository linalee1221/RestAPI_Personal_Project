package com.example.eunsong_site.dao;

import com.example.eunsong_site.model.Faq;
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

public interface FaqDao {

    List<Faq> findAll(Criteria criteria);

    List<Faq> findByTitleContaining(Criteria criteria);
    int selectTotalCount(String title);

    Optional<Faq> findById(Long id);

    long insertFaq(Faq faq);

    long updateFaq(Faq faq);

    int deleteFaq(Long id);

    int deleteAll();
}
