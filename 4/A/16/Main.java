import java.util.Arrays;
import java.util.Objects;

// Класс Лепесток
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

// Класс Бутон
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // цветет или нет

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
        return "Нет лепестков";
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

// Класс Роза
class Rose {
    private String name;
    private Bud bud;

    public Rose(String name, Bud bud) {
        this.name = name;
        this.bud = bud;
    }

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
    }
}
