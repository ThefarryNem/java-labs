import java.util.Objects;

class Krylo {
    private double razmah; // размах крыла
    private double ploshad; // площадь крыла

    public Krylo(double razmah, double ploshad) {
        this.razmah = razmah;
        this.ploshad = ploshad;
    }

    public double getRazmah() {
        return razmah;
    }

    public void setRazmah(double razmah) {
        this.razmah = razmah;
    }

    public double getPloshad() {
        return ploshad;
    }

    public void setPloshad(double ploshad) {
        this.ploshad = ploshad;
    }

    @Override
    public String toString() {
        return "Крыло {размах=" + razmah + " м, площадь=" + ploshad + " м²}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Krylo)) return false;
        Krylo krylo = (Krylo) o;
        return Double.compare(krylo.razmah, razmah) == 0 &&
                Double.compare(krylo.ploshad, ploshad) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(razmah, ploshad);
    }
}

// Класс Самолет
class Samolet {
    private String marshrut;
    private Krylo krylo;

    public Samolet(Krylo krylo) {
        this.krylo = krylo;
    }

    public void zadatMarshrut(String marshrut) {
        this.marshrut = marshrut;
    }

    public void vyvestiMarshrut() {
        if (marshrut != null) {
            System.out.println("Маршрут самолета: " + marshrut);
        } else {
            System.out.println("Маршрут не задан.");
        }
    }

    public void letat() {
        if (marshrut != null) {
            System.out.println("Самолет летит по маршруту: " + marshrut);
        } else {
            System.out.println("Самолет готов к полету, но маршрут не задан.");
        }
    }

    @Override
    public String toString() {
        return "Самолет {маршрут='" + marshrut + "', " + krylo + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Samolet)) return false;
        Samolet samolet = (Samolet) o;
        return Objects.equals(marshrut, samolet.marshrut) &&
                Objects.equals(krylo, samolet.krylo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marshrut, krylo);
    }
}
