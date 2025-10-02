public class Main {
    public static void main(String[] args) {
        Winchester hdd = new Winchester(512);
        DiskDrive drive = new DiskDrive("DVD");
        RAM ram = new RAM(16);

        Computer pc = new Computer(hdd, drive, ram);

        pc.turnOn();
        pc.checkViruses();
        pc.printWinchesterSize();
        pc.turnOff();

        System.out.println(pc);
    }
}
