import java.util.Objects;

public class Hour {
    private int hour;

    public Hour(int hour) {
        if(hour < 0 || hour > 23) throw new IllegalArgumentException("Час должен быть 0-23");
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour < 0 || hour > 23) throw new IllegalArgumentException("Час должен быть 0-23");
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Hour)) return false;
        Hour h = (Hour) o;
        return hour == h.hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour);
    }

    @Override
    public String toString() {
        return String.format("%02d", hour);
    }
}
