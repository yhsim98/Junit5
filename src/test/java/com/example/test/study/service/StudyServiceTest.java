package com.example.test.study.service;

import com.example.test.parent.Parent;
import com.example.test.parent.ParentService;
import com.example.test.study.domain.Study;
import com.example.test.study.repository.StudyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    ParentService parentService;
    // MockitoExtension 이 있어야 동작한다

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService(){
        StudyService studyService = new StudyService(parentService, studyRepository);
        assertNotNull(studyService);
    }

    @Test
    @DisplayName("mock stubbing")
    void createStudyService2(@Mock ParentService parentService2, @Mock StudyRepository studyRepository2){
        StudyService studyService = new StudyService(parentService2, studyRepository2);
        Parent parent = new Parent();
        Study study = new Study(5);

        when(studyRepository2.save(any())).thenReturn(new Study(5));
        when(parentService2.findById(1L)).thenReturn(Optional.of(parent));
        // 해당 값을 파라미터로 받으면 설정된 리턴값을 리턴

        Study createdStudy = studyService.createStudy(1L, study);

        assertEquals(study.getLimit(), createdStudy.getLimit());

        doThrow(new IllegalArgumentException()).when(parentService2).validate(1L);

        assertThrows(IllegalArgumentException.class, ()->{
            parentService2.validate(1L);
        });
    }

    @Test
    @DisplayName("mock 객체 확인")
    void mockNotify(){
        Parent parent = new Parent();
        when(parentService.findById(1L)).thenReturn(Optional.of(parent));

        verify(parentService, times(1)).validate(any());
    }

    @Test
    @DisplayName("BDD Mockito")
    void bddMockitoTest(){
        // given
        Parent parent = new Parent();
        given(parentService.findById(any())).willReturn(Optional.of(parent));

        // when
        Parent returnParent = parentService.findById(1L).get();

        // then
        assertEquals(parent.getStr1(), returnParent.getStr1());
    }
}
