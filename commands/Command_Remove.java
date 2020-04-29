package commands;

import java.io.IOException;

public class Command_Remove extends AbstractCommand implements Command {
    public Command_Remove(){
    }

    @Override
    public void execute() throws IOException{
    }

    @Override
    public String toString(){
        return "Remove";
    }
}
