import java.util.ArrayList;
import java.util.List;

class QuadraticEquation {
	private double a;
	private double b;
	private double c;

	// Конструктор по трем коэффициентам
	public QuadraticEquation(double a, double b, double c) {
		if (a == 0) {
			throw new IllegalArgumentException("Коэффициент 'a' не может быть равен нулю для квадратного уравнения.");
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}

	// Конструктор для вида a*x^2 + b*x = 0 (c=0)
	public QuadraticEquation(double a, double b) {
		this(a, b, 0);
	}

	// Конструктор для уравнения x^2 + b*x + c = 0 (a=1)
	public QuadraticEquation(double b, double c, boolean isStandard) {
		this(1, b, c);
	}

	// Метод для нахождения корней
	public double[] findRoots() {
		double discriminant = b * b - 4 * a * c;

		if (discriminant > 0) {
			double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
			double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
			return new double[] { root1, root2 };
		} else if (discriminant == 0) {
			double root = -b / (2 * a);
			return new double[] { root };
		} else {
			// Корней нет в действительных числах, можно вернуть пустой массив или null
			return new double[0];
		}
	}

	// Метод для нахождения экстремума (координаты вершины параболы)
	public double vertexX() {
		return -b / (2 * a);
	}

	public double vertexY() {
		double x = vertexX();
		return a * x * x + b * x + c;
	}

	// Метод интервалов возрастания и убывания
	// Возвращаем описание интервалов как строку
	public String intervals() {
		double vertex = vertexX();

		if (a > 0) {
			// Парабола "вверх", минимум в вершине
			return "Убывание на (-∞, " + vertex + "), Возрастание на (" + vertex + ", +∞)";
		} else {
			// Парабола "вниз", максимум в вершине
			return "Возрастание на (-∞, " + vertex + "), Убывание на (" + vertex + ", +∞)";
		}
	}

	// Геттеры для коэффициентов (на случай необходимости)
	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	public static void main(String[] args) {
		QuadraticEquation[] equations = {
				new QuadraticEquation(1, -3, 2), // x^2 -3x +2 =0 (корни 2 и 1)
				new QuadraticEquation(2, 4, -6), // 2x^2 +4x -6=0
				new QuadraticEquation(1, 2, 1), // x^2 +2x +1=0 (корень -1)
				new QuadraticEquation(1, 0, -4) // x^2 -4=0 (корни 2 и -2)
		};

		List<Double> allRoots = new ArrayList<>();

		for (QuadraticEquation eq : equations) {
			double[] roots = eq.findRoots();
			if (roots.length == 0) {
				System.out.println(
						"Уравнение " + eq.a + "x^2 + " + eq.b + "x + " + eq.c + " = 0 не имеет действительных корней.");
			} else {
				System.out.print("Корни уравнения " + eq.a + "x^2 + " + eq.b + "x + " + eq.c + " = 0: ");
				for (double r : roots) {
					System.out.print(r + " ");
					allRoots.add(r);
				}
				System.out.println();
			}
			System.out.println("Экстремум (вершина): (" + eq.vertexX() + ", " + eq.vertexY() + ")");
			System.out.println("Интервалы: " + eq.intervals());
			System.out.println();
		}

		if (!allRoots.isEmpty()) {
			double minRoot = allRoots.get(0);
			double maxRoot = allRoots.get(0);
			for (double root : allRoots) {
				if (root < minRoot)
					minRoot = root;
				if (root > maxRoot)
					maxRoot = root;
			}
			System.out.println("Наименьший корень среди всех уравнений: " + minRoot);
			System.out.println("Наибольший корень среди всех уравнений: " + maxRoot);
		} else {
			System.out.println("Действительные корни отсутствуют во всех уравнениях.");
		}
	}
}
