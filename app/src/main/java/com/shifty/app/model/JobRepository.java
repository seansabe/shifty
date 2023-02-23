package com.shifty.app.model;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {



}
