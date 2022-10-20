package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="University info from https://github.com/Hipo/university-domains-list")
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UniversityQueryService universityQueryService;

    @ApiOperation(value = "Get a list of University names")
    @GetMapping("/get")
    public ResponseEntity<String> getSubreddits() throws JsonProcessingException {
        String result = universityQueryService.getJSON();
        return ResponseEntity.ok().body(result);
    }

}