public class FindGcdLcm {
	public static void main(String[] args) {
		int[] array = { 30, 20, 50, 5 };
		System.out.print("array: ");
		for (int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();

		int gcd = findGcd(array);
		int lcm = findLCM(array);
		System.out.println("GDC: " + gcd);
		System.out.println("LCM: " + lcm);
	}

	static int findGcd(int[] array) {
		int gcd = array[0];

		for (int i = 1; i < array.length; i++) {
			gcd = gcd(gcd, array[i]);
		}

		return gcd;
	}

	static int findLCM(int[] array) {
		int lcm = array[0];

		for (int i = 1; i < array.length; i++) {
			lcm = lcm(lcm, array[i]);
		}

		return lcm;
	}

	static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}
}
