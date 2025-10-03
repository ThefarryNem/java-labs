import java.util.Objects;
import java.util.Random;

// Класс "Точка"
class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Point))
			return false;
		Point point = (Point) o;
		return Double.compare(point.x, x) == 0 &&
				Double.compare(point.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Точка с координатами (" + x + ", " + y + ")";
	}
}

// Класс "Круг"
class Circle {
	private Point center;
	private double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	// Метод: задание размеров (центр и радиус)
	public void setSize(Point center, double radius) {
		this.center = center;
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new IllegalArgumentException("Радиус должен быть положительным");
		}
	}

	// Метод: изменение радиуса
	public void changeRadius(double newRadius) {
		if (newRadius > 0) {
			this.radius = newRadius;
		} else {
			throw new IllegalArgumentException("Радиус должен быть положительным");
		}
	}

	// Метод: принадлежит ли точка кругу
	public boolean belongs(Point p) {
		double dx = p.getX() - center.getX();
		double dy = p.getY() - center.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance <= radius;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Circle))
			return false;
		Circle circle = (Circle) o;
		return Double.compare(circle.radius, radius) == 0 &&
				Objects.equals(center, circle.center);
	}

	@Override
	public int hashCode() {
		return Objects.hash(center, radius);
	}

	@Override
	public String toString() {
		return "Круг с центром в " + center + " и радиусом " + radius;
	}
}

// Главный класс
public class CircleApp {
	public static void main(String[] args) {
		Random rand = new Random();

		// создаём случайный центр и радиус
		Point center = new Point(rand.nextInt(21) - 10, rand.nextInt(21) - 10);
		double radius = 1 + rand.nextInt(10);

		Circle circle = new Circle(center, radius);

		Point p1 = new Point(rand.nextInt(21) - 10, rand.nextInt(21) - 10);
		Point p2 = new Point(rand.nextInt(21) - 10, rand.nextInt(21) - 10);

		System.out.println("Исходные данные:");
		System.out.println(circle);

		System.out.println(p1 + " принадлежит кругу? " + (circle.belongs(p1) ? "Да" : "Нет"));
		System.out.println(p2 + " принадлежит кругу? " + (circle.belongs(p2) ? "Да" : "Нет"));

		// изменение радиуса
		circle.changeRadius(15);
		System.out.println("После изменения радиуса: " + circle);
		System.out.println(p2 + " принадлежит кругу? " + (circle.belongs(p2) ? "Да" : "Нет"));

		// задание новых размеров
		circle.setSize(new Point(0, 0), 7);
		System.out.println("После задания новых размеров: " + circle);
	}
}
