package com.foxlora.reflection;


import java.lang.reflect.InvocationTargetException;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {


        A a = new A();
        System.out.println(A.m);


        Class c1 = Class.forName("com.foxlora.reflection.A");
        System.out.println(c1);

        System.out.println("-----------------------------");
        c1.getDeclaredConstructor().newInstance();
    }
}


class A {

    static int m = 100;

    static {
        System.out.println("A静态代码初始化");
        m = 300;
    }


    public A() {
        System.out.println("A类无参构造初始化");
    }
}
