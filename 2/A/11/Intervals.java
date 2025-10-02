import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Intervals {
  public static void main(String[] args) {
    String dateGiven = "2025-09-09 09:00";
    System.out.println("Дата получения задания: " + dateGiven);
    System.out.println("Дата сдачи: " + new Date());
    int i = getUserInputClamped();

    Random rng = new Random();
    int k = rng.nextInt(1, 10);

    boolean inFirstRange = -10 * k < i && i <= 0;
    boolean inSecondRange = 0 < i && i <= 5;
    boolean inThirdRange = 5 < i && i <= 10;
    boolean inFourthRange = 10 < i && i <= 10 * k;

    System.out.println("k = " + k);

    // извиняюсь за данный код.
    // по условию нужно использовать switch
    switch (inFirstRange ? 1 : 0) {
      case 1:
        System.out.println("in (-10k, 0]");
        break;
      default:
        break;
    }

    switch (inSecondRange ? 1 : 0) {
      case 1:
        System.out.println("in (0, 5]");
        break;
      default:
        break;
    }

    switch (inThirdRange ? 1 : 0) {
      case 1:
        System.out.println("in (5, 10]");
        break;
      default:
        break;
    }

    switch (inFourthRange ? 1 : 0) {
      case 1:
        System.out.println("in (10, 10k]");
        break;
      default:
        break;
    }

  }

  /**
   * @return Введённое значение, ограниченое диапазоном [-100, 100].
   */
  private static int getUserInputClamped() {
    System.out.print("enter i (-100..100): ");
    var scanner = new Scanner(System.in);
    String input = scanner.next();

    int i = Integer.parseInt(input);
    scanner.close();

    i = Math.clamp(i, -100, 100);
    return i;
  }
}
