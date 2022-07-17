package com.example.test.study.domain;

import com.example.test.study.enums.StudyStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    @Test
    @Order(1)
    void create_new_study(){
        // given
        Study study = new Study(10);
        System.out.println(study.getClass().getClassLoader());
        // when

        // then
        assertAll(
            () -> assertNotNull(study),
            () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 "+ StudyStatus.DRAFT + "여야 한다"),
                    ()->assertTrue(study.getLimit() > 0, ()->"스터디 최대 참석 인원은 0보다 커야 한다")
        );
        // 모든 테스트를 한번에 확인 가능
        
        // 람다식으로 한다면, 메시지 생성을 간편하게 할 수 있을뿐만 아니라
        // 연산을 필요한 시점에 함으로써 성능적으로 이점이 있다
        // 단순 문자열로 하면 문자열 연산을 무조건 수행한다
    }

    @Test
    @Order(2)
    @DisplayName("예외 발생 테스트")
    public void throwExceptionTest(){
        // given

        // when

        // then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->new Study(-10));
        assertEquals("1이상이어야 함", ex.getMessage());
        // 코드 실행 시 예외가 발생하는지 테스트할 수 있다
    }

    @Test
    @Order(3)
    @DisplayName("time out test")
    public void timeOutTest(){
        assertTimeout(Duration.ofMillis(10), ()->{
            new Study((10));
            //Thread.sleep(300);
        });
        // 괄호 안의 실행할 내용들이 설장한 시간을 넘으면 실패
        // 시간 넘어도 일단 다 실행한다
        // 쓰레드 safe 하다
    }

    @Test
    @Order(4)
    @DisplayName("time out시 즉시 종료 테스트")
    public void timeOutPreemptivelyTest(){
        assertTimeoutPreemptively(Duration.ofMillis(10), ()->{
            new Study((10));
            //Thread.sleep(300);
        });
        // 만약 설정한 시간을 초과하면 즉시 종료
        // 쓰레드 safe 하지 않다
        // 내부 실행에서 threadLocal을 건드리는 경우 문제가 발생할 수 있다
        // ex) spring transaction
    }

    @Test
    @DisplayName("private field를 직접 수정해보는 테스트")
    public void privateFieldAccessTest() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // given
        Study study = new Study(10);
        Class<?> studyClass = study.getClass();
        Field fields = studyClass.getDeclaredField("limit");

        // when
        fields.setAccessible(true);
        fields.set(study, 5);

        // then
        assertEquals(5, fields.get(study), ()->"private field 접근 실패");
    }

    @Test
    @DisplayName("private method에 접근해보는 테스트")
    public void privateMethodAccessTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // given
        Study study = new Study(5);
        Method method = study.getClass().getDeclaredMethod("isFull", int.class);
        method.setAccessible(true);

        // when
        boolean isFull = (boolean) method.invoke(study, 5);
        boolean isNotFull = (boolean)  method.invoke(study, 3);

        // then
        assertTrue(isFull, "인원 다 찼는데 안잡힘");
        assertFalse(isNotFull, "인원 다 안찼는데 잡힘");
    }

    @Test
    @DisplayName("TestInfo 테스트")
    void testParameterResolver(TestInfo testInfo){
        try {
            Class studyTest = Class.forName("com.example.test.study.domain.StudyTest");
            Method method = studyTest.getMethod("testParameterResolver", TestInfo.class);
            assertEquals(method, testInfo.getTestMethod().get());

        } catch (ClassNotFoundException e){

        } catch (NoSuchMethodException e){

        }
    }

    static int a = 0;
    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}")
    @DisplayName("repeat test")
    void repeatTest(RepetitionInfo repetitionInfo){
        a += 1;
        assertTrue(repetitionInfo.getTotalRepetitions() == 3);
        assertEquals(a, repetitionInfo.getCurrentRepetition());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    @DisplayName("parameter test")
    void parameterTest(String s){
        System.out.println(s);
    }
}