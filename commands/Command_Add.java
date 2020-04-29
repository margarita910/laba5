package commands;

import com.company.Receiver;
import com.company.Ticket;

/**
 Команда, добавляющая элемент в коллекцию.
 */

public class Command_Add extends AbstractCommand implements Command{
    private Ticket ticket;

    public Command_Add(Ticket ticket){
        this.ticket = ticket;
    }

    public Command_Add(){
    }

    @Override
    public void execute(){
        Receiver.add(AbstractCommand.getCollection(), this.ticket);
    }

    @Override
    public String toString(){
        return "Add: добавляет элемент класса Ticket в коллекцию.";
    }
}

