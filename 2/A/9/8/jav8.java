public class jav8 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы слова в аргументах командной строки.");
            return;
        }

        int count = 0;
        String secondPalindrome = null;

        for (String word : args) {
            // Проверяем, что слово состоит только из цифр
            if (word.matches("\\d+")) {
                if (isPalindrome(word)) {
                    count++;
                    if (count == 2) {
                        secondPalindrome = word;
                        break; // нашли второе, можно завершить
                    }
                }
            }
        }

        if (secondPalindrome != null) {
            System.out.println("Второе числовое слово-палиндром: " + secondPalindrome);
        } else {
            System.out.println("Второго числового палиндрома нет.");
        }
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
