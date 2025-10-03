public class Main {
public static void main(String[] args) {
        // Создаем квадрат со стороной 4, нижний левый угол в (0,0)
        Square square = new Square(0, 0, 4);
        square.printInfo();

        System.out.println("\nРастягиваем квадрат в 1.5 раза:");
        square.scale(1.5);
        square.printInfo();

        System.out.println("\nПоворачиваем квадрат на 45 градусов:");
        square.rotate(45);
        square.printInfo();

        System.out.println("\nИзменяем цвет на красный:");
        square.setColor("Red");
        square.printInfo();

        System.out.println("\nУстанавливаем размер стороны 10:");
        square.setSize(10);
        square.printInfo();
    }
}
