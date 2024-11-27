import java.util.*;
import java.io.*;
class Light_shopException extends Exception {
    public Light_shopException(String message) {
        super(message);
    }
}
class Light_shop {
    public double Fin_Cost(double cost, int n) {
        return n * cost;
    }
    public void Cost_Check(Bulb incLamp, Bulb fluorLamp, Bulb led, double b, double l) {
        if (incLamp.cost > b && incLamp.cost < l)
            System.out.printf("Лампа  %s подходит, ее цена - %f\n", incLamp.name, incLamp.cost);
        if (fluorLamp.cost > b && fluorLamp.cost < l)
            System.out.printf("Лампа  %s подходит, ее цена - %f\n", fluorLamp.name, fluorLamp.cost);
        if (led.cost > b && led.cost < l)
            System.out.println("Лампа " + led.name + " подходит, ее цена -" + led.cost);
    }
}
class Bulb extends Light_shop implements Comparable<Bulb>,Serializable {
    protected String name;
    protected double cost;
    protected int btns;
    public Bulb(String name, double cost, int btns) {
        this.name = name;
        this.cost = cost;
        this.btns = btns;
    }
    String getName() {return name;}
    double getcost(){return cost;}
    int getbtns(){ return btns;}
    @Override
    public int compareTo(Bulb k) {
        return this.btns - k.btns;
    }
    @Override
    public String toString() {
        return name + ", " + cost + ", " + btns;
    }
}
class Inc_lamp extends Bulb //Incandescent lamp
{
    public Inc_lamp(String name, double cost, int btns) {
        super(name, cost, btns);
    }
}
class Fluor_lamp extends Bulb //Fluorescent lamp
{
    public Fluor_lamp(String name, double cost, int btns) {
        super(name, cost, btns);
    }
}
class LED extends Bulb {
    public LED(String name, double cost, int btns) {
        super(name, cost, btns);
    }
}
public class Main {
    public static void main(String[] args) {
        Bulb inclamp = new Inc_lamp("incandescent lamp", 1.67, 5);
        Bulb fluorLamp = new Fluor_lamp("fluorescent lamp", 3.67, 7);
        Bulb led = new LED("led", 0.48, 2);
        ArrayList<Bulb> f = new ArrayList<>();
        f.add(inclamp);
        f.add(fluorLamp);
        f.add(led);
        boolean h = true;
        while (h) {
            System.out.println("\nВыберите функцию");
            System.out.println("0-добавить объект");
            System.out.println("1-сортировка по яркости");
            System.out.println("2-проверка на попадание в диапазон цены");
            System.out.println("3-финальная стоимость за n выбранных лампочек");
            System.out.println("4-запись новых данных в файл");
            System.out.println("5-запись всех данных в файл");
            System.out.println("6-чтение из файла");
            System.out.println("7-выход из программы");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                switch (n) {
                    case 0:
                        System.out.println("Введите тип(Inclamp,Fluorlamp,LED),название,цену и яркость лампочки");
                        String q = scanner.next();
                        String x = scanner.next();
                        double y = 0;
                        int z = 0;
                        try {
                           y = scanner.nextDouble();
                           z = scanner.nextInt();
                           if (y < 0 || z < 0) {
                               throw new Light_shopException("Отрицательное значение цены или яркости");
                           }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Ошибка - некорректный ввод данных");
                        }
                        catch (Light_shopException e) {
                            System.out.println(e.getMessage());
                        }
                        switch (q) {
                            case "Inclamp": {
                                Bulb j = new Inc_lamp(x, y, z);
                                f.add(j);
                                break;
                            }
                            case "Fluorlamp": {
                                Bulb j = new Fluor_lamp(x, y, z);
                                f.add(j);
                                break;
                            }
                            case "LED": {
                                Bulb j = new LED(x, y, z);
                                f.add(j);
                                break;
                            }
                            default:
                                System.out.println("Неверный тип лампочки");
                                break;
                        }
                        break;
                    case 1: {
                        Collections.sort(f);

                        for (Bulb std : f) {
                            System.out.println(std);
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Введите диапазон цены(минимум и максимум)");
                        double b = 0;
                        double l = 0;
                        try {
                            b = scanner.nextDouble();
                            l = scanner.nextDouble();
                            if (b < 0 || l < 0) {
                                throw new Light_shopException("Отрицательное значение цены");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Ошибка - некорректный ввод данных");
                        }
                        catch (Light_shopException e) {
                            System.out.println(e.getMessage());
                        }
                        fluorLamp.Cost_Check(inclamp, fluorLamp, led, b, l);
                        break;
                    }
                    case 3: {
                        System.out.println("Введите количество лампочек");
                        int v = 0;
                        String p = "";
                        try {
                            v = scanner.nextInt();
                            System.out.println("Выберите тип лампочки(Inclamp,Fluorlamp,LED)");
                            p = scanner.next();
                            if (v < 0) {
                                throw new Light_shopException("Отрицательное количество лампочек");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ошибка - некорректный ввод данных");
                        }
                        catch (Light_shopException e) {
                            System.out.println(e.getMessage());
                        }
                        switch (p) {
                            case "Inclamp":
                                System.out.println("Цена за " + v + " лампочек " + inclamp.Fin_Cost(inclamp.cost, v));
                                break;
                            case "Fluorlamp": {
                                System.out.println("Цена за " + v + " лампочек " + fluorLamp.Fin_Cost(fluorLamp.cost, v));
                                break;
                            }
                            case "LED": {
                                System.out.println("Цена за " + v + " лампочек " + led.Fin_Cost(led.cost, v));
                                break;
                            }
                            default: {
                                System.out.println("Не найдено соответсвие");
                                break;
                            }
                        }
                        break;
                    }
                    case 4:
                        System.out.println("Введите название,цену и яркость лампочки");
                        String m = scanner.next();
                        double g = scanner.nextDouble();
                        int p = scanner.nextInt();
                        try(ObjectOutputStream objOutStr = new ObjectOutputStream(new FileOutputStream("C:\\Users\\garba\\OneDrive\\Рабочий стол\\лабораторка\\Оопип\\Student.dat")))
                        {
                            Bulb stud = new Bulb(m, g, p);
                            objOutStr.writeObject(stud);
                        }
                        catch (IOException e) {
                            System.out.println("Error. Can not write data in file!");
                        }
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 5:
                        try(ObjectOutputStream objOutStr = new ObjectOutputStream(new FileOutputStream("C:\\Users\\garba\\OneDrive\\Рабочий стол\\лабораторка\\Оопип\\Student.dat")))
                        {
                            objOutStr.writeObject(f);
                        }
                        catch (IOException e) {
                            System.out.println("Error. Can not write data in file!");
                        }
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 6:
                        try(ObjectInputStream objInpStr = new ObjectInputStream(new FileInputStream("C:\\Users\\garba\\OneDrive\\Рабочий стол\\лабораторка\\Оопип\\Student.dat")))
                        {
                            ArrayList<Bulb> bulbs = (ArrayList<Bulb>) objInpStr.readObject();
                            for(Bulb stud:bulbs)
                            {
                            System.out.printf("Name: %s \t Cost: %f \t Btns: %d\n", stud.getName(), stud.getcost(),stud.getbtns());
                            f.add(stud);
                            }
                        }
                        catch (IOException e) {
                            System.out.println("Error. Can not read file!");
                        }

                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 7:
                        h = false;
                        break;
                }
            } else {
                System.out.println("Вы ввели не число");
            }
        }
    }
}