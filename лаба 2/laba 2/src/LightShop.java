import java.time.Period;
abstract class Shop {
    int size;
    String type;
    double weight;
    Shop(String type,int size,double weight)
    {
        this.type=type;
        this.size=size;
        this.weight=weight;
    }
    public abstract void Info(String type,int size, double weight);
}
class Main {
    public static void main(String[] args) {
        Shop n= new ShowInfo("vacuum",4,0.2);
        n.Info("vacuum",4,0.2);
    }
}