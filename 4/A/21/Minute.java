import java.util.Objects;

public class Minute {
    private int minute; // 0-59

    public Minute(int minute) {
        if(minute < 0 || minute > 59) throw new IllegalArgumentException("Минута должна быть 0-59");
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute < 0 || minute > 59) throw new IllegalArgumentException("Минута должна быть 0-59");
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Minute)) return false;
        Minute m = (Minute) o;
        return minute == m.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute);
    }

    @Override
    public String toString() {
        return String.format("%02d", minute);
    }
}
