class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Смещение точки на dx, dy
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    // Поворот точки вокруг origin на angle радианов
    public void rotate(Point origin, double angle) {
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        // Сдвигаем точку в нулевую систему от origin
        double translatedX = x - origin.x;
        double translatedY = y - origin.y;

        // Поворот
        double rotatedX = translatedX * cos - translatedY * sin;
        double rotatedY = translatedX * sin + translatedY * cos;

        // Возврат в исходную систему координат
        x = rotatedX + origin.x;
        y = rotatedY + origin.y;
    }

    // Масштабирование относительно origin с коэффициентом scale
    public void scale(Point origin, double scale) {
        x = origin.x + (x - origin.x) * scale;
        y = origin.y + (y - origin.y) * scale;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}
