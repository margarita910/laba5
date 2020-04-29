package commands;

import com.company.Receiver;

import java.io.IOException;

/**
 Команда, которая сохраняет коллекцию в файл.
 */

public class Command_Save extends AbstractCommand implements Command{

    public Command_Save(){
    }

    @Override
    public void execute() throws IOException {
        Receiver.save(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "Save: сохраняет коллекцию в файл.";
    }
}
