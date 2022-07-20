package com.example.test.parent;

import java.util.Optional;

public interface ParentService {
    Optional<Parent> findById(Long id);
    boolean validate(Long id);
}
