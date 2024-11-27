import java.util.*;

class Light_shop {
    public double Fin_Cost(double cost, int n) {
        return n * cost;
    }

    public void Cost_Check(Bulb bulb, Fluor_lamp fluorLamp, LED led, double n, double l) {
        if (bulb.cost > n && bulb.cost < l)
            System.out.printf("Лампа  %s подходит, ее цена - %l", bulb.name, bulb.cost);
        if (fluorLamp.cost > n && fluorLamp.cost < l)
            System.out.printf("Лампа  %s подходит, ее цена - %l", fluorLamp.name, fluorLamp.cost);
        if (led.cost > n && led.cost < l)
            System.out.println("Лампа " + led.name + " подходит, ее цена -" + led.cost);
    }
}

abstract class Bulb extends Light_shop {
    String name;
    double cost;
    int btns;

}

class Inc_lamp extends Bulb //Incandescent lamp
{
    protected String name;
    protected double cost = 2.24;
    protected int btns = 2;

    public Inc_lamp(String name, double cost, int btns) {
        this.name = name;
        this.cost = cost;
        this.btns = btns;
    }
}

class Fluor_lamp extends Bulb //Fluorescent lamp
{
    protected String name;
    protected double cost = 3.78;
    protected int btns = 5;

    public void Set(String name, double cost, int btns) {
        this.name = name;
        this.cost = cost;
        this.btns = btns;
    }

    public double getCost() {
        return cost;
    }
}

class LED extends Bulb {
    protected String name;
    protected double cost = 0.56;
    protected int btns = 5;

    public LED(String name, double cost, int btns) {
        this.name = name;
        this.cost = cost;
        this.btns = btns;
    }
}

public class Main {
    public static void main(String[] args) {
        Inc_lamp bulb = new Inc_lamp("incandescent lamp", 1.67, 5);
        Fluor_lamp fluorLamp = new Fluor_lamp();
        fluorLamp.Set("fluorescent lamp", 3.67, 7);
        LED led = new LED("led", 0.48, 2);
        List<Comp> comp = new ArrayList<>();

    }
}