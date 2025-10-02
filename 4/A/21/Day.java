public class Day {
    private Hour hour;
    private Minute minute;

    public Day(Hour hour, Minute minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // --- Методы ---
    public void printTime() {
        System.out.println("Текущее время: " + hour + ":" + minute);
    }

    public String getTimeOfDay() {
        int h = hour.getHour();
        if(h >= 6 && h < 12) return "Утро";
        else if(h >= 12 && h < 18) return "День";
        else if(h >= 18 && h < 23) return "Вечер";
        else return "Ночь";
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Day)) return false;
        Day d = (Day) o;
        return hour.equals(d.hour) && minute.equals(d.minute);
    }

    @Override
    public int hashCode() {
        return hour.hashCode() * 31 + minute.hashCode();
    }

    @Override
    public String toString() {
        return hour + ":" + minute + " (" + getTimeOfDay() + ")";
    }
}
