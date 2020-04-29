package commands;

import com.company.Receiver;
import com.company.Ticket;

/**
 Команда, которая добавляет элемент в коллекцию, если его значение больше максимального.
 */

public class Command_AddIfMax extends AbstractCommand implements Command{
    private Ticket ticket;

    public Command_AddIfMax(Ticket ticket){
        this.ticket = ticket;
    }

    public Command_AddIfMax(){
    }

    @Override
    public void execute(){
        Receiver.addIfMax(AbstractCommand.getCollection(), this.ticket);
    }

    @Override
    public String toString(){
        return "AddIfMax: добавляет элемент в коллекцию, если значение его поля coordinates (или значение поля price) больше максимального.";
    }

}
