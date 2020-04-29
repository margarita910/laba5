package commands;

import com.company.Receiver;

public class Command_Info extends AbstractCommand implements Command{
    public Command_Info(){
    }

    @Override
    public void execute(){
        Receiver.info(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "Info: выводит информацию о коллекции.";
    }
}
