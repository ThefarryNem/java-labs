public class Bus extends Transport {
    public Bus(String id) { super(id); }
    @Override
    public void move() { System.out.println("Автобус " + id + " движется."); }
}
