package com.company;

/**
 * Colors used in class Person.
 */

public enum Color {
    GREEN ("GREEN"),
    BLUE ("BLUE"),
    YELLOW ("YELLOW"),
    BROWN ("BROWN"),
    RED ("RED"),
    WHITE ("WHITE");
    private String title;
    Color(String title){
        this.title = title;
    }
    @Override
    public String toString(){
        return title;
    }
}
