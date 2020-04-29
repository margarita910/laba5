package commands;

import com.company.Receiver;

/**
 Команда, отсортировывающая коллекцию в порядке, обратном данному.
 */

public class Command_Reorder extends AbstractCommand implements Command{
    public Command_Reorder(){
    }

    @Override
    public void execute(){
        Receiver.reorder(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "Reorder: отсортировывает коллекцию в порядке, обратном нынешнему.";
    }
}
