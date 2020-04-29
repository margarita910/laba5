package com.company;

import commands.Command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 A class that starts any command.
 */
public class Invoker {
    private Command command;

    public Invoker(){
    }

    public void setCommand(Command command) throws NullPointerException{
        this.command = command;
    }

    public void executeCommand() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IllegalArgumentException, NullPointerException, InvalidScriptException{
        this.command.execute();
    }

    public Command getCommand(){
        return command;
    }
}

