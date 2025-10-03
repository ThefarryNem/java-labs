public class Main {

	// Класс Когти
	static class Когти {
		private double длина; // длина когтей в мм
		private String тип; // например, "острые", "крепкие"

		public Когти(double длина, String тип) {
			if (длина <= 0) {
				throw new IllegalArgumentException("Длина когтей должна быть положительной");
			}
			if (тип == null || тип.isEmpty()) {
				throw new IllegalArgumentException("Тип когтей не может быть пустым");
			}
			this.длина = длина;
			this.тип = тип;
		}

		public double getДлина() {
			return длина;
		}

		public void setДлина(double длина) {
			if (длина <= 0) {
				throw new IllegalArgumentException("Длина когтей должна быть положительной");
			}
			this.длина = длина;
		}

		public String getТип() {
			return тип;
		}

		public void setТип(String тип) {
			if (тип == null || тип.isEmpty()) {
				throw new IllegalArgumentException("Тип когтей не может быть пустым");
			}
			this.тип = тип;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Когти когти = (Когти) obj;
			return Double.compare(когти.длина, длина) == 0 &&
					тип.equals(когти.тип);
		}

		@Override
		public int hashCode() {
			int result = Double.hashCode(длина);
			result = 31 * result + тип.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return "Когти{длина=" + длина + "мм, тип='" + тип + "'}";
		}
	}

	// Класс Тигр
	static class Тигр {
		private Когти когти;

		public Тигр(Когти когти) {
			if (когти == null) {
				throw new IllegalArgumentException("Когти не могут быть null");
			}
			this.когти = когти;
		}

		public Когти getКогти() {
			return когти;
		}

		public void setКогти(Когти когти) {
			if (когти == null) {
				throw new IllegalArgumentException("Когти не могут быть null");
			}
			this.когти = когти;
		}

		public void рычать() {
			System.out.println("Тигр рычит: Гррр!");
		}

		public void бежать() {
			System.out.println("Тигр бежит быстро по саванне.");
		}

		public void добыватьПищу() {
			System.out.println("Тигр ищет и охотится на добычу.");
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Тигр тигр = (Тигр) obj;
			return когти.equals(тигр.когти);
		}

		@Override
		public int hashCode() {
			return когти.hashCode();
		}

		@Override
		public String toString() {
			return "Тигр{" +
					"когти=" + когти +
					'}';
		}
	}

	// Основной метод
	public static void main(String[] args) {
		try {
			// Создаём объект Когти
			Когти когтиТигра = new Когти(7.5, "острые");
			// Используем его для создания Тигра
			Тигр тигр = new Тигр(когтиТигра);

			// Демонстрация методов
			System.out.println(тигр.toString());
			тигр.рычать();
			тигр.бежать();
			тигр.добыватьПищу();

			// Проверка методов equals и hashCode
			Когти когтиДругие = new Когти(7.5, "острые");
			Тигр тигр2 = new Тигр(когтиДругие);

			System.out.println("Тигры равны? " + тигр.equals(тигр2));
			System.out.println("HashCode первого тигра: " + тигр.hashCode());
			System.out.println("HashCode второго тигра: " + тигр2.hashCode());

			// Попытка создания когтей с неправильными параметрами
			Когти плохиеКогти = new Когти(-5, "острые"); // вызовет исключение
		} catch (IllegalArgumentException e) {
			System.out.println("Ошибка: " + e.getMessage());
		}
	}
}
