package commands;
import com.company.Receiver;

/**
 Команда, показывающая все доступные комманды.
 */

public class Command_Help extends AbstractCommand implements Command{
    public Command_Help(){
    }

    @Override
    public void execute(){
        Receiver.help();
    }

    @Override
    public String toString(){
        return "Help: показывает все доступные комманды.";
    }
}
