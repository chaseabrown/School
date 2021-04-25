import java.util.Scanner;

public class prob05 {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		String word = "";
		int iterations = reader.nextInt();
		int taxNumber = reader.nextInt();
		String input = reader.next();
		int outputNumber = input.length();

		for (int s = 0; s < iterations; s++) {
			for (int i = 0; i < input.length(); i++) {
				if (i % taxNumber != 0) {
					word += input.substring(i, i + 1);
				} else {
					outputNumber--;
				}
			}
			System.out.println(word + " " + outputNumber);
			
			taxNumber = reader.nextInt();
			input = reader.next();
			outputNumber = input.length();
			word = "";
		}
	}

}
