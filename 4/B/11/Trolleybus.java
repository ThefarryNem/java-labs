public class Trolleybus extends Transport {
    public Trolleybus(String id) { super(id); }
    @Override
    public void move() { System.out.println("Троллейбус " + id + " движется."); }
}
