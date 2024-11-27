import java.time.Period;

class Light_shop
{
    double weight;
    String type;
    int size;
    Light_shop()
    {
        type="bulb";
        size=2;
        weight=0.1;
    }
    Light_shop(String type)
    {
        this.type=type;
        size=1;
        weight=0.2;
    }
    Light_shop(String type,int size)
    {
        this.type=type;
        this.size=size;
        weight=0.3;
    }
    int Sum(int size,int weight){
        return size + weight;
    }
    String Sum(String type,String weight)
    {
        return type+weight;
    }
    public void displayInfo() {
        System.out.printf("Type: %s Size: %d Weight: %f\n", type, size,weight);
    }
}
class Bulb extends Light_shop{
    @Override
    public void displayInfo() {
        System.out.printf("Vse rabotaet");
    }
}
public class Main {
    public static void main(String[] args) {
        Light_shop j = new Bulb();
        j.displayInfo();
        Light_shop t = new Bulb();
        t.displayInfo();
        System.out.printf("%d",t.Sum(8,9));
        System.out.printf("%s",t.Sum("hi ","friend"));
        Light_shop n = new Light_shop("incandescent");
        n.displayInfo();
        Light_shop k = new Light_shop("vacuum", 4);
        k.displayInfo();
    }
}