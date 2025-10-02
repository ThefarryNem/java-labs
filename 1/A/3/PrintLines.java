public class PrintLines {
    public static void main(String[] args) {
        int n = 2; // можно поменять или передавать через args

        System.out.println("С переходом на новую строку:");
        for (int i = 0; i < n; i++) {
            System.out.println("Строка " + (i + 1));
        }

        System.out.println("\nБез перехода на новую строку:");
        for (int i = 0; i < n; i++) {
            System.out.print("Строка " + (i + 1) + " ");
        }
    }
}