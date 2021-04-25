import java.util.Scanner;


public class prob04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner reader = new Scanner(System.in);
		double b=reader.nextDouble();
		double e=reader.nextDouble();
		double number;
		for (int i=1; i>0; i++)
		{
			if ((b==0)&&(e==0)){
				break;
			}
			number=Math.pow(10, e);
			//System.out.println((Math.round((b*number*100))));
			double newNumber = ((Math.round((b*number*100))));
			System.out.println(newNumber*.01);
			b=reader.nextDouble();
			e=reader.nextDouble();
		}
		
	}

}
