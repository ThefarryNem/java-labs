public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы слова в аргументах командной строки.");
            return;
        }

        // Поиск первого слова с символами в строгом порядке возрастания
        String result = findWordWithAscendingChars(args);

        // Вывод результата
        if (result != null) {
            System.out.println("Первое слово с символами в строгом порядке возрастания: " + result);
        } else {
            System.out.println("Не найдено слов с символами в строгом порядке возрастания.");
        }
    }

    public static String findWordWithAscendingChars(String[] words) {
        for (String word : words) {
            if (hasStrictlyAscendingChars(word)) {
                return word;
            }
        }
        return null;
    }

    public static boolean hasStrictlyAscendingChars(String word) {
        if (word == null || word.length() <= 1) {
            // Слово из 0 или 1 символа считается удовлетворяющим условию
            return true;
        }

        // Проверяем, что каждый следующий символ больше предыдущего
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) >= word.charAt(i + 1)) {
                return false;
            }
        }

        return true;
    }
}
