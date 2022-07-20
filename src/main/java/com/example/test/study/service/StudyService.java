package com.example.test.study.service;

import com.example.test.parent.Parent;
import com.example.test.parent.ParentService;
import com.example.test.study.domain.Study;
import com.example.test.study.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudyService {
    private final ParentService parentService;
    private final StudyRepository studyRepository;

    public StudyService(ParentService parentService, StudyRepository studyRepository) {
        this.parentService = parentService;
        this.studyRepository = studyRepository;
    }

    public Study createStudy(Long memberId, Study study){
        Optional<Parent> parent = parentService.findById(memberId);
        study.setOwner(parent.orElseThrow(()->new IllegalArgumentException("Member not exist")));
        return studyRepository.save(study);
    }
}
