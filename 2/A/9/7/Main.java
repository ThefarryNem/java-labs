import java.util.Date;

/** Главный класс программы для поиска слова из уникальных символов. */
public class Main {

    /** Основной метод программы. */
    public static void main(String[] args) {
        // Дата и время получения задания
        Date startTime = new Date();

        if (args.length == 0) {
            System.out.println("Ошибка: не переданы слова в аргументах командной строки.");
            return;
        }

        String firstUniqueWord = null;

        // Поиск первого слова с уникальными символами
        for (String word : args) {
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
