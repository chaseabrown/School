public class TTTBoard{
	private char [][] board;

	public TTTBoard(){
		board =new char[3][3];
		reset();
	}

	// For making copies of a TTTBoard object
	public TTTBoard(TTTBoard originalBoard)	{
		board = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				board[row][col] = originalBoard.getMark(row, col);
			}
		}
	}

	public void reset(){
		for(int row=0; row<3; row++){
			for(int col=0; col<3; col++){
				board[row][col]='-';
			}
		}
	}

	public String toString(){
		String result="\n";
		for(int row=0; row<3; row++){
			for(int col=0; col<3; col++){
				result +=board[row][col]+ " ";

			}
			result += "\n";
		}
		return result;
	}
	public boolean placeXorO(char player, int row, int col){
		if(board[row-1][col-1]== '-'){
			board[row-1][col-1]= player;
			return true;
		}else{
			return false;
		}
	}

	public char getMark(int row, int col) {
		return board[row][col];
	}

	public int count_empty_squares() {
		int count = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (getMark(row,col) == '-') {
					count++;
				}
			}
		}
		return count;
	}

	public int[][] get_empty_squares() {
		int[][] emptySquares;

		int count = count_empty_squares();
		int next = 0;

		emptySquares = new int[count][2];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (getMark(row,col) == '-') {
					emptySquares[next][0] = row;
					emptySquares[next][1] = col;
					next++;
				}
			}
		}
		return emptySquares;
	}

	public char getWinner(){

		// Each triple represents the current content of a row,
		// column, or diagonal
		String XXX = "XXX";
		String OOO = "OOO";

		String column="";
		String row="";
		String diagonal1="";
		String diagonal2="";

		// Check rows and columns
		for(int i=0; i<3; i++){
			column=getColumn(i);
			row=getRow(i);
			if (column.equals(XXX) || row.equals(XXX)) {
				return 'X';
			}
			if (column.equals(OOO) || row.equals(OOO)) {
				return 'O';
			}
		}

		// Check diagonals
		diagonal1 = getDiagonal(0);
		diagonal2 = getDiagonal(2);
		if(diagonal1.equals(XXX) || diagonal2.equals(XXX)) {
			return 'X';
		}

		if(diagonal1.equals(OOO) || diagonal2.equals(OOO)){
			return 'O';
		}	

		// Default is a DRAW
		return '-';
	}

	// Return the content of a column
	private String getColumn(int column){
		String Col="";
		for(int row=0; row<3; row++){
			Col+=board[row][column];
		}
		return Col;
	}

	// Return the content of a row
	private String getRow(int row){
		String Row="";
		for(int col=0; col<3; col++){
			Row+=board[row][col];
		}
		return Row;
	}

	// Return the content of a diagonal
	private String getDiagonal(int startRow){
		String Diagonal="";
		int row, col;
		col = 0;
		if(startRow==0) {
			for(row=0; row < 3; row++) {
				Diagonal+=board[row][col];
				col++;
			}
		} else {
			for (row=2; row >= 0; row--) {
				Diagonal+=board[row][col];
				col++;
			}
		}
		return Diagonal;
	}

	public boolean full(){
		for(int row=0; row<3; row++){
			for(int col=0; col<3; col++){
				if(board[row][col]=='-'){
					// Any empty space means the board's not full.
					return false;
				}
			}
		}
		// We didn't find an empty space, so there's not one.
		return true;
	}

	public char switch_player(char p) {
		if (p == 'X') {
			return 'O';
		} else if (p == 'O') {
			return 'X';
		} else {
			System.out.println("Error: switch_player() called with bad player value");
			return 'X';
		}
	}

}
