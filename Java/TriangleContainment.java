import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class TriangleContainment {
	static Scanner reader;

	public static void main(String[] args) {

		
		try {
			reader = new Scanner(new File("./points.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double ax = 0, ay = 0, bx = 0, by = 0, cx = 0, cy = 0, xx = 0, xy = 0;
		double d1 = 0, d2 = 0, d3 = 0, dx1 = 0, dx2 = 0, dx3 = 0;
		double perimeter = 0, sumofdistance = 0;
		int counter = 0;

		while(reader.hasNext()){
			ax = reader.nextDouble();
			ay = reader.nextDouble();
			bx = reader.nextDouble();
			by = reader.nextDouble();
			cx = reader.nextDouble();
			cy = reader.nextDouble();


			d1 = Math.sqrt(Math.pow((ax - bx) , 2)+Math.pow((ay-by), 2));
			d2 = Math.sqrt(Math.pow((bx - cx) , 2)+Math.pow((by-cy), 2));
			d3 = Math.sqrt(Math.pow((cx - ax) , 2)+Math.pow((cy-ay), 2));
			perimeter = d1+d2+d3;
			dx1 = Math.sqrt(Math.pow((ax - xx) , 2)+Math.pow((ay-xy), 2));
			dx2 = Math.sqrt(Math.pow((bx - xx) , 2)+Math.pow((by-xy), 2));
			dx3 = Math.sqrt(Math.pow((cx - xx) , 2)+Math.pow((cy-xy), 2));
			sumofdistance = dx1+dx2+dx3;
			if(perimeter>= sumofdistance){
				counter++;
			}
		System.out.print(counter);
		}
	}

}
