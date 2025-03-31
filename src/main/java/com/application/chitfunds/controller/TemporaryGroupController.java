package com.application.chitfunds.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.repository.TemporaryGroupRepo;
import com.application.chitfunds.service.TemporaryGroupService;

@CrossOrigin("*")
@RestController
@RequestMapping("temporaryGroup")
public class TemporaryGroupController {

    @Autowired
    TemporaryGroupService service;

    @Autowired
    TemporaryGroupRepo repo;

    @GetMapping("/getCount")
    public Long getTemporaryGroupCount() {
        return repo.count();
    }

   
}
