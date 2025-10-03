import java.util.Date;

public class WordAnalyzer {
    public static boolean isLatinOnly(String word) {
        return word.matches("[a-zA-Z]+");
    }

    public static int[] countVowelsAndConsonants(String word) {
        int vowels = 0;
        int consonants = 0;
        String lowerWord = word.toLowerCase();

        for (int i = 0; i < lowerWord.length(); i++) {
            char c = lowerWord.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
                vowels++;
            } else if (Character.isLetter(c)) {
                consonants++;
            }
        }

        return new int[] { vowels, consonants };
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы слова в аргументах командной строки.");
            return;
        }

        int n = args.length;
        int latinWordsCount = 0;
        int equalVowelsConsonantsCount = 0;

        // Чтение и обработка слов
        for (String word : args) {
            if (isLatinOnly(word)) {
                latinWordsCount++;

                int[] counts = countVowelsAndConsonants(word);
                if (counts[0] == counts[1]) {
                    equalVowelsConsonantsCount++;
                }
            }
        }

        // Вывод результатов
        System.out.println("\nРезультаты анализа:");
        System.out.println("Всего слов: " + n);
        System.out.println("Слов с только латинскими символами: " + latinWordsCount);
        System.out.println("Слов с равным числом гласных и согласных: " + equalVowelsConsonantsCount);

        // Дата и время сдачи задания
        Date assignmentSubmitted = new Date();

        System.out.println("Разработчик: Казакевич");
        System.out.println("Дата и время получения задания : 2025-09-26 10:00:00");
        System.out.println("Дата и время сдачи задания: " + assignmentSubmitted);
    }
}
