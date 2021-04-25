import java.util.Scanner;

public class prob07 {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		int[] letters1 = new int[26];
		int[] letters2 = new int[26];
		int[] letters3 = new int[26];
		int iterations = reader.nextInt();
		String word1 = reader.next();
		String word2 = reader.next();
		String word3 = reader.next();
		String output = "";

		for (int g = 0; g < iterations; g++) {
			for (int i = 0; i < word1.length(); i++) {
				char letter = word1.charAt(i);
				switch (letter) {
				case 'a':
					letters1[0]++;
					break;
				case 'b':
					letters1[1]++;
					break;
				case 'c':
					letters1[2]++;
					break;
				case 'd':
					letters1[3]++;
					break;
				case 'e':
					letters1[4]++;
					break;
				case 'f':
					letters1[5]++;
					break;
				case 'g':
					letters1[6]++;
					break;
				case 'h':
					letters1[7]++;
					break;
				case 'i':
					letters1[8]++;
					break;
				case 'j':
					letters1[9]++;
					break;
				case 'k':
					letters1[10]++;
					break;
				case 'l':
					letters1[11]++;
					break;
				case 'm':
					letters1[12]++;
					break;
				case 'n':
					letters1[13]++;
					break;
				case 'o':
					letters1[14]++;
					break;
				case 'p':
					letters1[15]++;
					break;
				case 'q':
					letters1[16]++;
					break;
				case 'r':
					letters1[17]++;
					break;
				case 's':
					letters1[18]++;
					break;
				case 't':
					letters1[19]++;
					break;
				case 'u':
					letters1[20]++;
					break;
				case 'v':
					letters1[21]++;
					break;
				case 'w':
					letters1[22]++;
					break;
				case 'x':
					letters1[23]++;
					break;
				case 'y':
					letters1[24]++;
					break;
				case 'z':
					letters1[25]++;
					break;
				}
			}

			for (int i = 0; i < word2.length(); i++) {
				char letter = word2.charAt(i);
				switch (letter) {
				case 'a':
					letters2[0]++;
					break;
				case 'b':
					letters2[1]++;
					break;
				case 'c':
					letters2[2]++;
					break;
				case 'd':
					letters2[3]++;
					break;
				case 'e':
					letters2[4]++;
					break;
				case 'f':
					letters2[5]++;
					break;
				case 'g':
					letters2[6]++;
					break;
				case 'h':
					letters2[7]++;
					break;
				case 'i':
					letters2[8]++;
					break;
				case 'j':
					letters2[9]++;
					break;
				case 'k':
					letters2[10]++;
					break;
				case 'l':
					letters2[11]++;
					break;
				case 'm':
					letters2[12]++;
					break;
				case 'n':
					letters2[13]++;
					break;
				case 'o':
					letters2[14]++;
					break;
				case 'p':
					letters2[15]++;
					break;
				case 'q':
					letters2[16]++;
					break;
				case 'r':
					letters2[17]++;
					break;
				case 's':
					letters2[18]++;
					break;
				case 't':
					letters2[19]++;
					break;
				case 'u':
					letters2[20]++;
					break;
				case 'v':
					letters2[21]++;
					break;
				case 'w':
					letters2[22]++;
					break;
				case 'x':
					letters2[23]++;
					break;
				case 'y':
					letters2[24]++;
					break;
				case 'z':
					letters2[25]++;
					break;
				}
			}

			for (int i = 0; i < word3.length(); i++) {
				char letter = word3.charAt(i);
				switch (letter) {
				case 'a':
					letters3[0]++;
					break;
				case 'b':
					letters3[1]++;
					break;
				case 'c':
					letters3[2]++;
					break;
				case 'd':
					letters3[3]++;
					break;
				case 'e':
					letters3[4]++;
					break;
				case 'f':
					letters3[5]++;
					break;
				case 'g':
					letters3[6]++;
					break;
				case 'h':
					letters3[7]++;
					break;
				case 'i':
					letters3[8]++;
					break;
				case 'j':
					letters3[9]++;
					break;
				case 'k':
					letters3[10]++;
					break;
				case 'l':
					letters3[11]++;
					break;
				case 'm':
					letters3[12]++;
					break;
				case 'n':
					letters3[13]++;
					break;
				case 'o':
					letters3[14]++;
					break;
				case 'p':
					letters3[15]++;
					break;
				case 'q':
					letters3[16]++;
					break;
				case 'r':
					letters3[17]++;
					break;
				case 's':
					letters3[18]++;
					break;
				case 't':
					letters3[19]++;
					break;
				case 'u':
					letters3[20]++;
					break;
				case 'v':
					letters3[21]++;
					break;
				case 'w':
					letters3[22]++;
					break;
				case 'x':
					letters3[23]++;
					break;
				case 'y':
					letters3[24]++;
					break;
				case 'z':
					letters3[25]++;
					break;
				}
			}

			for (int i = 0; i < 26; i++) {
				int smallest = Math.min(letters1[i],
						Math.min(letters2[i], letters3[i]));
				String letter = "";
				switch (i) {
				case 0:
					letter = "a";
					break;
				case 1:
					letter = "b";
					break;
				case 2:
					letter = "c";
					break;
				case 3:
					letter = "d";
					break;
				case 4:
					letter = "e";
					break;
				case 5:
					letter = "f";
					break;
				case 6:
					letter = "g";
					break;
				case 7:
					letter = "h";
					break;
				case 8:
					letter = "i";
					break;
				case 9:
					letter = "j";
					break;
				case 10:
					letter = "k";
					break;
				case 11:
					letter = "l";
					break;
				case 12:
					letter = "m";
					break;
				case 13:
					letter = "n";
					break;
				case 14:
					letter = "o";
					break;
				case 15:
					letter = "p";
					break;
				case 16:
					letter = "q";
					break;
				case 17:
					letter = "r";
					break;
				case 18:
					letter = "s";
					break;
				case 19:
					letter = "t";
					break;
				case 20:
					letter = "u";
					break;
				case 21:
					letter = "v";
					break;
				case 22:
					letter = "w";
					break;
				case 23:
					letter = "x";
					break;
				case 24:
					letter = "y";
					break;
				case 25:
					letter = "z";
					break;
				}
				for (int s = 0; s < smallest; s++) {
					output += letter;
				}
			}

			System.out.println(output);
			output = "";
			for(int i = 0; i<letters1.length; i++){
				letters1[i] = 0;
				letters2[i] = 0;
				letters3[i] = 0;
			}
			word1 = reader.next();
			word2 = reader.next();
			word3 = reader.next();
		}
	}

}
