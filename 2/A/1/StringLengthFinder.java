import java.util.Scanner;
import java.util.Date;

class StringLengthFinder {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите количество строк: ");
		int n = scanner.nextInt();
		scanner.nextLine(); // очистка буфера

		if (n <= 0) {
			System.out.println("Количество строк должно быть положительным числом.");
			scanner.close();
			return;
		}

		String shortest = null;
		String longest = null;

		for (int i = 1; i <= n; i++) {
			System.out.print("Введите строку " + i + ": ");
			String line = scanner.nextLine();

			if (shortest == null || line.length() < shortest.length()) {
				shortest = line;
			}
			if (longest == null || line.length() > longest.length()) {
				longest = line;
			}
		}

		System.out.println();
		System.out.println("Самая короткая строка: " + shortest + " (" + shortest.length() + " символов)");
		System.out.println("Самая длинная строка: " + longest + " (" + longest.length() + " символов)");

		System.out.println("\nРазработчик: Жыжка");
		System.out.println("Дата и время получения задания: 2025-09-26 10:00:00");
		Date currentDate = new Date();
		System.out.println("Дата и время сдачи задания: " + currentDate.toString());
		scanner.close();
	}
}
