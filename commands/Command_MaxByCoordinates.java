package commands;

import com.company.Receiver;

/**
 * Команда, которая выводит элемент коллекции, значение поля coordinates которого является максимальным.
 */

public class Command_MaxByCoordinates extends AbstractCommand implements Command {
    public Command_MaxByCoordinates(){
    }

    @Override
    public void execute(){
        Receiver.maxByCoordinates(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "MaxByCoordinates: выводит элемент коллекции, значение поля coordinates которого является максимальным.";
    }
}
