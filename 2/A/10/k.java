import java.util.Date;
import java.util.Scanner;

public class k {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число k: ");
        double k = scanner.nextDouble();
        
        int category;

        if (k > -10 * k && k <= 0) {
            category = 1;
        } else if (k > 0 && k <= 5) {
            category = 2;
        } else if (k > 5 && k <= 10) {
            category = 3;
        } else if (k > 10 && k <= 10 * k) {
            category = 4;
        } else {
            category = 0; // вне всех интервалов
        }

        switch (category) {
            case 1:
                System.out.println("Число k принадлежит интервалу (-10k, 0]");
                break;
            case 2:
                System.out.println("Число k принадлежит интервалу (0, 5]");
                break;
            case 3:
                System.out.println("Число k принадлежит интервалу (5, 10]");
                break;
            case 4:
                System.out.println("Число k принадлежит интервалу (10, 10k]");
                break;
            default:
                System.out.println("Число k не принадлежит ни одному заданному интервалу");
        }

        // Вывод информации о разработчике и датах
        System.out.println("\nРазработчик: ваше имя");
        System.out.println("Дата и время получения задания: 2025-10-01 10:00");

        Date date = new Date(); // текущая дата и время
        System.out.println("Дата и время сдачи задания: " + date);

        scanner.close();
    }
}
