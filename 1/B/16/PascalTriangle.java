public class PascalTriangle {
	public static void main(String[] args) {
		int[] array = { 0, -2, 10 };

		for (var i : array) {
			if (i > 0) {
				printPascalTriangle(i);
				break;
			}
		}
	}

	private static void printPascalTriangle(int rows) {
		for (int i = 0; i < rows; i++) {
			int number = 1; 
			System.out.format("%" + (rows - i) + "s", ""); 

			for (int j = 0; j <= i; j++) {
				System.out.print(number + " "); 
				number = number * (i - j) / (j + 1); 
			}
			System.out.println(); 
		}
	}
}
