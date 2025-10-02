import java.util.*;

public class MinUniqueChars {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Введите количество слов: ");
		int n = scanner.nextInt();
		scanner.nextLine(); // очистка буфера

		String resultWord = null;
		int minUniqueCount = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			System.out.print("Введите слово " + (i + 1) + ": ");
			String word = scanner.nextLine();

			Set<Character> uniqueChars = new HashSet<>();
			for (char c : word.toCharArray()) {
				uniqueChars.add(c);
			}

			int uniqueCount = uniqueChars.size();
			if (uniqueCount < minUniqueCount) {
				minUniqueCount = uniqueCount;
				resultWord = word;
			}
		}

		System.out.println("Слово с минимальным числом различных символов: " + resultWord);
	}
}
