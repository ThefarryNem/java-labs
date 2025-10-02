import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        final String SAMPLE = "MySecret123"; // строка-образец
        String password;

        if (args.length > 0) {
            password = args[0];
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите пароль: ");
            password = sc.nextLine();
        }

        if (SAMPLE.equals(password)) {
            System.out.println("Пароль верный!");
        } else {
            System.out.println("Пароль неверный!");
        }
    }
}