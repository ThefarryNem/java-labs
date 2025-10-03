import java.util.Arrays;
import java.util.Objects;

// ����� ��������
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

// ����� �����
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // ������ ��� ���

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
        return "��� ���������";
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

// ����� ����
class Rose {
    private String name;
    private Bud bud;

    public Rose(String name, Bud bud) {
        this.name = name;
        this.bud = bud;
    }

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
    }
}
