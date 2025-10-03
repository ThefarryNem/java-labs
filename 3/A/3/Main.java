import java.util.Scanner;

class VectorR3 {
    private double x, y, z;

    public VectorR3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

<<<<<<< HEAD
    // ÑÐºÐ°Ð»ÑÑ€Ð½Ð¾Ðµ Ð¿Ñ€Ð¾Ð¸Ð·Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ
=======
    // ñêàëÿðíîå ïðîèçâåäåíèå
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    public double dot(VectorR3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

<<<<<<< HEAD
    // Ð²ÐµÐºÑ‚Ð¾Ñ€Ð½Ð¾Ðµ Ð¿Ñ€Ð¾Ð¸Ð·Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ
    public VectorR3 cross(VectorR3 v) {
        return new VectorR3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    // Ð´Ð»Ð¸Ð½Ð° Ð²ÐµÐºÑ‚Ð¾Ñ€Ð°
=======
    // âåêòîðíîå ïðîèçâåäåíèå
    public VectorR3 cross(VectorR3 v) {
        return new VectorR3(
            y * v.z - z * v.y,
            z * v.x - x * v.z,
            x * v.y - y * v.x
        );
    }

    // äëèíà âåêòîðà
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

<<<<<<< HEAD
    // Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð½Ð° Ð¾Ñ€Ñ‚Ð¾Ð³Ð¾Ð½Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ
=======
    // ïðîâåðêà íà îðòîãîíàëüíîñòü
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    public boolean isOrthogonal(VectorR3 v) {
        return Math.abs(this.dot(v)) < 1e-9;
    }

<<<<<<< HEAD
    // Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð½Ð° ÐºÐ¾Ð»Ð»Ð¸Ð½ÐµÐ°Ñ€Ð½Ð¾ÑÑ‚ÑŒ (ÐµÑÐ»Ð¸ Ð²ÐµÐºÑ‚Ð¾Ñ€Ð½Ð¾Ðµ Ð¿Ñ€Ð¾Ð¸Ð·Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ = 0)
    public boolean isCollinear(VectorR3 v) {
        VectorR3 cross = this.cross(v);
        return Math.abs(cross.x) < 1e-9 &&
                Math.abs(cross.y) < 1e-9 &&
                Math.abs(cross.z) < 1e-9;
    }

    // ÑÑ€Ð°Ð²Ð½ÐµÐ½Ð¸Ðµ Ð¿Ð¾ Ð´Ð»Ð¸Ð½Ðµ
=======
    // ïðîâåðêà íà êîëëèíåàðíîñòü (åñëè âåêòîðíîå ïðîèçâåäåíèå = 0)
    public boolean isCollinear(VectorR3 v) {
        VectorR3 cross = this.cross(v);
        return Math.abs(cross.x) < 1e-9 &&
               Math.abs(cross.y) < 1e-9 &&
               Math.abs(cross.z) < 1e-9;
    }

    // ñðàâíåíèå ïî äëèíå
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
    public int compareTo(VectorR3 v) {
        return Double.compare(this.length(), v.length());
    }

<<<<<<< HEAD
    // ÑÐ¼ÐµÑˆÐ°Ð½Ð½Ð¾Ðµ Ð¿Ñ€Ð¾Ð¸Ð·Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ (Ð´Ð»Ñ Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ¸ ÐºÐ¾Ð¼Ð¿Ð»Ð°Ð½Ð°Ñ€Ð½Ð¾ÑÑ‚Ð¸ Ñ‚Ñ€Ñ‘Ñ… Ð²ÐµÐºÑ‚Ð¾Ñ€Ð¾Ð²)
=======
    // ñìåøàííîå ïðîèçâåäåíèå (äëÿ ïðîâåðêè êîìïëàíàðíîñòè òð¸õ âåêòîðîâ)
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
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

<<<<<<< HEAD
        System.out.print("Ð’Ð²ÐµÐ´Ð¸Ñ‚Ðµ ÐºÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð²ÐµÐºÑ‚Ð¾Ñ€Ð¾Ð² m: ");
=======
        System.out.print("Ââåäèòå êîëè÷åñòâî âåêòîðîâ m: ");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        int m = sc.nextInt();

        VectorR3[] vectors = new VectorR3[m];

<<<<<<< HEAD
        System.out.println("Ð’Ð²ÐµÐ´Ð¸Ñ‚Ðµ ÐºÐ¾Ð¾Ñ€Ð´Ð¸Ð½Ð°Ñ‚Ñ‹ Ð²ÐµÐºÑ‚Ð¾Ñ€Ð¾Ð²:");
=======
        System.out.println("Ââåäèòå êîîðäèíàòû âåêòîðîâ:");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        for (int i = 0; i < m; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            double z = sc.nextDouble();
            vectors[i] = new VectorR3(x, y, z);
        }

<<<<<<< HEAD
        // Ð’Ñ‹Ð²Ð¾Ð´Ð¸Ð¼ Ð¼Ð°ÑÑÐ¸Ð²
        System.out.println("\nÐ’Ð²ÐµÐ´Ñ‘Ð½Ð½Ñ‹Ðµ Ð²ÐµÐºÑ‚Ð¾Ñ€Ñ‹:");
=======
        // Âûâîäèì ìàññèâ
        System.out.println("\nÂâåä¸ííûå âåêòîðû:");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        for (int i = 0; i < m; i++) {
            System.out.println("v" + (i+1) + " = " + vectors[i] + ", |v| = " + vectors[i].length());
        }

<<<<<<< HEAD
        // ÐŸÑ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð´Ð»Ñ ÐºÐ°Ð¶Ð´Ð¾Ð¹ Ð¿Ð°Ñ€Ñ‹
        System.out.println("\nÐŸÑ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð¿Ð°Ñ€ Ð²ÐµÐºÑ‚Ð¾Ñ€Ð¾Ð²:");
=======
        // Ïðîâåðêà äëÿ êàæäîé ïàðû
        System.out.println("\nÏðîâåðêà ïàð âåêòîðîâ:");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                VectorR3 a = vectors[i];
                VectorR3 b = vectors[j];
<<<<<<< HEAD
                System.out.println("ÐŸÐ°Ñ€Ð° v" + (i+1) + " Ð¸ v" + (j+1) + ":");
                System.out.println("  ÐžÑ€Ñ‚Ð¾Ð³Ð¾Ð½Ð°Ð»ÑŒÐ½Ñ‹? " + a.isOrthogonal(b));
                System.out.println("  ÐšÐ¾Ð»Ð»Ð¸Ð½ÐµÐ°Ñ€Ð½Ñ‹? " + a.isCollinear(b));
                int cmp = a.compareTo(b);
                if (cmp == 0)
                    System.out.println("  Ð’ÐµÐºÑ‚Ð¾Ñ€Ñ‹ Ñ€Ð°Ð²Ð½Ñ‹ Ð¿Ð¾ Ð´Ð»Ð¸Ð½Ðµ.");
                else if (cmp < 0)
                    System.out.println("  v" + (i+1) + " ÐºÐ¾Ñ€Ð¾Ñ‡Ðµ, Ñ‡ÐµÐ¼ v" + (j+1));
                else
                    System.out.println("  v" + (i+1) + " Ð´Ð»Ð¸Ð½Ð½ÐµÐµ, Ñ‡ÐµÐ¼ v" + (j+1));
            }
        }

