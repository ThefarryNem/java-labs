import java.util.Objects;

public class DiskDrive {
    private String type;

    public DiskDrive(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiskDrive)) return false;
        DiskDrive that = (DiskDrive) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "DiskDrive{" + "type='" + type + '\'' + '}';
    }
}
