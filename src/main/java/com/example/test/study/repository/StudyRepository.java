package com.example.test.study.repository;

import com.example.test.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

public interface StudyRepository extends JpaRepository<Study, JpaRepositoryFactory> {
}
