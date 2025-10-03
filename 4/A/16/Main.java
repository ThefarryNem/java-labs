import java.util.Arrays;
import java.util.Objects;

<<<<<<< HEAD
// ÐšÐ»Ð°ÑÑ Ð›ÐµÐ¿ÐµÑÑ‚Ð¾Ðº
=======
// Êëàññ Ëåïåñòîê
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
// ÐšÐ»Ð°ÑÑ Ð‘ÑƒÑ‚Ð¾Ð½
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // Ñ†Ð²ÐµÑ‚ÐµÑ‚ Ð¸Ð»Ð¸ Ð½ÐµÑ‚
=======
// Êëàññ Áóòîí
class Bud {
    private Petal[] petals;
    private boolean isBloomed; // öâåòåò èëè íåò
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
        return "ÐÐµÑ‚ Ð»ÐµÐ¿ÐµÑÑ‚ÐºÐ¾Ð²";
=======
        return "Íåò ëåïåñòêîâ";
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
// ÐšÐ»Ð°ÑÑ Ð Ð¾Ð·Ð°
=======
// Êëàññ Ðîçà
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
class Rose {
    private String name;
    private Bud bud;

    public Rose(String name, Bud bud) {
        this.name = name;
        this.bud = bud;
    }

<<<<<<< HEAD
    // ÐœÐµÑ‚Ð¾Ð´ Ñ€Ð°ÑÑ†Ð²ÐµÑÑ‚Ð¸ â€” Ð¿Ñ€Ð¸Ð½Ð°Ð´Ð»ÐµÐ¶Ð¸Ñ‚ ÐºÐ»Ð°ÑÑÑƒ Ð Ð¾Ð·Ð°, Ñ‚.Ðº. ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÐµÑ‚ Ð±ÑƒÑ‚Ð¾Ð½Ð¾Ð¼
    public void bloom() {
        bud.bloom();
        System.out.println(name + " Ñ€Ð°ÑÑ†Ð²ÐµÐ»Ð°!");
    }

    // ÐœÐµÑ‚Ð¾Ð´ Ð·Ð°Ð²ÑÑ‚ÑŒ â€” Ð¿Ñ€Ð¸Ð½Ð°Ð´Ð»ÐµÐ¶Ð¸Ñ‚ ÐºÐ»Ð°ÑÑÑƒ Ð Ð¾Ð·Ð°
    public void wither() {
        bud.wither();
        System.out.println(name + " Ð·Ð°Ð²ÑÐ»Ð°!");
    }

    // ÐœÐµÑ‚Ð¾Ð´ Ð²Ñ‹Ð²ÐµÑÑ‚Ð¸ Ñ†Ð²ÐµÑ‚ Ð±ÑƒÑ‚Ð¾Ð½Ð° â€” Ð¿Ñ€Ð¸Ð½Ð°Ð´Ð»ÐµÐ¶Ð¸Ñ‚ Ð Ð¾Ð·Ðµ, Ð½Ð¾ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ð¼ÐµÑ‚Ð¾Ð´ Ð±ÑƒÑ‚Ð¾Ð½Ð°
    public void printBudColor() {
        System.out.println("Ð¦Ð²ÐµÑ‚ Ð±ÑƒÑ‚Ð¾Ð½Ð°: " + bud.getColor());
=======
    // Ìåòîä ðàñöâåñòè — ïðèíàäëåæèò êëàññó Ðîçà, ò.ê. óïðàâëÿåò áóòîíîì
    public void bloom() {
        bud.bloom();
        System.out.println(name + " ðàñöâåëà!");
    }

    // Ìåòîä çàâÿòü — ïðèíàäëåæèò êëàññó Ðîçà
    public void wither() {
        bud.wither();
        System.out.println(name + " çàâÿëà!");
    }

    // Ìåòîä âûâåñòè öâåò áóòîíà — ïðèíàäëåæèò Ðîçå, íî èñïîëüçóåò ìåòîä áóòîíà
    public void printBudColor() {
        System.out.println("Öâåò áóòîíà: " + bud.getColor());
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
// ÐŸÑ€Ð¸Ð»Ð¾Ð¶ÐµÐ½Ð¸Ðµ
public class Main {
    public static void main(String[] args) {
        Petal p1 = new Petal("ÐšÑ€Ð°ÑÐ½Ñ‹Ð¹");
        Petal p2 = new Petal("ÐšÑ€Ð°ÑÐ½Ñ‹Ð¹");
        Petal[] petals = {p1, p2};

        Bud bud = new Bud(petals);
        Rose rose = new Rose("ÐœÐ¾ÑÐ Ð¾Ð·Ð°", bud);

        rose.printBudColor();  // Ð¦Ð²ÐµÑ‚ Ð±ÑƒÑ‚Ð¾Ð½Ð°
        rose.bloom();          // Ð Ð°ÑÑ†Ð²ÐµÑÑ‚Ð¸
        System.out.println(rose); // Ð¡Ñ‚Ð°Ñ‚ÑƒÑ
        rose.wither();         // Ð—Ð°Ð²ÑÑ‚ÑŒ
        System.out.println(rose); // Ð¡Ñ‚Ð°Ñ‚ÑƒÑ Ð¿Ð¾ÑÐ»Ðµ ÑƒÐ²ÑÐ´Ð°Ð½Ð¸Ñ
=======
// Ïðèëîæåíèå
public class Main {
    public static void main(String[] args) {
        Petal p1 = new Petal("Êðàñíûé");
        Petal p2 = new Petal("Êðàñíûé");
        Petal[] petals = {p1, p2};

        Bud bud = new Bud(petals);
        Rose rose = new Rose("ÌîÿÐîçà", bud);

        rose.printBudColor();  // Öâåò áóòîíà
        rose.bloom();          // Ðàñöâåñòè
        System.out.println(rose); // Ñòàòóñ
        rose.wither();         // Çàâÿòü
        System.out.println(rose); // Ñòàòóñ ïîñëå óâÿäàíèÿ
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    }
}
