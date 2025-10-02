import java.util.Objects;

public class Computer {
    private Winchester winchester;
    private DiskDrive diskDrive;
    private RAM ram;

    public Computer(Winchester winchester, DiskDrive diskDrive, RAM ram) {
        this.winchester = winchester;
        this.diskDrive = diskDrive;
        this.ram = ram;
    }

    public void turnOn() {
        System.out.println("Компьютер включен.");
    }

    public void turnOff() {
        System.out.println("Компьютер выключен.");
    }

    public void checkViruses() {
        System.out.println("Проверка на вирусы завершена. Угроз не обнаружено.");
    }

    public void printWinchesterSize() {
        System.out.println("Размер винчестера: " + winchester.getCapacity() + " GB");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer)) return false;
        Computer computer = (Computer) o;
        return Objects.equals(winchester, computer.winchester) &&
                Objects.equals(diskDrive, computer.diskDrive) &&
                Objects.equals(ram, computer.ram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winchester, diskDrive, ram);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "winchester=" + winchester +
                ", diskDrive=" + diskDrive +
                ", ram=" + ram +
                '}';
    }
}
