public class Fractional {
	public static void main(String[] args) {
		int[] array = { -1, 3, 10, -2 };

		int i;
		for (i = 1; i < array.length; i++) {
			if (array[i - 1] > 0 && array[i] > 0){
				System.out.println(array[i-1]);
				System.out.println(array[i]);
				break;
			}
		}

		float quotient = (float)array[i] / array [i - 1];
		var str = Float.toString(quotient);
		int dot = str.indexOf('.');
		if (dot == -1) {
			System.out.println("quiotient: " + quotient + ", 0 digits");
			return;
		}

		System.out.println("quiotient: " + quotient + ", " + (str.length() - 1 - dot));
	}
}
