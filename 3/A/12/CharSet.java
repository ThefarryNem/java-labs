import java.util.*;

public class CharSet {
	private Set<Character> elements;
	private int capacity;

	// Конструктор по умолчанию
	public CharSet() {
		this.capacity = 10;
		this.elements = new HashSet<>();
	}

	// Конструктор с заданной мощностью
	public CharSet(int capacity) {
		this.capacity = capacity;
		this.elements = new HashSet<>();
	}

	// Конструктор из строки
	public CharSet(String input, int capacity) {
		this.capacity = capacity;
		this.elements = new HashSet<>();
		for (char c : input.toCharArray()) {
			if (elements.size() < capacity) {
				elements.add(c);
			}
		}
	}

	// Проверка принадлежности
	public boolean contains(char c) {
		return elements.contains(c);
	}

	// Пересечение
	public CharSet intersect(CharSet other) {
		CharSet result = new CharSet(Math.min(this.capacity, other.capacity));
		for (char c : this.elements) {
			if (other.contains(c)) {
				result.add(c);
			}
		}
		return result;
	}

	// Объединение
	public CharSet union(CharSet other) {
		CharSet result = new CharSet(this.capacity + other.capacity);
		result.elements.addAll(this.elements);
		for (char c : other.elements) {
			if (result.elements.size() < result.capacity) {
				result.elements.add(c);
			}
		}
		return result;
	}

	// Разность
	public CharSet difference(CharSet other) {
		CharSet result = new CharSet(this.capacity);
		for (char c : this.elements) {
			if (!other.contains(c)) {
				result.add(c);
			}
		}
		return result;
	}

	// Добавление элемента
	public boolean add(char c) {
		if (elements.size() < capacity) {
			return elements.add(c);
		}
		return false;
	}

	// Индексирование (по позиции в отсортированном виде)
	public Character get(int index) {
		List<Character> sorted = new ArrayList<>(elements);
		Collections.sort(sorted);
		if (index >= 0 && index < sorted.size()) {
			return sorted.get(index);
		}
		return null;
	}

	// Присваивание
	public void assign(CharSet other) {
		this.capacity = other.capacity;
		this.elements = new HashSet<>(other.elements);
	}

	// Переопределение toString
	@Override
	public String toString() {
		return elements.toString();
	}

	class CharSetProcessor {
		// Множество, состоящее из элементов, входящих только в одно из двух множеств
		public static CharSet symmetricDifference(CharSet a, CharSet b) {
			CharSet result = new CharSet(a.capacity + b.capacity);
			for (char c : a.elements) {
				if (!b.contains(c))
					result.add(c);
			}
			for (char c : b.elements) {
				if (!a.contains(c))
					result.add(c);
			}
			return result;
		}
	}

	public static void main(String[] args) {
		CharSet set1 = new CharSet("abcde", 5);
		CharSet set2 = new CharSet("cdefg", 5);

		System.out.println("Set1: " + set1);
		System.out.println("Set2: " + set2);

		System.out.println("Union: " + set1.union(set2));
		System.out.println("Intersection: " + set1.intersect(set2));
		System.out.println("Difference: " + set1.difference(set2));

		CharSet[] sets = { set1, set2 };
		CharSet result = CharSetProcessor.symmetricDifference(sets[0], sets[1]);
		System.out.println("Symmetric Difference: " + result);
	}
}
