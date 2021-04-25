class Practice {
	public static void main(String[] args) {
		
		int addition = 0;
		int subtraction = 0;
		int division = 0;
		int multiplication = 0;
		
		addition = addNumbers(1,2,3);
		subtraction = subtractNumbers(10,5);
		division = divideNumbers(10,2);
		multiplication = multiplyNumbers(2,3,4);
		
		System.out.println("Addition is equal to: " + addition);
		System.out.println("Subtraction is equal to: " + subtraction);
		System.out.println("Division is equal to: " + division);
		System.out.println("Multiplication is equal to: " + multiplication);
		
	}
	
	public static int addNumbers (int n1, int n2, int n3){
		int sum = n1+n2+n3;
		return sum;
	}
	
	public static int subtractNumbers (int n1, int n2){
		int difference = n1-n2;
		return difference;
	}
	
	public static int divideNumbers (int n1, int n2){
		int quocient = n1/n2;
		return quocient;
	}
	
	public static int multiplyNumbers (int n1, int n2, int n3){
		int product = n1*n2*n3;
		return product;
	}
}