        // ÐŸÑ€Ð¾Ð²ÐµÑ€ÐºÐ° ÐºÐ¾Ð¼Ð¿Ð»Ð°Ð½Ð°Ñ€Ð½Ð¾ÑÑ‚Ð¸ Ñ‚Ñ€Ð¾ÐµÐº
        System.out.println("\nÐšÐ¾Ð¼Ð¿Ð»Ð°Ð½Ð°Ñ€Ð½Ñ‹Ðµ Ñ‚Ñ€Ð¾Ð¹ÐºÐ¸ Ð²ÐµÐºÑ‚Ð¾Ñ€Ð¾Ð²:");
=======
                System.out.println("Ïàðà v" + (i+1) + " è v" + (j+1) + ":");
                System.out.println("  Îðòîãîíàëüíû? " + a.isOrthogonal(b));
                System.out.println("  Êîëëèíåàðíû? " + a.isCollinear(b));
                int cmp = a.compareTo(b);
                if (cmp == 0)
                    System.out.println("  Âåêòîðû ðàâíû ïî äëèíå.");
                else if (cmp < 0)
                    System.out.println("  v" + (i+1) + " êîðî÷å, ÷åì v" + (j+1));
                else
                    System.out.println("  v" + (i+1) + " äëèííåå, ÷åì v" + (j+1));
            }
        }

        // Ïðîâåðêà êîìïëàíàðíîñòè òðîåê
        System.out.println("\nÊîìïëàíàðíûå òðîéêè âåêòîðîâ:");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
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
<<<<<<< HEAD
            System.out.println("  ÐšÐ¾Ð¼Ð¿Ð»Ð°Ð½Ð°Ñ€Ð½Ñ‹Ñ… Ñ‚Ñ€Ð¾ÐµÐº Ð½ÐµÑ‚.");
=======
            System.out.println("  Êîìïëàíàðíûõ òðîåê íåò.");
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        sc.close();
    }
}
