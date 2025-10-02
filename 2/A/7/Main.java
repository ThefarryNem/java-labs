import java.util.Scanner;
import java.util.Date;

/** Главный класс программы для поиска слова из уникальных символов. */
public class Main {

    /** Основной метод программы. */
    public static void main(String[] args) {
        // Дата и время получения задания
        Date startTime = new Date();

        Scanner scanner = new Scanner(System.in);
        int n = 0;
        boolean validInput = false;

        // Обработка ввода количества слов с проверкой
        do {
            System.out.print("Введите количество слов n: ");
            try {
                n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Число должно быть положительным. Попробуйте снова.");
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите целое положительное число.");
                scanner.nextLine(); // очищаем буфер
            }
        } while (!validInput);

        scanner.nextLine(); // очищаем буфер после ввода числа

        String[] words = new String[n];

        // Ввод всех слов
        for (int i = 0; i < n; i++) {
            System.out.printf("Введите слово %d: ", i + 1);
            words[i] = scanner.nextLine();
        }

        String firstUniqueWord = null;

        // Поиск первого слова с уникальными символами
        for (String word : words) {
            if (isUniqueChars(word)) {
                firstUniqueWord = word;
                break;
            }
        }

        // Вывод результата
        if (firstUniqueWord != null) {
            System.out.println("Первое слово с уникальными символами: " + firstUniqueWord);
        } else {
            System.out.println("Не найдено слов, состоящих только из уникальных символов.");
        }

        // Дата и время сдачи задания
        Date endTime = new Date();

        System.out.println("\nРазработчик: Иванов И.И.");
        System.out.println("Дата и время получения задания: " + startTime.toString());
        System.out.println("Дата и время сдачи задания: " + endTime.toString());

        scanner.close();
    }

    /** Проверяет, состоит ли слово только из уникальных символов. */
    public static boolean isUniqueChars(String word) {
        boolean[] chars = new boolean[65536]; // Для Unicode символов
        for (char c : word.toCharArray()) {
            if (chars[c]) {
                return false; // Символ уже встречался
            }
            chars[c] = true;
        }
        return true;
    }
}
