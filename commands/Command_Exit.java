package commands;

import com.company.Receiver;

/**
 Команда, осуществляющая выход из программы.
 */

public class Command_Exit extends AbstractCommand implements Command{
    public Command_Exit(){
    }

    @Override
    public void execute(){
        Receiver.exit();
    }

    @Override
    public String toString(){
        return "Exit: осуществляет выход из программы без сохранения изменений.";
    }
}
