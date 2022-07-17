package com.example.test.parent;

import com.example.test.child.Child;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ParentTest {

    @Test
    public void 테스트() throws NoSuchMethodException {
        Class child = Child.class;
        Constructor constructor = child.getDeclaredConstructor();
        System.out.println("Class name: " + constructor.getName());
        assertNotNull(child);
    }

    @BeforeAll
    static void BeforeAll(){}

    @AfterAll
    static void afterAll(){}

    @BeforeEach
    public void before(){}

    @AfterEach
    public void after(){}
}
