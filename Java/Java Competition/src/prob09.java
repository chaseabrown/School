import java.util.*;

public class prob09 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String answer = "";
		while (true) {
			double length = sc.nextDouble();
			double width = sc.nextDouble();
			double depth = sc.nextDouble();
			if (length == 0 && width == 0 && depth == 0) {
				break;
			}
			double volumeT = length*width*depth;
			double volumeUnpainted = (length-2)*(width-2)*(depth-2);
			double volumePainted = volumeT - volumeUnpainted;
			
			if(volumeUnpainted > volumePainted){
				 answer = "LESS than Perfect";
			}else if(volumeUnpainted < volumePainted){
				 answer = "MORE than Perfect";
			}else{
				 answer = "PERFECT";
			}
			System.out.println("A "+ (int)length +"x"+(int)width+"x"+(int)depth + " block is " + answer);
		}
	}

}
