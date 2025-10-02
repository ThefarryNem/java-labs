public class Main {
    public static void main(String[] args) {
        Hour h = new Hour(14);
        Minute m = new Minute(35);

        Day day = new Day(h, m);

        day.printTime();
        System.out.println("Время суток: " + day.getTimeOfDay());

        System.out.println(day);
    }
}
