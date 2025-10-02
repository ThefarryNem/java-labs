public class LabA6 {
	public static double evaluate(int[] a, double x) {
		int n = a.length;
		double result = a[n - 1]; // последний элемент
		for (int i = n - 2; i >= 1; i--) { // идем от конца к началу
			result = a[i] + x / result;
		}
		return a[0] + x / result;
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 4, 5 }; // a₀ = 2, a₁ = 3, ...
		double x = 1.0;
		double value = evaluate(a, x);
		System.out.println("Значение цепной дроби: " + value);

	}
}
