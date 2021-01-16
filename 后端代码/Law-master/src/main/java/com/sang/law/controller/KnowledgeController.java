package com.sang.law.controller;

import com.sang.law.service.KnowledegService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledegService knowledegService;

    @RequestMapping("/getAllKnowledge")
    public String getAllKnowledge() {
        return new JSONArray(knowledegService.getAllKnowledge()).toString();
    }

}
