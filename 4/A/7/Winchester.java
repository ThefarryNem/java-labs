import java.util.Objects;

public class Winchester {
    private int capacity; // размер в ГБ

    public Winchester(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Winchester)) return false;
        Winchester that = (Winchester) o;
        return capacity == that.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    @Override
    public String toString() {
        return "Winchester{" + "capacity=" + capacity + "GB}";
    }
}
