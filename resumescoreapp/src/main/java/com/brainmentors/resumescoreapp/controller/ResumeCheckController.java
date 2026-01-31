package com.brainmentors.resumescoreapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brainmentors.resumescoreapp.models.ResumeCheck;
import com.brainmentors.resumescoreapp.service.ResumeCheckService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/resumeengine")
@CrossOrigin(origins = "*")
public class ResumeCheckController {
    private ResumeCheckService resumeCheckService;

    public ResumeCheckController(ResumeCheckService resumeCheckService) {
        this.resumeCheckService = resumeCheckService;
    }

    @PostMapping("/save-resume")
    public ResponseEntity<String> saveResume(@RequestBody ResumeCheck resumeCheck) {
        String result = resumeCheckService.saveResume(resumeCheck);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/compute-score/{id}")
    public ResponseEntity<ResumeCheck> computeScore(@PathVariable("id") Long id) {
        System.out.println("Controller Id is " + id);
        ResumeCheck resumeCheck = resumeCheckService.getScore(id);
        return ResponseEntity.status(HttpStatus.OK).body(resumeCheck);
    }
}
