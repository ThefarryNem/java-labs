public class Tram extends Transport {
    public Tram(String id) { super(id); }
    @Override
    public void move() { System.out.println("Трамвай " + id + " движется."); }
}
