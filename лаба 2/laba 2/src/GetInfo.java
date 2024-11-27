public class GetInfo extends Shop
{
    GetInfo(String type,int size,double weight)
    {
        super(type,size,weight);
    }
    String type;
    int size;
    double weight;

    public void Info(String type,int size, double weight)
    {
        this.type=type;
        this.size=size;
        this.weight=weight;
    }
}
