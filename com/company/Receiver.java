package com.company;

import commands.*;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.AccessDeniedException;
import java.util.*;

import com.google.gson.Gson;

import static java.util.Collections.*;

/**
 * Receiver.
 */

public class Receiver {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    static ArrayList<String> scriptNames = new ArrayList<>();
    public static int countExecute;

    public static ArrayList<String> getScriptNames(){
        return scriptNames;
    }

    public static void help(){
        int number = 1;
        for (Command command : AbstractCommand.getCommands()){
            System.out.println("\t"+number+": "+command);
            number++;
        }
    }

    public static void info(Vector<Ticket> collection){
        System.out.println("Коллекция: "+collection.getClass());
        System.out.println("Колличество элементов: "+collection.size());
        if (collection.size() > 0 ) {
            System.out.println("Тип элементов: "+collection.get(0).getClass());
            System.out.println("Коллекция создана: "+collection.get(0).getCreationDate());
        }
        else System.out.println("Вы всегда можете добавить новые элементы в коллекцию =)");
    }

    public static void show (Vector<Ticket> collection) throws NullPointerException{
        if (collection.size() > 0){
            for (Ticket ticket : collection){
                System.out.println(ticket);
            }
        }
        else{
            System.out.println("Коллекция не имеет элементов.");
        }
    }

    public static void add(Vector<Ticket> collection, Ticket ticket){
        collection.add(ticket);
        System.out.println("Ticket добавлен в коллекцию.");
    }

    public static Ticket detect (int value, Vector<Ticket> collection) throws IllegalArgumentException{
        if (value < 0 ) throw new IllegalArgumentException();
        if (collection.size()>0){
            for (Ticket ticket : collection) {
                if (ticket.getId() == value) return ticket;
            }
        }
        else System.out.println("Коллекция не имеет элементов.");
        throw new IllegalArgumentException();
    }

    public static void update(Vector<Ticket> collection, int value, Ticket ticket){
        if (collection.size() > 0){
            try{
                detect(value, collection);
            }
            catch (IllegalArgumentException exp){
                System.out.println("Элемента с таким Id не существует.");
                return;
            }
            collection.set(value - 1, ticket);
            System.out.println("Элемент коллекции с Id = " + value+" обновлен.");
        }
        else System.out.println("Коллекция не имеет элементов. Заменить ticket с Id = "+ (value) + " на созданный вами билет невозможно.");
    }

