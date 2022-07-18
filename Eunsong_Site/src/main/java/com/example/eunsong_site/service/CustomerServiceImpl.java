package com.example.eunsong_site.service;

import com.example.eunsong_site.dao.CustomerDao;
import com.example.eunsong_site.model.Customer;
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
@Service // 스프링에 객체 생성

public class CustomerServiceImpl implements CustomerService {
    // 스프링에 customerDao가 이미 생성되어 있다
    // customerDao에 @Mapper를 걸어놓았기 때문에 이미 객체는 생성되어 있음
    @Autowired // 생성된 객체를 받아오는 어노테이션
    private CustomerDao customerDao; // 객체 정의(null), @Autowired를 걸었기 때문에 null -> 스프링객체

    // 나중에 에러 로그 추적을 위해 생성
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // id로 회원을 조회하는 메소드(결과 : 1건)
    @Override
    public Optional<Customer> findById(long id) {
        return customerDao.findById(id);
    }

    // 전체 회원을 조회하는 메소드(결과 : 여러건)
    // Todo : findAll() -> findAll(Criteria criteria) 수정
    @Override
    public List<Customer> findAll(Criteria criteria) {
        return customerDao.findAll(criteria);
    }

    @Override
    public List<Customer> findByEmailContaining(Criteria criteria) {
        List<Customer> customers = Collections.emptyList();

        // null 체크 : 이메일이 null인지 체크함함
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getEmail());

        // 테이블의 총 건수를 조회하는 메소드 호출
        // email이 null이면 ""로 바뀜
        int totalCount = customerDao.selectTotalCount(optionalCriteria.orElse(""));

        // Criteria 객체의 변수에 페이지 정보(totalCount, totalPages) 를 저장함
        criteria.setTotalItems(totalCount); // 테이블 총 건수 저장

        // totalPages(총 페이지 수) = totalCount(총 건수) / size(페이지 당 총 건수)
        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getEmail() == null) {
            // email이 없으면 전체 검색(여러 건 -> 리스트에 저장)
            customers = customerDao.findAll(criteria);
        }
            // email이 있으면 email 검색(여러 건 -> 리스트에 저장)
        else {
            customers = customerDao.findByEmailContaining(criteria);
        }

        return customers;
    }

    // 회원 생성 / 수정 서비스(insert/update)
    @Override
    public Optional<Customer> save(Customer customer) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

        // 매개변수 customer 정보 출력
        logger.info("customer {} : ", customer);

        // customer.getId() 없으면 insert 문 호출(새로운 회원 생성)
        if (customer.getId() == null) {
            customerDao.insertCustomer(customer);
        }
        // customer.getId() 있으면 update 문 호출
        else {
            customerDao.updateCustomer(customer);
        }

        // insert 문 후에는 customer의 id 속성에 값이 저장됨(<selectkey>)
        seqId = customer.getId();
        logger.info("seqId {} : ", seqId);

        return customerDao.findById(seqId);
    }

    // delete / update / insert 문은 결과 실행 후,
    // 정상 실행되면 (삭제/수정/생성) 건수가 표시됨
    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = customerDao.deleteCustomer(id);
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = customerDao.deleteAll();
        return (queryResult >= 1) ? true : false;
    }
}

