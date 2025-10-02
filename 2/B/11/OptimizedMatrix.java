import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OptimizedMatrix {
  public static void main(String[] args) {
    int y = 0;
    int x = 0;

    var scanner = new Scanner(System.in);
    System.out.print("enter y: ");
    y = scanner.nextInt();

    System.out.print("enter x: ");
    x = scanner.nextInt();

    scanner.close();

    var matrix = new ArrayList<ArrayList<Integer>>();
    var rng = new Random();

    for (int i = 0; i < y; i++) {
      matrix.add(new ArrayList<Integer>());
      for (int j = 0; j < x; j++) {
        // генерирует 0 или 1
        // и помещает значение в матрицу
        int value = rng.nextInt(2);
        matrix.get(i).add(value);
        System.out.print(value + " ");
      }

      System.out.println();
    }

    System.out.println();

    // удаление строк
    for (int i = y - 1; i >= 0; i--) {
      int rowSum = 0;
      for (int j = 0; j < x; j++) {
        rowSum += matrix.get(i).get(j);
      }

      if (rowSum == 0) {
        matrix.remove(i);
        y--;
      }
    }

    // запись пустых рядов
    var emptyColoums = new ArrayList<Integer>();
    for (int j = x - 1; j >= 0; j--) {
      int coloumnSum = 0;
      for (int i = 0; i < y; i++) {
        coloumnSum += matrix.get(i).get(j);
      }

      if (coloumnSum == 0) {
        emptyColoums.add(j);
      }
    }

    // удаление рядов
    for (int j : emptyColoums) {
      for (int i = 0; i < y; i++) {
        matrix.get(i).remove(j);
      }
    }

    // вывод
    for (var row : matrix) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
}
