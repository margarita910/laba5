package commands;

import com.company.InvalidScriptException;
import com.company.Receiver;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Command_ExecuteScriptFile extends AbstractCommand implements Command {
    String Path;

    public Command_ExecuteScriptFile(String Path){
        this.Path = Path;
    }

    public Command_ExecuteScriptFile(){
    }

    @Override
    public void execute() throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, IllegalArgumentException, InvalidScriptException {
        ArrayList<Command> commands = Receiver.readCommandFromFile(this.Path);
        if (commands != null){
            for (Command command : commands){
                if (command != null)
                    command.execute();
            }
        }
    }

    @Override
    public String toString(){
        return "ExecuteScriptFile <file_name>: исполняет скрипт.";
    }
}