package com.example.eunsong_site.controller;

import com.example.eunsong_site.model.Faq;
import com.example.eunsong_site.paging.Criteria;
import com.example.eunsong_site.service.FaqServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : Eunsong LEE
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07    Eunsong LEE        최초 생성
 */
@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/api")
public class FaqController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FaqServiceImpl faqService;

    @GetMapping("/faqs")
    public ResponseEntity<Map<String,Object>>
    getAllFaqPage(Criteria criteria) {

        logger.info("criteria(vue에서 전송됨) {}", criteria);

        List<Faq> faqs
                = faqService.findByTitleContaining(criteria);
        logger.info("fqas {}", faqs);

        try {
            if(faqs.isEmpty()) {
                // Vue에 내용물이 없다고 전송함
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("실행 후 : criteria {} ", criteria);

            Map<String, Object> response = new HashMap<>();
            response.put("faqs", faqs);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/faqs")
    public ResponseEntity<Object>
    insertFaq(@RequestBody Faq faq) {
        logger.info("faq {} ", faq);

        Faq savedFaq = faqService.save(faq).get();

        try {
            return new ResponseEntity<Object>(savedFaq, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/faqs/{id}")
    public ResponseEntity<Object>
    getFaqById(@PathVariable("id") Long id) {

        Faq faq = faqService.findById(id).get();

        try {
            if (faq != null) {

                return new ResponseEntity<Object>(faq, HttpStatus.OK);
            } else {

                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/faqs/{id}")
    public ResponseEntity<Object> updateFaq(
            @PathVariable("id") Long id,
            @RequestBody Faq faq
    ) {
        try {
            faq.setId(id);

            Faq savedFaq = faqService.save(faq).get();

            return new ResponseEntity<Object>(savedFaq, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/faqs/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteFaq(
            @PathVariable("id") Long id
    ) {
        try {
            faqService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}