    public static void removeById(Vector<Ticket> collection, int value){
        if (collection.size() > 0){
            try{
                detect(value, collection);
            }
            catch (IllegalArgumentException exp){
                System.out.println("Элемента с таким Id не существует.");
                return;
            }
            collection.removeIf(ticket -> ticket.getId() == value);
            System.out.println("Элемент с Id = " + value + " удален.");
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    public static void removeAtIndex(Vector<Ticket> collection, int index){
        if (collection.size() > 0){
            if (index < collection.size()){
                collection.remove(index);
                System.out.println("Элемент с индексом = " + index + " удален.");
            }
            else System.out.println("Элемента с таким индексом не существует.");
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    public static void clear(Vector<Ticket> collection){
        if (collection.size() > 0){
            collection.clear();
            System.out.println("Коллекция очищена.");
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    public static void save(Vector<Ticket> collection) throws IOException {
        Gson gson = new Gson();
        FileOutputStream file;
        String json = gson.toJson(collection);
        try {
            file = new FileOutputStream(System.getenv("File_Path"));
            file.write(json.getBytes());
        }
        catch (FileNotFoundException exp){
            System.out.println("Файл не найден.");
            return;
        }
        file.close();
        System.out.println("Изменения сохранены.");
    }

    public static void exit(){
        System.out.println("Программа завершена.");
        System.exit(0);
    }

    public static void reorder (Vector<Ticket> collection) {
        if (collection.size()>0){
            reverse(collection);
            System.out.println("Коллекция отсортирована в обратном порядке.");
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    public static void addIfMax(Vector<Ticket> collection, Ticket ticket){
        class AddIfMaxCoordinates implements Comparator<Ticket>{
            @Override
            public int compare (Ticket ticket1, Ticket ticket2){
                int result = 2;
                if (!(ticket1.getCoordinates().getX() == null || ticket2.getCoordinates().getX() == null)){
                    if (ticket1.getCoordinates().getX() > ticket2.getCoordinates().getX()) result = 1;
                    else if (ticket1.getCoordinates().getX() < ticket2.getCoordinates().getX()) result = -1;
                    else if (ticket1.getCoordinates().getX().equals(ticket2.getCoordinates().getX())){
                        if (ticket1.getCoordinates().getY() > ticket2.getCoordinates().getY()) result = 1;
                        else if (ticket1.getCoordinates().getY() < ticket2.getCoordinates().getY()) result = -1;
                        else if (ticket1.getCoordinates().getY() == ticket2.getCoordinates().getY()) result = 0;
                    }
                }
                return result;
            }
        }

        class AddIfMaxPrice implements Comparator<Ticket>{
            @Override
            public int compare(Ticket ticket1, Ticket ticket2){
                int result = 2;
                if (!(ticket1.getPrice() < 0 || ticket2.getPrice() < 0)){
                    if (ticket1.getPrice() > ticket2.getPrice()) result = 1;
                    else if (ticket1.getPrice() < ticket2.getPrice()) result = -1;
                    else if (ticket1.getPrice().equals(ticket2.getPrice())) result = 0;
                }
                return result;
            }
        }

        Ticket maxByCoordinates = max(collection, new AddIfMaxCoordinates());
        Ticket maxByPrice = max(collection, new AddIfMaxPrice());
        if (collection.size() > 0){
            if (maxByCoordinates.compareTo(ticket) < 0 ) {
                System.out.println("Данный Ticket является максимальным по значению поля coordinates.");
                collection.add(ticket);
                System.out.println("Ticket добавлен в коллекцию.");
            }
            else if (maxByPrice.getPrice() < ticket.getPrice()) {
                System.out.println("Данный Ticket является максимальным по значению поля price.");
                collection.add(ticket);
                System.out.println("Ticket добавлен в коллекцию.");
            }
            else if ((maxByCoordinates.compareTo(ticket) > -1) && (!(maxByPrice.getPrice() < ticket.getPrice())))
                System.out.println("Данный Ticket не является максимальным ни по значению поля coordinates, ни по значению поля price. Ticket не добавлен.");
        }
        else {
            System.out.println("Коллекция пуста. Данный ticket является максимальным по всем параметрам.");
            collection.add(ticket);
            System.out.println("Ticket добавлен в коллекцию.");
        }
    }

    public static void maxByCoordinates(Vector<Ticket> collection) {
        if (collection.size() > 0){
            class FoundMaxCoordinates implements Comparator<Ticket>{
                @Override
                public int compare (Ticket ticket1, Ticket ticket2){
                    int result = 0;
                    if (!(ticket1.getCoordinates().getX() == null || ticket2.getCoordinates().getX() == null )){
                        result = ticket1.getCoordinates().compareTo(ticket2.getCoordinates());
                    }
                    return result;
                }
            }
            System.out.println(max(collection, new FoundMaxCoordinates()));
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    public static void printUniquePrice(Vector<Ticket> collection, float price){
        if (collection.size() > 0){
            for (Ticket ticket : collection){
                if(ticket.getPrice() != null){
                    if (!(ticket.getPrice() == price)){
                        System.out.println(ticket.getPrice());
                    }
                }
                else System.out.println("Билет с Id "+ticket.getId()+ " не содержит значения price.");
            }
        }
        else System.out.println("Коллекция не имеет элементов.");
    }


    public static void printFieldDescendingPrice(Vector<Ticket> collection){
        if (collection.size() > 0){
            class SortByPrice implements Comparator<Ticket>{
                @Override
                public int compare(Ticket ticket1, Ticket ticket2){
                    int result = 0;
                    if (!(ticket1.getPrice() < 0 || ticket2.getPrice() < 0)){
                        result = ticket1.getPrice().compareTo(ticket2.getPrice());
                    }
                    return result;
                }
            }
            Comparator comparator = Collections.reverseOrder(new SortByPrice());
            sort(collection, comparator);
            for (Ticket ticket : collection) {
                System.out.println(ticket.getPrice());
            }
        }
        else System.out.println("Коллекция не имеет элементов.");
    }

    /**
     * The method that reads the script.
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     * @throws InvalidScriptException
     * @throws ClassNotFoundException
     */

    public static ArrayList<Command> readCommandFromFile(String Path) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, IllegalArgumentException, InvalidScriptException, ClassNotFoundException {
        BufferedReader bufferedReader;
        ArrayList<Command> commands = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new FileReader(Path));
            scriptNames.add(Path);
        }
        catch (IOException e){
            System.out.println("Ошибка в пути к файлу.");
            return null;
        }

        //Считываем построчно файл.

        Scanner scanner;
        String line;
        String data;
        Class myObject;
        Command command = null;
        String name = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("");
            System.out.println("data: " + line);
            scanner = new Scanner(line);
            while (scanner.hasNext()) {
                while (true) {
                    try {
                        data = scanner.nextLine() + " ";
                        name = data.substring(0, data.indexOf(" "));
                        myObject = Class.forName("commands.Command_" + name);
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Несуществующая команда " + name + ". Исправьте скрипт.");
                        return null;
                    }
                }
                data = data.trim();
                Constructor[] constructors = myObject.getConstructors();
                Class[] parameterTypes = constructors[0].getParameterTypes();
                try {
                    if (parameterTypes.length == 0) {
                        command = (Command) constructors[0].newInstance();
                        System.out.println("Полученная из скрипта команда: " + command);
                    }
                    else if (parameterTypes.length == 1) {
                        if (parameterTypes[0].toString().compareTo("int") == 0) {
                            command = (Command) constructors[0].newInstance(Integer.parseInt(data.substring(data.lastIndexOf(" ") + 1)));
                            System.out.println("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("class com.company.Ticket") == 0) {
                            command = (Command) constructors[0].newInstance(createTicket(bufferedReader));
                            System.out.println("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("float") == 0) {
                            command = (Command) constructors[0].newInstance(Float.parseFloat(data.substring(data.lastIndexOf(" ") + 1)));
                            System.out.println("Полученная из скрипта команда: " + command);
                        } else if (parameterTypes[0].toString().compareTo("class java.lang.String") == 0) {
                            command = (Command) constructors[0].newInstance(data.substring(data.indexOf(" ") + 1));
                            System.out.println("Полученная из скрипта команда: " + command);
                        }
                    }
                    else if (parameterTypes.length == 2) {
                        command = (Command) constructors[0].newInstance(Integer.parseInt(data.substring(data.indexOf(" ") + 1)), createTicket(bufferedReader));
                    }
                } catch (NumberFormatException | InputMismatchException | InvalidScriptException e) {
                    throw new InvalidScriptException();
                }
                if ((command instanceof Command_ExecuteScriptFile)){
                    Iterator iterator = scriptNames.iterator();
                    boolean check = false;
                    while (iterator.hasNext()){
                        if (parameterTypes[0].toString().equals(iterator.next())) check = true;
                    }
                    if (check){
                        System.out.println(ANSI_RED+"\tПопытка зациклить программу прервана."+ANSI_RESET);
                    }
                    else{
                        scriptNames.add(parameterTypes[0].toString());
                        ++countExecute;
                        System.out.println(ANSI_GREEN+"\tИсполняется полученный из скрипта скрипт."+ANSI_RESET);
                        command.execute();
                        --countExecute;
                        System.out.println(ANSI_GREEN+"\tСкрипт, полученный из скрипта, исполнен."+ANSI_RESET);
                    }
                }
                else {
                    command.execute();
                }
            }
        }
        if (countExecute == 0){
            scriptNames.clear();
        }
        return commands;
    }

    private static Ticket createTicket (BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        String name = null;
        float price;
        String comment;
        TicketType type;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            if (!input.isEmpty()) name = scanner.next().trim();
        }
        System.out.println("name: " + name);
        Coordinates coordinates = createCoordinates(bufferedReader);
        price = getFloat(bufferedReader);
        System.out.println("price: " + price);
        comment = getComment(bufferedReader);
        System.out.println("comment: "+ comment);
        type = createType(bufferedReader);
        System.out.println("type: "+type);
        Person person = createPerson(bufferedReader);
        return new Ticket(name, coordinates, price, comment, type, person);
    }

    private static Float getFloat(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Float result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.nextFloat();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать дробное число (float).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static String getComment(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        String result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.next();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать строку.");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static Coordinates createCoordinates(BufferedReader bufferedReader) throws IOException, InputMismatchException, InvalidScriptException{
        System.out.println("coordinates: ");
        Float x = null;
        double y;
        x = getFloat(bufferedReader);
        System.out.println("x: "+x);
        y = getDouble(bufferedReader);
        System.out.println("y: "+y);
        return new Coordinates(x, y);
    }

    private static Double getDouble(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Double result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) result = scanner.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать дробное число (double).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static TicketType createType(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        TicketType type = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try {
                if (!input.isEmpty()) type = TicketType.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return type;
    }

    private static Person createPerson(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        int height = getInteger(bufferedReader);
        System.out.println("height: " + height);
        Color eyeColor = createColor(bufferedReader);
        System.out.println("eyeColor: "+ eyeColor);
        Color hairColor = createColor(bufferedReader);
        System.out.println("hairColor: "+hairColor);
        Country nationality = createCountry(bufferedReader);
        System.out.println("nationality: "+nationality);
        return new Person(height, eyeColor, hairColor, nationality);
    }

    private static Integer getInteger(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Integer result = null;
        String input = "";
        while (input.isEmpty()){
            input = bufferedReader.readLine();
            scanner = new Scanner(input);
            try{
                if (!input.isEmpty()) result = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Ошибка в скрипте: Неверные данные. Данная строка должна содержать целое число (int).");
                throw new InvalidScriptException();
            }
        }
        return result;
    }

    private static Color createColor(BufferedReader bufferedReader) throws IOException, InvalidScriptException{
        Scanner scanner;
        Color color = null;
        String string = "";
        while (string.isEmpty()){
            string = bufferedReader.readLine();
            scanner = new Scanner(string);
            try {
                if (!string.isEmpty()) color = Color.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return color;
    }

    private static Country createCountry(BufferedReader bufferedReader) throws IOException, InvalidScriptException {
        Scanner scanner;
        Country country = null;
        String string = "";
        while (string.isEmpty()){
            string = bufferedReader.readLine();
            scanner = new Scanner(string);
            try {
                if (!string.isEmpty()) country = Country.valueOf(scanner.next().trim());
            }
            catch (IllegalArgumentException e){
                System.out.println("Ошибка в скрипте: Неверные данные.");
                throw new InvalidScriptException();
            }
        }
        return country;
    }
}

