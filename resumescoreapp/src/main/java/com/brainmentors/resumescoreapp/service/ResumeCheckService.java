package com.brainmentors.resumescoreapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brainmentors.resumescoreapp.models.ResumeCheck;
import com.brainmentors.resumescoreapp.repo.ResumeCheckRepository;

@Service
public class ResumeCheckService {
    private ResumeCheckRepository resumeCheckRepository;

    // Constructor Injection
    ResumeCheckService(ResumeCheckRepository resumeCheckRepository) {
        this.resumeCheckRepository = resumeCheckRepository;
    }

    public String saveResume(ResumeCheck resumeCheck) {
        try {
            resumeCheckRepository.save(resumeCheck);
            return "Resume Save...";
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return "Fail to Save Resume";
        }
    }

    public ResumeCheck getScore(Long id) {
        System.out.println("Service id " + id);
        // Compute the Score of the candidate
        Optional<ResumeCheck> opt = resumeCheckRepository.findById(id);
        if (opt.isEmpty()) {
            System.out.println("Id not found ");
            return null;
        }

        ResumeCheck resumeCheck = opt.get();
        String jdText = resumeCheck.getJdText();
        String resumeText = resumeCheck.getResumeText();
        resumeText = resumeText.toLowerCase();
        String words[] = jdText.split(",");
        int score = 0;
        String missingWords = "";
        for (String word : words) {
            if (resumeText.contains(word.toLowerCase())) {
                score++;
            } else {
                missingWords = missingWords + word + " ";
            }
        }

        int finalScore = (score * 100) / words.length;
        if (finalScore >= 70) {
            resumeCheck.setShortListStatus("S");
        } else {
            resumeCheck.setShortListStatus("R");
        }
        System.out.println("Score is " + finalScore + " Missing Words are " + missingWords);
        resumeCheck.setScore(finalScore);
        resumeCheck.setMissingWords(missingWords);
        resumeCheckRepository.save(resumeCheck);
        return resumeCheck;

    }
}
