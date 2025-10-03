public class StringLengthAnalyzer {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы строки в аргументах командной строки.");
            return;
        }

        String[] strings = args;
        int[] lengths = new int[strings.length];

        int totalLength = 0;
        for (int i = 0; i < strings.length; i++) {
            lengths[i] = strings[i].length();
            totalLength += lengths[i];
        }

        // Вычисление средней длины
        double averageLength = (double) totalLength / strings.length;
        System.out.printf("\nСредняя длина строк: %.2f\n", averageLength);

        // Вывод строк, длина которых меньше средней
        System.out.println("\nСтроки с длиной МЕНЬШЕ средней:");
        for (int i = 0; i < strings.length; i++) {
            if (lengths[i] < averageLength) {
                System.out.println("Строка: \"" + strings[i] + "\", длина: " + lengths[i]);
            }
        }

        // Вывод строк, длина которых больше средней
        System.out.println("\nСтроки с длиной БОЛЬШЕ средней:");
        for (int i = 0; i < strings.length; i++) {
            if (lengths[i] > averageLength) {
                System.out.println("Строка: \"" + strings[i] + "\", длина: " + lengths[i]);
            }
        }
    }
}
