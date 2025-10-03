import java.util.Objects;
import java.util.Arrays;

// ����� ������
class Wheel {
    private String type;   // ��� ������ (������, ������ � �.�.)
    private int diameter;  // �������

    public Wheel(String type, int diameter) {
        this.type = type;
        this.diameter = diameter;
    }

    public String getType() {
        return type;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wheel)) return false;
        Wheel wheel = (Wheel) o;
        return diameter == wheel.diameter &&
                Objects.equals(type, wheel.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, diameter);
    }

    @Override
    public String toString() {
        return "Wheel{type='" + type + "', diameter=" + diameter + "}";
    }
}

// ����� ����������
class Car {
    private String brand;   // �����
    private Wheel[] wheels; // ������ ����
    private double fuel;    // ������� �������

    public Car(String brand, Wheel wheelTemplate, int wheelCount) {
        this.brand = brand;
        this.wheels = new Wheel[wheelCount];
        for (int i = 0; i < wheelCount; i++) {
            wheels[i] = new Wheel(wheelTemplate.getType(), wheelTemplate.getDiameter());
        }
        this.fuel = 0.0;
    }

    // ����� �����
    public void drive() {
        if (fuel > 0) {
            System.out.println(brand + " ����!");
            fuel -= 1.0;
        } else {
            System.out.println("��� �������! ����� �����������.");
        }
    }

    // ����� ������������
    public void refuel(double amount) {
        fuel += amount;
        System.out.println("������ " + brand + " ����������. ������� = " + fuel);
    }

    // ����� ������ ������
    public void changeWheel(int index, Wheel newWheel) {
        if (index >= 0 && index < wheels.length) {
            wheels[index] = newWheel;
            System.out.println("������ #" + (index + 1) + " �������� �� " + newWheel);
        } else {
            System.out.println("�������� ������ ������!");
        }
    }

    // ������� �����
    public void printBrand() {
        System.out.println("����� ����������: " + brand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Double.compare(car.fuel, fuel) == 0 &&
                Objects.equals(brand, car.brand) &&
                Objects.deepEquals(wheels, car.wheels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, fuel, Arrays.hashCode(wheels));
    }

    @Override
    public String toString() {
        return "Car{brand='" + brand + "', fuel=" + fuel + ", wheels=" + Arrays.toString(wheels) + "}";
    }
}

// ����������
public class Main {
    public static void main(String[] args) {
        Wheel summerWheel = new Wheel("������", 16);
        Car car = new Car("Toyota", summerWheel, 4);

        car.printBrand();             // ������� �����
        car.drive();                  // ������� ����� ��� �������
        car.refuel(10);               // ����������
        car.drive();                  // ����
        car.changeWheel(2, new Wheel("������", 16)); // ������ ������

        System.out.println(car);      // ������ ��������� ����
    }
}
