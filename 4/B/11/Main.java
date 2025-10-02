public class Main {
    public static void main(String[] args) {
        Bus bus1 = new Bus("Bus-101");
        Trolleybus trol1 = new Trolleybus("Trol-12");
        Tram tram1 = new Tram("Tram-7");

        Route route1 = new Route("Маршрут 1", bus1, trol1, 15);
        Route route2 = new Route("Маршрут 2", tram1, null, 20);

        // Симуляция работы
        route1.startRoute();
        bus1.reportBreakdown();
        route1.startRoute();
        route2.startRoute();
    }
}
