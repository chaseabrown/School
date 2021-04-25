import java.util.Scanner;


public class prob03 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		int iterations = reader.nextInt();
		reader.nextLine();
		String word = reader.nextLine();
		boolean likes = false;
		
		for(int i = 0; i<iterations-1; i++){
			for(int s = 0; s<word.length()-2; s++){
				String first = word.substring(s, s+1);
				String second = word.substring(s+1, s+2);
				if(first.equalsIgnoreCase(second)){
					likes = true;
				}
			}
			if(likes){
				System.out.println("likes " + word);
			}else{
				System.out.println("hates " + word);
			}
			word = reader.nextLine();
			likes = false;
		}
		
		
	}

}
