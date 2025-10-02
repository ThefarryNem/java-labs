import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Класс Лист
class Listok {
	private String color;
	private boolean onTree;

	public Listok(String color) {
		this.color = color;
		this.onTree = true; // изначально лист на дереве
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isOnTree() {
		return onTree;
	}

	public void setOnTree(boolean onTree) {
		this.onTree = onTree;
	}

	@Override
	public String toString() {
		return "Лист {цвет='" + color + "', на дереве=" + onTree + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Listok))
			return false;
		Listok listok = (Listok) o;
		return onTree == listok.onTree &&
				Objects.equals(color, listok.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, onTree);
	}
}

// Класс Дерево
class Derevo {
	private List<Listok> listya;
	private boolean vCvetu;
	private boolean vIneyu;

	public Derevo(int kolichestvoListev) {
		this.listya = new ArrayList<>();
		for (int i = 0; i < kolichestvoListev; i++) {
			listya.add(new Listok("зеленый"));
		}
	}

	// Метод: зацвести
	public void zacvesti() {
		vCvetu = true;
		System.out.println("Дерево зацвело!");
	}

	// Метод: опасть листьям
	public void opastListyam() {
		for (Listok l : listya) {
			l.setOnTree(false);
		}
		System.out.println("Листья опали.");
	}

	// Метод: покрыться инеем
	public void pokrytsyaIneyem() {
		vIneyu = true;
		System.out.println("Дерево покрылось инеем.");
	}

	// Метод: пожелтеть листьям
	public void pozeletListyam() {
		for (Listok l : listya) {
			if (l.isOnTree()) {
				l.setColor("желтый");
			}
		}
		System.out.println("Листья пожелтели.");
	}

	@Override
	public String toString() {
		return "Дерево {в цвету=" + vCvetu +
				", в инее=" + vIneyu +
				", листья=" + listya + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Derevo))
			return false;
		Derevo derevo = (Derevo) o;
		return vCvetu == derevo.vCvetu &&
				vIneyu == derevo.vIneyu &&
				Objects.equals(listya, derevo.listya);
	}

	@Override
	public int hashCode() {
		return Objects.hash(listya, vCvetu, vIneyu);
	}
}

// Тест
public class Tree {
	public static void main(String[] args) {
		Derevo bereza = new Derevo(5);

		System.out.println(bereza);
		bereza.zacvesti();
		bereza.pozeletListyam();
		System.out.println(bereza);

		bereza.opastListyam();
		bereza.pokrytsyaIneyem();
		System.out.println(bereza);
	}
}
