public class ReverseArgs {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Аргументы не переданы!");
            return;
        }

        System.out.println("Аргументы в обратном порядке:");
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.println(args[i]);
        }
    }
}