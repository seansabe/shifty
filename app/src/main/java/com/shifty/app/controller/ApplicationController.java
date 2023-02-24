package com.shifty.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shifty.app.model.Job;
import com.shifty.app.model.JobRepository;
import com.shifty.app.model.Application;
import com.shifty.app.model.ApplicationRepository;

@CrossOrigin(origins = "hhtp://localhost:8081")
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationRepository appRepo;

    @Autowired
    private JobRepository jobRepo;

}
