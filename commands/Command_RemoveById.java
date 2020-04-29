package commands;

import com.company.Receiver;

/**
 Команда, которая удаляет элемент по его Id.
 */

public class Command_RemoveById extends Command_Remove{
    int value;
    public Command_RemoveById(int value){
        this.value = value;
    }

    public Command_RemoveById(){
    }

    @Override
    public void execute(){
        Receiver.removeById(AbstractCommand.getCollection(), this.value);
    }

    @Override
    public String toString(){
        return "RemoveById <int Id>: удаляет элемент из коллекции по его Id.";
    }
}
