class Krylya {
private String material; // материал крыла
    private double span;     // размах крыла в метрах

    public Krylya(String material, double span) {
        this.material = material;
        this.span = span;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSpan(double span) {
        this.span = span;
    }

    public String getMaterial() {
        return material;
    }

    public double getSpan() {
        return span;
    }
}

// Класс Птица
class Ptitsa {
    private String species;     // вид птицы
    private Krylya krylya;       // объект Крылья

    public Ptitsa(String species, Krylya krylya) {
        this.species = species;
        this.krylya = krylya;
    }

    public void letat() {
        System.out.println(species + " летит, размах крыльев: " + krylya.getSpan() + " м");
    }

    public void pitatsya() {
        System.out.println(species + " ищет пищу...");
    }

    public void setKrylya(Krylya krylya) {
        this.krylya = krylya;
    }

    public String getSpecies() {
        return species;
    }
}

// Основной класс для тестирования
public class Main {
    public static void main(String[] args) {
        Krylya krylyaOrla = new Krylya("Перья", 2.5);
        Ptitsa sova = new Ptitsa("Сова", krylyaOrla);

        sova.letat();
        sova.pitatsya();

        // Можно изменить крылья
        Krylya krylyaGansa = new Krylya("Перье", 1.2);
        sova.setKrylya(krylyaGansa);

        System.out.println("\nПосле замены крыльев:");
        sova.letat();
    }
}
