public class SumProduct {
    public static void main(String[] args) {
        int sum = 0;
        int product = 1;

        if (args.length == 0) {
            System.out.println("Пожалуйста, передайте целые числа как аргументы командной строки.");
            return;
        }

        for (String arg : args) {
            try {
                int number = Integer.parseInt(arg);
                sum += number;
                product *= number;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: '" + arg + "' не является целым числом.");
                return;
            }
        }

        System.out.println("Сумма: " + sum);
        System.out.println("Произведение: " + product);
    }
}