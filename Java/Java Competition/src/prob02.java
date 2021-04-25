import java.util.Scanner;

public class prob02 {

	public static void main(String[] args) {

		int input = 0;
		Scanner reader = new Scanner(System.in);
		input = reader.nextInt();

		while (input != 0) {
			System.out.println(input + " gallons per week will last "
					+ ((int) 10000 / input) + " weeks");
			input = reader.nextInt();
		}

	}

}
