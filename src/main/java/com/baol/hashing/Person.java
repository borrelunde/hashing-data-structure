package com.baol.hashing;

public class Person {

    private final String name;

    Person(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
