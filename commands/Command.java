package commands;

import com.company.InvalidScriptException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface Command {
    ArrayList<Command> commands = new ArrayList<Command>();

    void execute() throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, InvalidScriptException;

    default void add(Command command){
        commands.add(command);
    }
}
