package commands;

import com.company.Receiver;

public class Command_PrintDescendingPrice extends AbstractCommand implements Command {
    public Command_PrintDescendingPrice(){
    }

    @Override
    public void execute(){
        Receiver.printFieldDescendingPrice(AbstractCommand.getCollection());
    }

    @Override
    public String toString(){
        return "PrintDescendingPrice: выводит знаения поля price всех элементов в порядке убывания.";
    }
}
