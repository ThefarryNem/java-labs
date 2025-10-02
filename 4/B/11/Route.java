public class Route {
    private String name;
    private Transport mainTransport;
    private Transport reserveTransport;
    private int intervalMinutes;

    public Route(String name, Transport mainTransport, Transport reserveTransport, int intervalMinutes) {
        this.name = name;
        this.mainTransport = mainTransport;
        this.reserveTransport = reserveTransport;
        this.intervalMinutes = intervalMinutes;
    }

    public void startRoute() {
        System.out.println("Маршрут " + name + " стартует с интервалом " + intervalMinutes + " минут.");
        if(!mainTransport.isBroken()) {
            mainTransport.move();
        } else {
            handleBreakdown();
        }
    }

    public void handleBreakdown() {
        System.out.println("Главный транспорт сломался на маршруте " + name);
        if(reserveTransport != null && !reserveTransport.isBroken()) {
            System.out.println("Используется резервный транспорт:");
            reserveTransport.move();
        } else {
            System.out.println("Резервного транспорта нет или он сломан. Интервал движения увеличен.");
            intervalMinutes += 10;
        }
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", mainTransport=" + mainTransport +
                ", reserveTransport=" + reserveTransport +
                ", intervalMinutes=" + intervalMinutes +
                '}';
    }
}
