import java.util.*;

public class SortStringsByLength {
    public static void main(String[] args) {
        // Проверка на пустой ввод
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы строки в аргументах командной строки.");
            return;
        }

        // Дата получения задания (фиксированная)
        String dateGiven = "2025-09-09 09:00";

        // Текущая дата сдачи задания
        Date dateNow = new Date();

        // Преобразуем массив аргументов в список
        List<String> strings = new ArrayList<>(Arrays.asList(args));

        // Сортировка по возрастанию длины
        strings.sort(Comparator.comparingInt(String::length));
        System.out.println("Строки по возрастанию длины:");
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
