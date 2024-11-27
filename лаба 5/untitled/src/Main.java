import java.util.*;
class Person
{
   private double weight;
   private int age;
   private String name;
    public Person(double weight,int age,String name)
    {
        this.weight=weight;
        this.age=age;
        this.name=name;
    }
    public String GetName()
    {
        return name;
    }
    public double GetWeight()
    {
        return weight;
    }
    public int value(int weight,int age)
    {
        return weight*age;
    }
    public int value(int weight,int age,int time)
    {
        return weight*age*time;
    }
}
public class Main {
    public static void main(String[] args) {
        Person person1 = new Person(34.5,14,"Sasha");
        Person person2 = new Person(62.7,24,"Vitya");
        Person person3 = new Person(73.9,19,"Nikita");
        List<Person> list=new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        for(Person std:list)
        {
            System.out.println("Вес "+std.GetName()+" составляет - " + std.GetWeight());
        }

    }
}