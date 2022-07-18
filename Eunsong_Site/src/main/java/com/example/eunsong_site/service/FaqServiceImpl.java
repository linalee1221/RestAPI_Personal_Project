package com.example.eunsong_site.service;

import com.example.eunsong_site.dao.FaqDao;
import com.example.eunsong_site.model.Faq;
import com.example.eunsong_site.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : Eunsong LEE
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07    Eunsong LEE        최초 생성
 */
@Service
public class FaqServiceImpl implements FaqService {
    @Autowired
    private FaqDao faqDao;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Optional<Faq> findById(long id) {
        return faqDao.findById(id);
    }
    @Override
    public List<Faq> findAll(Criteria criteria) {
        return faqDao.findAll(criteria);
    }
    @Override
    public List<Faq> findByTitleContaining(Criteria criteria) {
        List<Faq> faqs = Collections.emptyList();


        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());

        int totalCount = faqDao.selectTotalCount(optionalCriteria.orElse(""));

        criteria.setTotalItems(totalCount);

        criteria.setTotalPages(totalCount / criteria.getSize());

        if (criteria.getTitle() == null) {

            faqs = faqDao.findAll(criteria);
        } else {
            faqs = faqDao.findByTitleContaining(criteria);
        }

        return faqs;
    }

    // 회원 생성 / 수정 서비스(insert/update)
    @Override
    public Optional<Faq> save(Faq faq) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

        // 매개변수 customer 정보 출력
        logger.info("faq {} : ", faq);

        // customer.getId() 없으면 insert 문 호출(새로운 회원 생성)
        if (faq.getId() == null) {
            faqDao.insertFaq(faq);
        }
        // customer.getId() 있으면 update 문 호출
        else {
            faqDao.updateFaq(faq);
        }

        // insert 문 후에는 customer의 id 속성에 값이 저장됨(<selectkey>)
        seqId = faq.getId();
        logger.info("seqId {} : ", seqId);

        return faqDao.findById(seqId);
    }

    // delete / update / insert 문은 결과 실행 후,
    // 정상 실행되면 (삭제/수정/생성) 건수가 표시됨
    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = faqDao.deleteFaq(id);
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = faqDao.deleteAll();
        return (queryResult >= 1) ? true : false;
    }
}

