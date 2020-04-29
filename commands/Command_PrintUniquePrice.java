package commands;

import com.company.Receiver;

public class Command_PrintUniquePrice extends AbstractCommand implements Command {
    float price;
    public Command_PrintUniquePrice(float price){
        this.price = price;
    }

    public Command_PrintUniquePrice(){
    }

    @Override
    public void execute(){
        Receiver.printUniquePrice(AbstractCommand.getCollection(), this.price);
    }

    @Override
    public String toString(){
        return "PrintUniquePrice <float price>: выводит уникальные значения поля price всех элементов коллекции.";
    }
}
