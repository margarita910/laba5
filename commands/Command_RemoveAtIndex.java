package commands;

import com.company.Receiver;

/**
 Команда, которая удаляет элемент, находящийся в заданной позиции.
 */

public class Command_RemoveAtIndex extends Command_Remove {
    int index;

    public Command_RemoveAtIndex(int index){
        this.index = index;
    }

    public Command_RemoveAtIndex(){
    }

    @Override
    public void execute(){
        Receiver.removeAtIndex(AbstractCommand.getCollection(), this.index);
    }

    @Override
    public String toString(){
        return "RemoveAtIndex <int index>: удаляет элемент, находящийся в заданной позиции.";
    }
}

