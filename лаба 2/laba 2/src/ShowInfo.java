public class ShowInfo extends Shop
{
    ShowInfo(String type,int size,double weight)
    {
        super(type,size,weight);
    }
public void Info(String type,int size, double weight)
{
    System.out.printf("Type: %s, Size: %d Weight: %f",type,size,weight);
}
}