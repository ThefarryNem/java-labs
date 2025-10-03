import java.util.Objects;
import java.util.Arrays;

// Класс Колесо
class Wheel {
    private String type;   // тип колеса (летнее, зимнее и т.п.)
    private int diameter;  // диаметр

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

// Класс Автомобиль
class Car {
    private String brand;   // марка
    private Wheel[] wheels; // массив колёс
    private double fuel;    // уровень топлива

    public Car(String brand, Wheel wheelTemplate, int wheelCount) {
        this.brand = brand;
        this.wheels = new Wheel[wheelCount];
        for (int i = 0; i < wheelCount; i++) {
            wheels[i] = new Wheel(wheelTemplate.getType(), wheelTemplate.getDiameter());
        }
        this.fuel = 0.0;
    }

    // метод ехать
    public void drive() {
        if (fuel > 0) {
            System.out.println(brand + " едет!");
            fuel -= 1.0;
        } else {
            System.out.println("Нет топлива! Нужно заправиться.");
        }
    }

    // метод заправляться
    public void refuel(double amount) {
        fuel += amount;
        System.out.println("Машина " + brand + " заправлена. Топливо = " + fuel);
    }

    // метод менять колесо
    public void changeWheel(int index, Wheel newWheel) {
        if (index >= 0 && index < wheels.length) {
            wheels[index] = newWheel;
            System.out.println("Колесо #" + (index + 1) + " заменено на " + newWheel);
        } else {
            System.out.println("Неверный индекс колеса!");
        }
    }

    // вывести марку
    public void printBrand() {
        System.out.println("Марка автомобиля: " + brand);
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

// Приложение
public class Main {
    public static void main(String[] args) {
        Wheel summerWheel = new Wheel("летнее", 16);
        Car car = new Car("Toyota", summerWheel, 4);

        car.printBrand();             // вывести марку
        car.drive();                  // пробуем ехать без топлива
        car.refuel(10);               // заправляем
        car.drive();                  // едем
        car.changeWheel(2, new Wheel("зимнее", 16)); // меняем колесо

        System.out.println(car);      // печать состояния авто
    }
}
