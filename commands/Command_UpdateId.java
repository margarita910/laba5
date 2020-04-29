package commands;

import com.company.Receiver;
import com.company.Ticket;

/**
 Команда, которая заменяет элемент по ID.
 */

public class Command_UpdateId extends AbstractCommand implements Command{
    private Ticket ticket;
    private int value;

    public Command_UpdateId(int value, Ticket ticket){
        this.value = value;
        this.ticket = ticket;
    }

    public Command_UpdateId(){
    }

    @Override
    public void execute(){
        Receiver.update(AbstractCommand.getCollection(), this.value, this.ticket);
    }

    @Override
    public String toString(){
        return "UpdateId <int Id>: обновляет значение элемента коллекции, ID которого равно заданному.";
    }
}
