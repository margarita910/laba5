package com.company;

/**
 Type objects <Ticket>.
 */

public enum TicketType {
    VIP ("VIP"),
    USUAL ("USUAL"),
    CHEAP ("CHEAP");
    private String title;
    TicketType(String title){
        this.title = title;
    }
    @Override
    public String toString(){
        return title;
    }
}