package com.example.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TestApplication {

    ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        //System.out.println(singleton.getInstance());

        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++){
            service.submit(()->{
                System.out.println(DoubleTon.getInstance());
            });
        }

        service.shutdown();
        //SpringApplication.run(TestApplication.class, args);
    }

}

class Singleton {
    private Singleton() {
        System.out.println("singleton init");
    }

    public static Singleton getInstance(){
        return singletonFactory.unique;
    }

    private static class singletonFactory{
        private static Singleton unique = new Singleton();
    }
}

class DoubleTon{
    static DoubleTon doubleTon;

    private DoubleTon() {
        System.out.println("doubleTon init");
    }

    public static DoubleTon getInstance(){
        if(doubleTon == null) doubleTon = new DoubleTon();
        return doubleTon;
    }
}
