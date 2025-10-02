import java.util.Objects;

public abstract class Transport {
    protected String id;
    protected boolean isBroken;

    public Transport(String id) {
        this.id = id;
        this.isBroken = false;
    }

    public abstract void move();

    public void reportBreakdown() {
        isBroken = true;
        System.out.println(id + " сломался!");
    }

    public void repair() {
        isBroken = false;
        System.out.println(id + " отремонтирован.");
    }

    public boolean isBroken() {
        return isBroken;
    }

    @Override
    public String toString() {
        return id + (isBroken ? " (Сломан)" : "");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Transport)) return false;
        Transport t = (Transport) o;
        return Objects.equals(id, t.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
