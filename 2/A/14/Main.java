import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число от 1 до 12: ");
        try {
            int x = in.nextInt();
            String[] months = {
                    "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                    "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
            };
            if (x >= 1 && x <= 12) {
                System.out.println(months[x - 1]);
            }
            else {
                System.out.println("Неверный ввод");
            }
        }
        catch (Exception e) {
            System.out.println("Введите целое число");
        }
        in.close();
    }
}
