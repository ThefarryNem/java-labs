import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод количества слов
        System.out.print("Enter number of words (n): ");
        int n = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        
        if (n <= 0) {
            System.out.println("Number of words must be positive");
            return;
        }
        
        String[] words = new String[n];
        
        // Ввод слов
        System.out.println("Enter " + n + " words:");
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }
        
        // Поиск слова с символами в строгом порядке возрастания
        String result = findWordWithAscendingChars(words);
        
        // Вывод результата
        if (result != null) {
            System.out.println("First word with strictly ascending characters: " + result);
        } else {
            System.out.println("No word found with strictly ascending characters");
        }
        
        scanner.close();
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
        
        // Проверяем, что каждый следующий символ имеет больший код, чем предыдущий
        for (int i = 0; i < word.length() - 1; i++) {
            char currentChar = word.charAt(i);
            char nextChar = word.charAt(i + 1);
            
            if (currentChar >= nextChar) {
                return false;
            }
        }
        
        return true;
    }
}
