import java.util.Scanner;

class VectorR3 {
    private double x, y, z;

    public VectorR3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // ��������� ������������
    public double dot(VectorR3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    // ��������� ������������
    public VectorR3 cross(VectorR3 v) {
        return new VectorR3(
            y * v.z - z * v.y,
            z * v.x - x * v.z,
            x * v.y - y * v.x
        );
    }

    // ����� �������
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // �������� �� ���������������
    public boolean isOrthogonal(VectorR3 v) {
        return Math.abs(this.dot(v)) < 1e-9;
    }

    // �������� �� �������������� (���� ��������� ������������ = 0)
    public boolean isCollinear(VectorR3 v) {
        VectorR3 cross = this.cross(v);
        return Math.abs(cross.x) < 1e-9 &&
               Math.abs(cross.y) < 1e-9 &&
               Math.abs(cross.z) < 1e-9;
    }

    // ��������� �� �����
    public int compareTo(VectorR3 v) {
        return Double.compare(this.length(), v.length());
    }

    // ��������� ������������ (��� �������� �������������� ��� ��������)
    public static double mixedProduct(VectorR3 a, VectorR3 b, VectorR3 c) {
        return a.dot(b.cross(c));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("������� ���������� �������� m: ");
        int m = sc.nextInt();

        VectorR3[] vectors = new VectorR3[m];

        System.out.println("������� ���������� ��������:");
        for (int i = 0; i < m; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            vectors[i] = new VectorR3(x, y, z);
        }

        // ������� ������
        System.out.println("\n�������� �������:");
        for (int i = 0; i < m; i++) {
            System.out.println("v" + (i+1) + " = " + vectors[i] + ", |v| = " + vectors[i].length());
        }

        // �������� ��� ������ ����
        System.out.println("\n�������� ��� ��������:");
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                VectorR3 a = vectors[i];
                VectorR3 b = vectors[j];
                System.out.println("���� v" + (i+1) + " � v" + (j+1) + ":");
                System.out.println("  ������������? " + a.isOrthogonal(b));
                System.out.println("  �����������? " + a.isCollinear(b));
                int cmp = a.compareTo(b);
                if (cmp == 0)
                    System.out.println("  ������� ����� �� �����.");
                else if (cmp < 0)
                    System.out.println("  v" + (i+1) + " ������, ��� v" + (j+1));
                else
                    System.out.println("  v" + (i+1) + " �������, ��� v" + (j+1));
            }
        }

        // �������� �������������� �����
        System.out.println("\n������������ ������ ��������:");
        boolean found = false;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    double mixed = VectorR3.mixedProduct(vectors[i], vectors[j], vectors[k]);
                    if (Math.abs(mixed) < 1e-9) {
                        System.out.println("  v" + (i+1) + ", v" + (j+1) + ", v" + (k+1));
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("  ������������ ����� ���.");
        }

        sc.close();
    }
}
