import java.util.Date;

class StringLengthFinder {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ошибка: не переданы строки в аргументах командной строки.");
            return;
        }

        String shortest = args[0];
        String longest = args[0];

        for (String line : args) {
            if (line.length() < shortest.length()) {
                shortest = line;
            }
            if (line.length() > longest.length()) {
                longest = line;
            }
        }

        System.out.println("Самая короткая строка: " + shortest + " (" + shortest.length() + " символов)");
        System.out.println("Самая длинная строка: " + longest + " (" + longest.length() + " символов)");

        System.out.println("\nРазработчик: Жыжка");
        System.out.println("Дата и время получения задания: 2025-09-26 10:00:00");
        Date currentDate = new Date();
        System.out.println("Дата и время сдачи задания: " + currentDate.toString());
    }
}
