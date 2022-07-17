package com.example.test.study.domain;

import com.example.test.study.enums.StudyStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Study {
    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;

    public Study(int limit) {
        if(limit <= 0){
            throw new IllegalArgumentException("1이상이어야 함");
        }
        this.limit = limit;
    }

    private boolean isFull(int joinPeopleNum){
        return joinPeopleNum >= limit;
    }
}
