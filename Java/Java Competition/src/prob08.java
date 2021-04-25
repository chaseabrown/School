import java.util.Scanner;


public class prob08 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		int iterations = reader.nextInt();
		reader.nextLine();
		String word = reader.nextLine();
		
		for(int i = 0; i<iterations; i++){
			String output = "";
			int counter = word.length();
			for(int s = 0; s<word.length();s++){
				for(int t = 0; t<counter; t++){
				System.out.print(" ");
				}
				int numOfLetters = word.length()-counter;
				output += word.substring(0, 0+numOfLetters);
				System.out.println(output);
				output = "";
				counter--;
			}
			
			for(int s = 0; s<word.length(); s++){
				output = word.substring(s, word.length());
				System.out.println(output);
				output = "";
			}
			word = reader.nextLine();
		}
		
		
	}

}
