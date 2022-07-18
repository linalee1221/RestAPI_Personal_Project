package com.example.eunsong_site.service;

import com.example.eunsong_site.model.Free;
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
public interface FreeService {
    Optional<Free> findById(long id);
    List<Free> findAll(Criteria criteria);
    List<Free> findByTitleContaining(Criteria criteria);
    Optional<Free> save(Free free);
    boolean deleteById(Long id);
    boolean deleteAll();
}
