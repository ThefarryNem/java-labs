class Segment {
    Point start, end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public double length() {
        return Math.hypot(end.x - start.x, end.y - start.y);
    }
}

class Square {
    private Point[] vertices = new Point[4];  // 4 вершины в порядке обхода
    private String color;

    // Конструктор: квадрат с нижним левым углом в (x, y) и длиной стороны side
    public Square(double x, double y, double side) {
        vertices[0] = new Point(x, y);               // нижний левый
        vertices[1] = new Point(x + side, y);        // нижний правый
        vertices[2] = new Point(x + side, y + side); // верхний правый
        vertices[3] = new Point(x, y + side);        // верхний левый
        color = "Black"; // цвет по умолчанию
    }

    // Задание размера стороны - изменяем вершины, масштабируя относительно центра
    public void setSize(double side) {
        Point center = getCenter();
        double currentSide = vertices[1].x - vertices[0].x; // исходная длина стороны по X
        double scale = side / currentSide;
        scale(scale);
    }

    // Масштабирование (растяжение/сжатие) относительно центра
    public void scale(double scale) {
        Point center = getCenter();
        for (Point p : vertices) {
            p.scale(center, scale);
        }
    }

    // Поворот квадрата относительно центра на угол degrees
    public void rotate(double degrees) {
        double radians = Math.toRadians(degrees);
        Point center = getCenter();
        for (Point p : vertices) {
            p.rotate(center, radians);
        }
    }

    // Получить центр квадрата
    private Point getCenter() {
        double centerX = 0, centerY = 0;
        for (Point p : vertices) {
            centerX += p.x;
            centerY += p.y;
        }
        centerX /= 4;
        centerY /= 4;
        return new Point(centerX, centerY);
    }

    // Изменение цвета
    public void setColor(String color) {
        this.color = color;
    }

    // Вывод информации о квадрате
    public void printInfo() {
        System.out.println("Квадрат:");
        for (int i = 0; i < 4; i++) {
            System.out.println("Вершина " + (i + 1) + ": " + vertices[i]);
        }
        System.out.println("Цвет: " + color);
    }
}
