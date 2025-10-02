java import java.util.*;

public class SortStringsByLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Дата получения задания (фиксированная)
        String dateGiven = "2025-09-09 09:00";

        // Текущая дата сдачи задания
        Date dateNow = new Date();

        System.out.print("Введите количество строк n: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        List<String> strings = new ArrayList<>();

        System.out.println("Введите строки:");
        for (int i = 0; i < n; i++) {
            strings.add(scanner.nextLine());
        }

        // Сортировка по возрастанию длины
        strings.sort(Comparator.comparingInt(String::length));
        System.out.println("\nСтроки по возрастанию длины:");
        for (String s : strings) {
            System.out.println(s);
        }

        // Сортировка по убыванию длины
        strings.sort((a, b) -> Integer.compare(b.length(), a.length()));
        System.out.println("\nСтроки по убыванию длины:");
        for (String s : strings) {
            System.out.println(s);
        }

        // Информация об авторе и датах
        System.out.println("\n-----------------------------------");
        System.out.println("Разработчик: ФИО");
        System.out.println("Дата получения задания: " + dateGiven);
        System.out.println("Дата и время сдачи: " + dateNow);
    }
}
