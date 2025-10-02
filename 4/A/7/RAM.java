import java.util.Objects;

public class RAM {
    private int size;

    public RAM(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RAM)) return false;
        RAM ram = (RAM) o;
        return size == ram.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "RAM{" + "size=" + size + "GB}";
    }
}
