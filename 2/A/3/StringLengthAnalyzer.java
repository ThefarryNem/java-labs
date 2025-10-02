import java.util.Scanner;

public class StringLengthAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ввод количества строк
        System.out.print("Введите количество строк: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        
        // Массив для хранения строк и их длин
        String[] strings = new String[n];
        int[] lengths = new int[n];
        
        // Ввод строк и вычисление их длин
        int totalLength = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Введите строку " + (i + 1) + ": ");
            strings[i] = scanner.nextLine();
            lengths[i] = strings[i].length();
            totalLength += lengths[i];
        }
        
        // Вычисление средней длины
        double averageLength = (double) totalLength / n;
        System.out.printf("\nСредняя длина строк: %.2f\n", averageLength);
        
        // Вывод строк, длина которых меньше средней
        System.out.println("\nСтроки с длиной МЕНЬШЕ средней:");
        for (int i = 0; i < n; i++) {
            if (lengths[i] < averageLength) {
                System.out.println("Строка: \"" + strings[i] + "\", длина: " + lengths[i]);
            }
        }
        
        // Вывод строк, длина которых больше средней
        System.out.println("\nСтроки с длиной БОЛЬШЕ средней:");
        for (int i = 0; i < n; i++) {
            if (lengths[i] > averageLength) {
                System.out.println("Строка: \"" + strings[i] + "\", длина: " + lengths[i]);
            }
        }
        
        scanner.close();
    }
}
