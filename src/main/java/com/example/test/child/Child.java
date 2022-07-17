package com.example.test.child;

import com.example.test.parent.Parent;

public class Child extends Parent {
    public String cstr1 = "1";
    private String cstr2 = "2";

    public Child() {
    }

    private Child(String cstr1) {
        this.cstr1 = cstr1;
    }

    public int method4(int n){
        System.out.println("method4: " + n);
        return n;
    }

    private int method5(int n){
        System.out.println("method5: " + n);
        return n;
    }
}

