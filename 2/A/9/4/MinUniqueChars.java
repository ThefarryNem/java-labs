import java.util.*;

public class MinUniqueChars {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы слова в аргументах командной строки.");
            return;
        }

        String resultWord = null;
        int minUniqueCount = Integer.MAX_VALUE;

        for (String word : args) {
            Set<Character> uniqueChars = new HashSet<>();
            for (char c : word.toCharArray()) {
                uniqueChars.add(c);
            }

            int uniqueCount = uniqueChars.size();
            if (uniqueCount < minUniqueCount) {
                minUniqueCount = uniqueCount;
                resultWord = word;
            }
        }

        System.out.println("Слово с минимальным числом различных символов: " + resultWord);
    }
}
