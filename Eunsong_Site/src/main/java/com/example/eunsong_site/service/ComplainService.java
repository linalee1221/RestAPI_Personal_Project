package com.example.eunsong_site.service;

import com.example.eunsong_site.model.Complain;
import com.example.eunsong_site.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : Eunsong LEE
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07    Eunsong LEE        최초 생성
 */
public interface ComplainService {
    Optional<Complain> findById(long id);
    List<Complain> findAll(Criteria criteria);
    List<Complain> findByTitleContaining(Criteria criteria);
    Optional<Complain> save(Complain complain);
    boolean deleteById(Long id);
    boolean deleteAll();
}
