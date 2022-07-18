package com.example.eunsong_site.service;

import com.example.eunsong_site.dao.FreeDao;
import com.example.eunsong_site.model.Free;
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
public class FreeServiceImpl implements FreeService {
    @Autowired
    private FreeDao freeDao;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Optional<Free> findById(long id) {
        return freeDao.findById(id);
    }
    @Override
    public List<Free> findAll(Criteria criteria) {
        return freeDao.findAll(criteria);
    }
    @Override
    public List<Free> findByTitleContaining(Criteria criteria) {
        List<Free> frees = Collections.emptyList();


        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());

        int totalCount = freeDao.selectTotalCount(optionalCriteria.orElse(""));

        criteria.setTotalItems(totalCount);

        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getTitle() == null) {

            frees = freeDao.findAll(criteria);
        }
            // email이 있으면 email 검색(여러 건 -> 리스트에 저장)
        else {
            frees = freeDao.findByTitleContaining(criteria);
        }

        return frees;
    }

    // 회원 생성 / 수정 서비스(insert/update)
    @Override
    public Optional<Free> save(Free free) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

        // 매개변수 customer 정보 출력
        logger.info("free {} : ", free);

        // customer.getId() 없으면 insert 문 호출(새로운 회원 생성)
        if (free.getId() == null) {
            freeDao.createFree(free);
        }
        // customer.getId() 있으면 update 문 호출
        else {
            freeDao.updateFree(free);
        }

        // insert 문 후에는 customer의 id 속성에 값이 저장됨(<selectkey>)
        seqId = free.getId();
        logger.info("seqId {} : ", seqId);

        return freeDao.findById(seqId);
    }

    // delete / update / insert 문은 결과 실행 후,
    // 정상 실행되면 (삭제/수정/생성) 건수가 표시됨
    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = freeDao.deleteFree(id);
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = freeDao.deleteAll();
        return (queryResult >= 1) ? true : false;
    }
}

