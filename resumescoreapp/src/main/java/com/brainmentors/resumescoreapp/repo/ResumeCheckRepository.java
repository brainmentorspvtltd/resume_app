package com.brainmentors.resumescoreapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brainmentors.resumescoreapp.models.ResumeCheck;

public interface ResumeCheckRepository extends JpaRepository<ResumeCheck, Long> {

}
