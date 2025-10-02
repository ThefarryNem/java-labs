public class Hello {
	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Привет, " + args[0] + "!");
		} else {
			System.out.println("Привет, пользователь!");
		}
	}
}
