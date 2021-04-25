import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FindSimilaritiesInLists {
	
	public static String[] list1 = new String[1000];
	public static String[] list2 = new String[1000];
	public static String[] list3 = new String[1000];
	public static String[] list4 = new String[1000];
	public static String[] list5 = new String[1000];
	public static String[] list6 = new String[1000];
	public static String[] list7 = new String[1000];
	public static String[] list8 = new String[1000];
	public static String[] list9 = new String[1000];
	public static String[] output1 = new String[1000];
	public static String[] output2 = new String[1000];
	public static String[] output3 = new String[1000];
	public static String[] output4 = new String[1000];
	public static String[] output5 = new String[1000];
	public static String[] output6 = new String[1000];
	public static String[] output7 = new String[1000];
	public static String[] output8 = new String[1000];
	
	public static void main(String[] args) throws IOException {

		readFileToArray("List Similarities/List1.txt", list1);
		readFileToArray("List Similarities/List2.txt", list2);
		readFileToArray("List Similarities/List3.txt", list3);
		readFileToArray("List Similarities/List4.txt", list4);
		readFileToArray("List Similarities/List5.txt", list5);
		readFileToArray("List Similarities/List6.txt", list6);
		readFileToArray("List Similarities/List7.txt", list7);
		readFileToArray("List Similarities/List8.txt", list8);
		readFileToArray("List Similarities/List9.txt", list9);
		
		int counter = 0;
		for(int i = 0; i<253; i++){
			for(int s = 0; s<260; s++){
				String x = list2[i];
				String y = list1[s];
				if((x).equalsIgnoreCase(y)){
					output1[counter] = list2[i];
					output2[counter] = list3[i];
					output3[counter] = list4[i];
					output4[counter] = list5[i];
					output5[counter] = list6[i];
					output6[counter] = list7[i];
					output7[counter] = list8[i];
					output8[counter] = list9[i];
					counter++;
				}
			}
		}
		
		printArrayToFile(output1, "List Similarities/Output1.txt");
		printArrayToFile(output2, "List Similarities/Output2.txt");
		printArrayToFile(output3, "List Similarities/Output3.txt");
		printArrayToFile(output4, "List Similarities/Output4.txt");
		printArrayToFile(output5, "List Similarities/Output5.txt");
		printArrayToFile(output6, "List Similarities/Output6.txt");
		printArrayToFile(output7, "List Similarities/Output7.txt");
		printArrayToFile(output8, "List Similarities/Output8.txt");
		
	}

	public static void printArrayToFile(String[] array, String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		int counter = 0;
		while(counter < array.length){
			bufferedWriter.write("" + array[counter] + "\n");
			counter++;
		}
		bufferedWriter.close();

	}

	public static int readFileToArray(String fileName, String[] array) {
		String line = "";
		int counter = 0;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				} else {
					array[counter] = line;
					counter++;
				}
			}
			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return counter;
	}

}
