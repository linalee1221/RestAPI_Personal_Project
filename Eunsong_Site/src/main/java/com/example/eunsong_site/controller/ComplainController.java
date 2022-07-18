package com.example.eunsong_site.controller;

import com.example.eunsong_site.model.Complain;
import com.example.eunsong_site.paging.Criteria;
import com.example.eunsong_site.service.ComplainServiceImpl;
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
public class ComplainController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ComplainServiceImpl complainService;

    @GetMapping("/complains")
    public ResponseEntity<Map<String,Object>>
    getAllComplainsPage(Criteria criteria) {

        logger.info("criteria(vue에서 전송됨) {}", criteria);

        List<Complain> complains
                = complainService.findByTitleContaining(criteria);
        logger.info("complains {}", complains);

        try {
            if(complains.isEmpty()) {
                // Vue에 내용물이 없다고 전송함
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("실행 후 : criteria {} ", criteria);

            Map<String, Object> response = new HashMap<>();
            response.put("complains", complains);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/complains")
    public ResponseEntity<Object>
    createComplain(@RequestBody Complain complain) {
        logger.info("complain###매개변수 {} ", complain);

        Complain savedComplain = complainService.save(complain).get();

        try {
            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/complains/{id}")
    public ResponseEntity<Object>
    getComplainById(@PathVariable("id") Long id) {

        Complain complain = complainService.findById(id).get();

        try {
            if (complain != null) {

                return new ResponseEntity<Object>(complain, HttpStatus.OK);
            } else {

                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/complains/{id}")
    public ResponseEntity<Object> updateComplain(
            @PathVariable("id") Long id,
            @RequestBody Complain complain
    ) {
        try {
            complain.setId(id);

            Complain savedComplain = complainService.save(complain).get();

            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/complains/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteComplain(
            @PathVariable("id") Long id
    ) {
        try {
            complainService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}