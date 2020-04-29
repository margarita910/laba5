package commands;

import com.company.Receiver;

/**
 Команда, выводящая все элементы коллекции
 */

public class Command_Show extends AbstractCommand implements Command{
    public Command_Show(){
    }

    @Override
    public void execute() throws NullPointerException{
        Receiver.show(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "Show: выводит все элементы коллекции";
    }
}