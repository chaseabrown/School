import java.util.*;
public class prob06 {

	public static void main(String[] args) {
		int iterations;
		int t;
		int t0;
		int t1;
		int c0;
		int c1;
		Scanner sc = new Scanner(System.in);
		iterations = sc.nextInt();
		
		for (int i = 0; i<iterations; i++){
			t = sc.nextInt();
			t0 = sc.nextInt();
			t1 = sc.nextInt();
			c0 = sc.nextInt()/8;
			c1 = sc.nextInt()/8;
			double slope = ((double)c1-(double)c0)/((double)t1-(double)t0);
			double b = (double)c0 - (double)slope*(double)t0;
			double Temperature = (double)t*(double)slope + (double)b;
			System.out.println(Temperature);
		}
		
		
	}

}
