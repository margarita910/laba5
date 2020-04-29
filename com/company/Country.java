package com.company;

/**
 * Countries used in class Person.
 */

public enum Country {
    UNITEDKINGDOM ("UNITEDKINGDOM"),
    THAILAND ("THAILAND"),
    NORTHKOREA("NORTHKOREA");
    private String title;
    Country(String title){
        this.title = title;
    }
    @Override
    public String toString(){
        return title;
    }
}

