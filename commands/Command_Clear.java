package commands;

import com.company.Receiver;

/**
 Команда, которая очищает коллекцию.
 */

public class Command_Clear extends AbstractCommand implements Command{
    public Command_Clear(){
    }

    @Override
    public void execute(){
        Receiver.clear(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "Clear: очищает коллекцию.";
    }
}
