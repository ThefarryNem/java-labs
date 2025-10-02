// Тест
public class Main {
    public static void main(String[] args) {
        Krylo krylo1 = new Krylo(35.8, 122.4);
        Samolet boeing = new Samolet(krylo1);

        boeing.zadatMarshrut("Москва - Нью-Йорк");
        boeing.vyvestiMarshrut();
        boeing.letat();

        System.out.println(boeing);
    }
}

