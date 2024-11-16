import java.util.*;
import java.util.stream.Collectors;

// --- Абстрактный класс для овощей ---
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

// --- Конкретные классы овощей ---
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

// --- Фабрика для создания овощей (порождающий паттерн) ---
class VegetableFactory {
    public static Vegetables createVegetable(String type) {
        switch (type.toLowerCase()) {
            case "cuc":
                return new Cucumber();
            case "tom":
                return new Tomato();
            case "on":
                return new Onion();
            default:
                throw new IllegalArgumentException("Unknown vegetable type: " + type);
        }
    }
}

// --- Интерфейс для подсчёта калорийности ---
@FunctionalInterface
interface MyInterface {
    double sum(int count, double calories);
}

// --- Паттерн Фасад (упрощает работу с фильтрацией и сортировкой) ---
class VegetableUtils {
    public static List<Vegetables> applyFilters(List<Vegetables> vegetables, boolean filterByName, String filterName, boolean filterByCalories, double minCalories) {
        return vegetables.stream()
                .filter(v -> !filterByName || v.getName().equalsIgnoreCase(filterName))
                .filter(v -> !filterByCalories || v.getCalories() >= minCalories)
                .collect(Collectors.toList());
    }
}

// --- Задача для сортировки (поведенческий паттерн: Стратегия) ---
class SortingStrategy {
    public static void sortAscending(List<Vegetables> vegetables) {
        vegetables.sort(Comparator.comparingDouble(Vegetables::getCalories));
    }

    public static void sortDescending(List<Vegetables> vegetables) {
        vegetables.sort(Comparator.comparingDouble(Vegetables::getCalories).reversed());
    }
}

class Filter {
    boolean filterByName = false;
    boolean filterByCalories = false;
    String filterName = "";
    double minCalories = 0;
}

// --- Пользователь и роли ---
class User {
    String username;
    String password;
    boolean isAdmin;
    boolean isBlocked;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBlocked = false;
    }
}

// --- Основной класс ---
public class Main {
    private static Map<String, User> users = new HashMap<>();
    private static User loggedInUser = null;
    public static volatile boolean isSortingCancelled = false;

    public static void main(String[] args) {
        // Добавляем администратора
        users.put("admin", new User("admin", "admin", true));

        MyInterface ref = (count, calories) -> count * calories;
        boolean h = true;

        // Создаем фильтр
        Filter filter = new Filter();

        // Инициализация коллекции
        List<Vegetables> vegetables = new ArrayList<>();
        Cucumber cucumbers = new Cucumber();
        Tomato tomatoes = new Tomato();
        Onion onions = new Onion();
        vegetables.add(cucumbers);
        vegetables.add(tomatoes);
        vegetables.add(onions);

        Scanner scanner = new Scanner(System.in);

        // Логин
        while (loggedInUser == null) {
            System.out.println("Введите логин:");
            String username = scanner.nextLine();
            System.out.println("Введите пароль:");
            String password = scanner.nextLine();
            loggedInUser = authenticate(username, password);
            if (loggedInUser == null) {
                System.out.println("Неверный логин или пароль.");
            }
        }

        while (h) {
            System.out.println("\nВыберите действие");
            if (loggedInUser.isAdmin) {
                System.out.println("0 - управление пользователями");
            }
            System.out.println("1 - создать салат");
            System.out.println("2 - посчитать калорийность салата");
            System.out.println("3 - добавить фильтр по названию");
            System.out.println("4 - добавить фильтр по калорийности");
            System.out.println("5 - убрать все фильтры");
            System.out.println("6 - вывести коллекцию");
            System.out.println("9 - выход");

            int n = scanner.nextInt();
            switch (n) {
                case 0: // Управление пользователями
                    if (loggedInUser.isAdmin) {
                        manageUsers(scanner);
                    } else {
                        System.out.println("Доступ запрещён.");
                    }
                    break;

                case 1: // Создание салата
                    boolean j = true;
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

                case 2: // Подсчёт калорийности
                    List<Vegetables> filteredVegetables = VegetableUtils.applyFilters(
                            vegetables,
                            filter.filterByName,
                            filter.filterName,
                            filter.filterByCalories,
                            filter.minCalories
                    );
                    double sum = filteredVegetables.stream()
                            .mapToDouble(v -> ref.sum(v.getCount(), v.getCalories()))
                            .sum();
                    System.out.println("Конечная калорийность салата: ");
                    System.out.format("%.3f\n", sum);
                    break;

                case 3: // Фильтр по названию
                    System.out.println("Введите название овоща для фильтрации (cuc, tom, on): ");
                    filter.filterName = scanner.next();
                    filter.filterByName = true;
                    System.out.println("Фильтр по названию установлен.");
                    break;

                case 4: // Фильтр по калорийности
                    System.out.println("Введите минимальную калорийность для фильтрации: ");
                    filter.minCalories = scanner.nextDouble();
                    filter.filterByCalories = true;
                    System.out.println("Фильтр по калорийности установлен.");
                    break;

                case 5: // Убрать фильтры
                    filter.filterByName = false;
                    filter.filterByCalories = false;
                    filter.filterName = "";
                    filter.minCalories = 0;
                    System.out.println("Все фильтры убраны.");
                    break;

                case 6: // Вывод коллекции
                    System.out.println("Коллекция овощей: ");
                    vegetables.forEach(System.out::println);
                    break;

                case 9: // Выход
                    h = false;
                    break;

                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
    }

    private static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && !user.isBlocked && user.password.equals(password)) {
            return user;
        }
        return null;
    }

    private static void manageUsers(Scanner scanner) {
        System.out.println("Управление пользователями:");
        System.out.println("1 - Добавить пользователя");
        System.out.println("2 - Заблокировать пользователя");
        System.out.println("3 - Удалить пользователя");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1: // Добавить пользователя
                System.out.println("Введите логин:");
                String username = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();
                System.out.println("Это администратор? (true/false):");
                boolean isAdmin = scanner.nextBoolean();
                users.put(username, new User(username, password, isAdmin));
                System.out.println("Пользователь добавлен.");
                break;

            case 2: // Заблокировать пользователя
                System.out.println("Введите логин пользователя:");
                String userToBlock = scanner.nextLine();
                User user = users.get(userToBlock);
                if (user != null) {
                    user.isBlocked = true;
                    System.out.println("Пользователь заблокирован.");
                } else {
                    System.out.println("Пользователь не найден.");
                }
                break;

            case 3: // Удалить пользователя
                System.out.println("Введите логин пользователя:");
                String userToDelete = scanner.nextLine();
                if (users.remove(userToDelete) != null) {
                    System.out.println("Пользователь удалён.");
                } else {
                    System.out.println("Пользователь не найден.");
                }
                break;

            default:
                System.out.println("Неверный ввод.");
        }
    }
}
