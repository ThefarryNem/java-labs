import java.util.Arrays;
import java.util.Objects;

<<<<<<< HEAD
// Класс Лепесток
=======
// ����� ��������
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
class Petal {
    private String color;

    public Petal(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Petal)) return false;
        Petal petal = (Petal) o;
        return Objects.equals(color, petal.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "Petal{color='" + color + "'}";
    }
}

<<<<<<< HEAD
// Класс Бутон
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // цветет или нет
=======
// ����� �����
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // ������ ��� ���
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50

    public Bud(Petal[] petals) {
        this.petals = petals;
        this.isBloomed = false;
    }

    public void bloom() {
        isBloomed = true;
    }

    public void wither() {
        isBloomed = false;
    }

    public String getColor() {
        if (petals.length > 0)
            return petals[0].getColor();
<<<<<<< HEAD
        return "Нет лепестков";
=======
        return "��� ���������";
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    }

    public boolean isBloomed() {
        return isBloomed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bud)) return false;
        Bud bud = (Bud) o;
        return isBloomed == bud.isBloomed &&
                Arrays.equals(petals, bud.petals);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isBloomed);
        result = 31 * result + Arrays.hashCode(petals);
        return result;
    }

    @Override
    public String toString() {
        return "Bud{petals=" + Arrays.toString(petals) +
                ", isBloomed=" + isBloomed + "}";
    }
}

<<<<<<< HEAD
// Класс Роза
=======
// ����� ����
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
class Rose {
    private String name;
    private Bud bud;

    public Rose(String name, Bud bud) {
        this.name = name;
        this.bud = bud;
    }

<<<<<<< HEAD
    // Метод расцвести — принадлежит классу Роза, т.к. управляет бутоном
    public void bloom() {
        bud.bloom();
        System.out.println(name + " расцвела!");
    }

    // Метод завять — принадлежит классу Роза
    public void wither() {
        bud.wither();
        System.out.println(name + " завяла!");
    }

    // Метод вывести цвет бутона — принадлежит Розе, но использует метод бутона
    public void printBudColor() {
        System.out.println("Цвет бутона: " + bud.getColor());
=======
    // ����� ��������� � ����������� ������ ����, �.�. ��������� �������
    public void bloom() {
        bud.bloom();
        System.out.println(name + " ��������!");
    }

    // ����� ������ � ����������� ������ ����
    public void wither() {
        bud.wither();
        System.out.println(name + " ������!");
    }

    // ����� ������� ���� ������ � ����������� ����, �� ���������� ����� ������
    public void printBudColor() {
        System.out.println("���� ������: " + bud.getColor());
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rose)) return false;
        Rose rose = (Rose) o;
        return Objects.equals(name, rose.name) &&
                Objects.equals(bud, rose.bud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bud);
    }

    @Override
    public String toString() {
        return "Rose{name='" + name + "', bud=" + bud + "}";
    }
}

<<<<<<< HEAD
// Приложение
public class Main {
    public static void main(String[] args) {
        Petal p1 = new Petal("Красный");
        Petal p2 = new Petal("Красный");
        Petal[] petals = {p1, p2};

        Bud bud = new Bud(petals);
        Rose rose = new Rose("МояРоза", bud);

        rose.printBudColor();  // Цвет бутона
        rose.bloom();          // Расцвести
        System.out.println(rose); // Статус
        rose.wither();         // Завять
        System.out.println(rose); // Статус после увядания
=======
// ����������
public class Main {
    public static void main(String[] args) {
        Petal p1 = new Petal("�������");
        Petal p2 = new Petal("�������");
        Petal[] petals = {p1, p2};

        Bud bud = new Bud(petals);
        Rose rose = new Rose("�������", bud);

        rose.printBudColor();  // ���� ������
        rose.bloom();          // ���������
        System.out.println(rose); // ������
        rose.wither();         // ������
        System.out.println(rose); // ������ ����� ��������
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    }
}
