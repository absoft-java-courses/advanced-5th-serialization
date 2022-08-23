package org.cource.advanced.serialization.beans;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String name;
    private int age;

    static {
        System.out.println("Hello from this static block");
    }

    {
        System.out.println("Hello from this block");
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;

        System.out.println("Hello from Person's constructor");
    }

    public String getName() {
        System.out.println("Calling getFullName");
        return name;
    }

    public int getAge() {
        System.out.println("Calling getAge");
        return age;
    }
}
