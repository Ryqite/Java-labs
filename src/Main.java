import java.util.*;
import java.util.stream.Collectors;

abstract class Vegetables {
    private String name;
    protected int count = 0;
    protected double calories;

    public Vegetables(String name, double calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public double getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return name + " (количество: " + count + ", калорийность: " + calories + ")";
    }
}

class Cucumber extends Vegetables {
    public Cucumber() {
        super("cuc", 18.2);
    }
}

class Tomato extends Vegetables {
    public Tomato() {
        super("tom", 27.5);
    }
}

class Onion extends Vegetables {
    public Onion() {
        super("on", 65.4);
    }
}

// Интерфейс для подсчета калорийности
@FunctionalInterface
interface Myinterface {
    double sum(int count, double calories);
}

public class Main {
    public static volatile boolean isSortingCancelled = false; // Флаг для отмены сортировки

    public static void main(String[] args) {
        Myinterface ref = (count, calories) -> count * calories;
        boolean h = true;
        boolean j = true;
        List<Integer> int_col=Arrays.asList(1, 2, 3, 4, 5);
        List<String> str_col=Arrays.asList("f", "e", "v", "g", "r");
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("Вывод коллекции чисел");
                int_col.forEach(System.out::println);
            }
        };
        new Thread(runnable).start();
        Thread thread=new Thread(){
            @Override
            public void run() {
                System.out.println("Вывод строковой коллекции:");
                str_col.forEach(System.out::println);
            }
        };
        thread.start();

        List<Vegetables> vegetables = new ArrayList<>();
        Cucumber cucumbers = new Cucumber();
        Tomato tomatoes = new Tomato();
        Onion onions = new Onion();

        vegetables.add(cucumbers);
        vegetables.add(tomatoes);
        vegetables.add(onions);

        boolean filterByName = false;
        boolean filterByCalories = false;
        String filterName = "";
        double minCalories = 0;

        Scanner scanner = new Scanner(System.in);

        while (h) {
            System.out.println("\nВыберите действие");
            System.out.println("0 - сделать салат");
            System.out.println("1 - посчитать калорийность салата");
            System.out.println("2 - добавить фильтр по названию");
            System.out.println("3 - добавить фильтр по калорийности");
            System.out.println("4 - убрать все фильтры");
            System.out.println("5 - вывести нефильтрованную и отфильтрованную коллекцию");
            System.out.println("6 - сортировать по возрастанию калорийности (Runnable)");
            System.out.println("7 - сортировать по убыванию калорийности (Thread)");
            System.out.println("8 - отменить сортировку");
            System.out.println("9 - выход из программы");

            int n = scanner.nextInt();
            switch (n) {
                case 0:
                    j = true;
                    while (j) {
                        System.out.println("\nВыберите овощ: ");
                        System.out.println("1 - добавить огурец");
                        System.out.println("2 - добавить томат");
                        System.out.println("3 - добавить лук");
                        System.out.println("4 - конец");

                        int k = scanner.nextInt();
                        switch (k) {
                            case 1:
                                cucumbers.addCount(1);
                                break;
                            case 2:
                                tomatoes.addCount(1);
                                break;
                            case 3:
                                onions.addCount(1);
                                break;
                            case 4:
                                j = false;
                                break;
                        }
                    }
                    break;

                case 1:
                    boolean finalFilterByName = filterByName;
                    boolean finalFilterByCalories = filterByCalories;
                    String finalFilterName = filterName;
                    double finalMinCalories = minCalories;
                    List<Vegetables> filteredVegetables = vegetables.stream()
                            .filter(v -> !finalFilterByName || v.getName().equalsIgnoreCase(finalFilterName))
                            .filter(v -> !finalFilterByCalories || v.getCalories() >= finalMinCalories)
                            .collect(Collectors.toList());

                    double sum = filteredVegetables.stream()
                            .mapToDouble(v -> ref.sum(v.getCount(), v.getCalories()))
                            .sum();

                    System.out.println("Конечная калорийность салата: ");
                    System.out.format("%.3f\n", sum);
                    break;

                case 2:
                    System.out.println("Введите название овоща для фильтрации (cuc, tom, on): ");
                    filterName = scanner.next();
                    filterByName = true;
                    System.out.println("Фильтр по названию установлен.");
                    break;

                case 3:
                    System.out.println("Введите минимальную калорийность для фильтрации: ");
                    minCalories = scanner.nextDouble();
                    filterByCalories = true;
                    System.out.println("Фильтр по калорийности установлен.");
                    break;

                case 4:
                    filterByName = false;
                    filterByCalories = false;
                    filterName = "";
                    minCalories = 0;
                    System.out.println("Все фильтры убраны.");
                    break;

                case 5:
                    System.out.println("Нефильтрованная коллекция: ");
                    vegetables.forEach(System.out::println);

                    boolean finalFilterByName1 = filterByName;
                    boolean finalFilterByCalories1 = filterByCalories;
                    String finalFilterName1 = filterName;
                    double finalMinCalories1 = minCalories;
                    List<Vegetables> filteredVegetablesList = vegetables.stream()
                            .filter(v -> !finalFilterByName1 || v.getName().equalsIgnoreCase(finalFilterName1))
                            .filter(v -> !finalFilterByCalories1 || v.getCalories() >= finalMinCalories1)
                            .collect(Collectors.toList());

                    System.out.println("Отфильтрованная коллекция: ");
                    filteredVegetablesList.forEach(System.out::println);
                    break;

                case 6:
                    isSortingCancelled = false;
                    List<Vegetables> filteredAsc = applyFilters(vegetables, filterByName, filterName, filterByCalories, minCalories);
                    new Thread(new AscendingSortTask(filteredAsc)).start();
                    break;

                case 7:
                    isSortingCancelled = false;
                    List<Vegetables> filteredDesc = applyFilters(vegetables, filterByName, filterName, filterByCalories, minCalories);
                    new DescendingSortThread(filteredDesc).start();
                    break;

                case 8:
                    isSortingCancelled = true;
                    System.out.println("Сортировка отменена. Текущая коллекция:");
                    vegetables.forEach(System.out::println);
                    break;

                case 9:
                    h = false;
                    break;
            }
        }
    }

    // Функция для применения фильтров
    public static List<Vegetables> applyFilters(List<Vegetables> vegetables, boolean filterByName, String filterName, boolean filterByCalories, double minCalories) {
        return vegetables.stream()
                .filter(v -> !filterByName || v.getName().equalsIgnoreCase(filterName))
                .filter(v -> !filterByCalories || v.getCalories() >= minCalories)
                .collect(Collectors.toList());
    }
}

class AscendingSortTask implements Runnable {
    private List<Vegetables> vegetables;

    public AscendingSortTask(List<Vegetables> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void run() {
        System.out.println("Сортировка по возрастанию начата...");
        vegetables.sort(Comparator.comparingDouble(Vegetables::getCalories));
        if (!Main.isSortingCancelled) {
            System.out.println("Результат сортировки по возрастанию:");
            vegetables.forEach(System.out::println);
        }
    }
}

class DescendingSortThread extends Thread {
    private List<Vegetables> vegetables;

    public DescendingSortThread(List<Vegetables> vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public void run() {
        System.out.println("Сортировка по убыванию начата...");
        vegetables.sort(Comparator.comparingDouble(Vegetables::getCalories).reversed());
        if (!Main.isSortingCancelled) {
            System.out.println("Результат сортировки по убыванию:");
            vegetables.forEach(System.out::println);
        }
    }
}