public class Main {
	// Класс Число
	static class Число {
		protected double value;

		public Число(double value) {
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return Double.toString(value);
		}
	}

	// Класс ПростаяДробь
	static class ПростаяДробь {
		private Число числитель;
		private Число знаменатель;

		public ПростаяДробь(Число числитель, Число знаменатель) {
			if (знаменатель.getValue() == 0) {
				throw new ArithmeticException("Знаменатель не может быть равен нулю");
			}
			this.числитель = числитель;
			this.знаменатель = знаменатель;
		}

		// Вывод на экран
		public void вывести() {
			System.out.println(toString());
		}

		@Override
		public String toString() {
			return числитель + "/" + знаменатель;
		}

		// Методы сложения, вычитания, умножения, деления
		public ПростаяДробь сложить(ПростаяДробь другая) {
			double num1 = this.числитель.getValue();
			double den1 = this.знаменатель.getValue();

			double num2 = другая.числитель.getValue();
			double den2 = другая.знаменатель.getValue();

			double newNum = num1 * den2 + num2 * den1;
			double newDen = den1 * den2;

			return new ПростаяДробь(new Число(newNum), new Число(newDen));
		}

		public ПростаяДробь вычесть(ПростаяДробь другая) {
			double num1 = this.числитель.getValue();
			double den1 = this.знаменатель.getValue();

			double num2 = другая.числитель.getValue();
			double den2 = другая.знаменатель.getValue();

			double newNum = num1 * den2 - num2 * den1;
			double newDen = den1 * den2;

			return new ПростаяДробь(new Число(newNum), new Число(newDen));
		}

		public ПростаяДробь умножить(ПростаяДробь другая) {
			double num1 = this.числитель.getValue();
			double den1 = this.знаменатель.getValue();

			double num2 = другая.числитель.getValue();
			double den2 = другая.знаменатель.getValue();

			return new ПростаяДробь(new Число(num1 * num2), new Число(den1 * den2));
		}

		public ПростаяДробь разделить(ПростаяДробь другая) {
			double num1 = this.числитель.getValue();
			double den1 = this.знаменатель.getValue();

			double num2 = другая.числитель.getValue();
			double den2 = другая.знаменатель.getValue();

			if (num2 == 0) {
				throw new ArithmeticException("Деление на ноль");
			}

			return new ПростаяДробь(new Число(num1 * den2), new Число(den1 * num2));
		}
	}

	public static void main(String[] args) {
		try {
			// Создаем дроби
			Число числитель1 = new Число(1);
			Число знаменатель1 = new Число(2);
			ПростаяДробь дробь1 = new ПростаяДробь(числитель1, знаменатель1);

			Число числитель2 = new Число(3);
			Число знаменатель2 = new Число(4);
			ПростаяДробь дробь2 = new ПростаяДробь(числитель2, знаменатель2);

			// Вывод исходных
			System.out.print("Дробь 1: ");
			дробь1.вывести();
			System.out.print("Дробь 2: ");
			дробь2.вывести();

			// Сложение
			ПростаяДробь сумма = дробь1.сложить(дробь2);
			System.out.print("Сумма: ");
			сумма.вывести();

			// Вычитание
			ПростаяДробь разность = дробь1.вычесть(дробь2);
			System.out.print("Разность: ");
			разность.вывести();

			// Умножение
			ПростаяДробь произведение = дробь1.умножить(дробь2);
			System.out.print("Произведение: ");
			произведение.вывести();

			// Деление
			ПростаяДробь частное = дробь1.разделить(дробь2);
			System.out.print("Частное: ");
			частное.вывести();

		} catch (Exception e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}
}
