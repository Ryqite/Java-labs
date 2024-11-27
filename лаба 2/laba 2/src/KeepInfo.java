import java.util.ArrayList;
import java.util.List;



public class KeepInfo extends Shop
{
    KeepInfo(String type,int size,double weight)
    {
        super(type,size,weight);
    }
    List<String> list = new ArrayList<>();

    public void Info(String type,int size, double weight)
    {
    list.add(type);
    }
}
