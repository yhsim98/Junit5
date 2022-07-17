package com.example.test.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parent {
    private String str1 = "1";
    public String str2 = "2";

    private void method1(){
        System.out.println("method1");
    }

    public void method2(int n){
        System.out.println("method2: " + n);
    }

    private void method3(){
        System.out.println("method3");
    }
}
