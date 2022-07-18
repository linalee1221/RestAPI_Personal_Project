package com.example.eunsong_site.service;

import com.example.eunsong_site.model.Faq;
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
public interface FaqService {
    Optional<Faq> findById(long id);
    List<Faq> findAll(Criteria criteria);
    List<Faq> findByTitleContaining(Criteria criteria);
    Optional<Faq> save(Faq faq);
    boolean deleteById(Long id);
    boolean deleteAll();
}
