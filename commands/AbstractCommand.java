package commands;

import com.company.Ticket;
import java.util.Vector;
import java.util.ArrayList;

public abstract class AbstractCommand {
    private static Vector<Ticket> collection;

    public static void setCollection (Vector<Ticket> collection){
        AbstractCommand.collection = collection;
    }

    public static Vector<Ticket> getCollection(){
        return collection;
    }

    private static ArrayList<Command> commands = new ArrayList<>();

    public static ArrayList<Command> getCommands(){
        return commands;
    }

    public static void addNewCommand (Command... commandsList){
        for (Command command : commandsList){
            commands.add(command);
        }
    }
}
