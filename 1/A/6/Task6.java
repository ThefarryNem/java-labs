import java.util.Calendar;

public class Task6 {
    public static void main(String[] args) {
        String developer = "Фамилия"; // фамилия разработчика
        Calendar received = Calendar.getInstance();
        received.set(2025, Calendar.SEPTEMBER, 29, 12, 0); // дата получения (пример)
        Calendar submitted = Calendar.getInstance(); // текущая дата и время

        System.out.println("Разработчик: " + developer);
        System.out.println("Дата и время получения: " + received.getTime());
        System.out.println("Дата и время сдачи: " + submitted.getTime());
    }
}