package com.example.eunsong_site.controller;

import com.example.eunsong_site.model.Free;
import com.example.eunsong_site.paging.Criteria;
import com.example.eunsong_site.service.FreeServiceImpl;
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
public class FreeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FreeServiceImpl freeService;

    @GetMapping("/frees")
    public ResponseEntity<Map<String,Object>>
    getAllFreePage(Criteria criteria) {

        logger.info("criteria(vue에서 전송됨) {}", criteria);

        List<Free> frees
                = freeService.findByTitleContaining(criteria);
        logger.info("frees {}", frees);

        try {
            if(frees.isEmpty()) {
                // Vue에 내용물이 없다고 전송함
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("실행 후 : criteria {} ", criteria);

            Map<String, Object> response = new HashMap<>();
            response.put("frees", frees);
            response.put("currentPage", criteria.getPage());
            response.put("totalItems", criteria.getTotalItems());
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/frees")
    public ResponseEntity<Object>
    insertFree(@RequestBody Free free) {
        logger.info("free###매개변수 {} ", free);

        Free savedFree = freeService.save(free).get();

        try {
            return new ResponseEntity<Object>(savedFree, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/frees/{id}")
    public ResponseEntity<Object>
    getFreeById(@PathVariable("id") Long id) {

        Free free = freeService.findById(id).get();

        try {
            if (free != null) {

                return new ResponseEntity<Object>(free, HttpStatus.OK);
            } else {

                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/frees/{id}")
    public ResponseEntity<Object> updateFree(
            @PathVariable("id") Long id,
            @RequestBody Free free
    ) {
        try {
            free.setId(id);

            Free savedFree = freeService.save(free).get();

            return new ResponseEntity<Object>(savedFree, HttpStatus.OK);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/frees/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteFree(
            @PathVariable("id") Long id
    ) {
        try {
            freeService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}