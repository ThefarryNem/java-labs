import java.util.Scanner;

public class jav8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество слов: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        int count = 0; 
        String secondPalindrome = null;

        System.out.println("Введите " + n + " слов:");
        for (int i = 0; i < n; i++) {
            String word = sc.next();

            if (word.matches("\\d+")) {
                if (isPalindrome(word)) {
                    count++;
                    if (count == 2) {
                        secondPalindrome = word;
                    }
                }
            }
        }

        if (secondPalindrome != null) {
            System.out.println("Второе числовое слово-палиндром: " + secondPalindrome);
        } else {
            System.out.println("Второго числового палиндрома нет.");
        }

        sc.close();
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