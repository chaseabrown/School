//Chase Brown && Christian Kitchen
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayTTT {

	private static int[][] savedBoard = new int[3][3];
	public static int[][] scores = new int[3][3];
	public static boolean savedPlayer = true;
	public final int NTRIALS = 1;
	public final static int SCORE_CURRENT = 1;
	public final static int SCORE_OTHER = 1;
	public final static int SCORE_EMPTY = 0;
	static Random generator = new Random();
	static boolean p1Turn = true;
	static boolean[][] used = new boolean[3][3];
	static boolean tie = false;
	static boolean p1Win = false;
	static boolean p2Win = false;
	static int[][] board = new int[3][3];
	static JFrame frame = new JFrame();
	static JLabel topBar = new JLabel();
	static JButton[][] buttons = new JButton[3][3];

	public static void main(String[] args) {
		mainGUI();
	}

	public static void mainGUI() {
		frame.setTitle("Tic Tac Toe");
		frame.setSize(315, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel backGround = new JPanel();

		topBar.setBounds(0, 0, 375, 20);

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				final int t = i;
				final int y = s;
				buttons[i][s] = new JButton();
				buttons[i][s].setOpaque(true);
				buttons[i][s].setBounds((i * 100), (s * 100) + 20, 100, 100);
				buttons[i][s].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {

						Random gen = new Random();
						int lastTemp = -1;

						int temp = gen.nextInt(8);

						while (temp == lastTemp) {
							temp = gen.nextInt(8);
						}

						if (temp == 0) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.white);
								}
							}
						} else if (temp == 1) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.green);
								}
							}
						} else if (temp == 2) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.red);
								}
							}
						} else if (temp == 3) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.cyan);
								}
							}
						} else if (temp == 4) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.magenta);
								}
							}
						} else if (temp == 5) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.orange);
								}
							}
						} else if (temp == 6) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.pink);
								}
							}
						} else if (temp == 7) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									buttons[i][s].setBackground(Color.yellow);
								}
							}
						}

						lastTemp = temp;
						
						if(!used[t][y]){
							playerMove(t,y);
							
							computerMove();
						}
						

						for (int i = 0; i < 3; i++) {
							for (int s = 0; s < 3; s++) {
								if (!used[i][s]) {
									tie = false;
								}
							}
						}

						if (PlayTTT.checkWin(board) == 1) {
							p1Win = true;
						}
						if (PlayTTT.checkWin(board) == 2) {
							p2Win = true;
						}

						if (checkWin(board) == 3) {
							frame.dispose();
							System.out.println("Tie!!");
						} else if (p1Win) {
							frame.dispose();
							System.out.println("Player one wins!!");
						} else if (p2Win) {
							frame.dispose();
							System.out.println("Player two wins!!");
						}

					}
				});
				frame.add(buttons[i][s]);
			}
		}
		frame.add(topBar);
		frame.add(backGround);
		frame.setVisible(true);

	}

	public static void playerMove(int t,int y){

			buttons[t][y].setText("x");
			board[t][y] = 1;
			used[t][y] = true;
			
			tie = true;
	}
	
	public static void computerMove(){
		int[] position = new int[2];
		position = mc_move(10000);
		int o = position[0];
		int p = position[1];
		
		buttons[o][p].setText("o");
		board[o][p] = 2;
		used[o][p] = true;
		tie = true;

	}
	
	public static int checkWin(int[][] board) {

		int x, y, z;

		for (int d = 0; d < 2; d++) {
			for (int i = 0; i < 3; i++) {
				x = -1;
				y = -1;
				z = -1;
				for (int s = 0; s < 3; s++) {

					if (d == 0) {
						if (x == -1) {
							x = board[i][s];
						} else if (y == -1) {
							y = board[i][s];
						} else if (z == -1) {
							z = board[i][s];
						}
					}

					if (d == 1) {
						if (x == -1) {
							x = board[s][i];
						} else if (y == -1) {
							y = board[s][i];
						} else if (z == -1) {
							z = board[s][i];
						}
					}

				}

				if (x == 1 && y == 1 && z == 1) {
					return 1;
				}
			}
		}

		if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) {
			return 1;
		} else if (board[2][0] == 1 && board[1][1] == 1 && board[0][2] == 1) {
			return 1;
		}

		for (int d = 0; d < 2; d++) {
			for (int i = 0; i < 3; i++) {
				x = -1;
				y = -1;
				z = -1;
				for (int s = 0; s < 3; s++) {

					if (d == 0) {
						if (x == -1) {
							x = board[i][s];
						} else if (y == -1) {
							y = board[i][s];
						} else if (z == -1) {
							z = board[i][s];
						}
					}

					if (d == 1) {
						if (x == -1) {
							x = board[s][i];
						} else if (y == -1) {
							y = board[s][i];
						} else if (z == -1) {
							z = board[s][i];
						}
					}

				}

				if (x == 2 && y == 2 && z == 2) {
					return 2;
				}
			}
		}

		if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) {
			return 2;
		} else if (board[2][0] == 2 && board[1][1] == 2 && board[0][2] == 2) {
			return 2;
		}
		
		for(int i = 0; i<3; i++){
			for(int s = 0; s<3; s++){
				if(board[i][s] == 0){
					return 0;
				}
			}
		}
		

		return 3;
	}

	public static void swapPlayer() {
		p1Turn = !p1Turn;
	}

	public static void saveBoard() {

		savedPlayer = p1Turn;

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				savedBoard[i][s] = board[i][s];
			}
		}

	}

	public static void loadBoard() {

		p1Turn = savedPlayer;

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				board[i][s] = savedBoard[i][s];
			}
		}

	}

	public static void mc_trial() {
		boolean[][] used = new boolean[3][3];

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				if (board[i][s] != 0) {
					used[i][s] = true;
				} else {
					used[i][s] = false;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (checkWin(board) == 0) {
				int player = 2;
				if (p1Turn) {
					player = 1;
				}
				int temp1 = generator.nextInt(3);
				int temp2 = generator.nextInt(3);

				while (used[temp1][temp2]) {
					temp1 = generator.nextInt(3);
					temp2 = generator.nextInt(3);
				}
				board[temp1][temp2] = player;
				used[temp1][temp2] = true;
				swapPlayer();
			}
		}

	}

	public static void mc_update_scores() {

		for (int o = 0; o < 3; o++) {
			for (int p = 0; p < 3; p++) {
				if(savedBoard[o][p] != 0){
					scores[o][p] = -100000000;
				}
			}
		}

		if (p1Turn) {
			if (checkWin(board) == 1) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (board[i][s] == 1) {
							scores[i][s] += SCORE_CURRENT;
						} else if (board[i][s] == 2) {
							scores[i][s] -= SCORE_OTHER;
						} else {
							scores[i][s] += SCORE_EMPTY;
						}
					}
				}
			} else if (checkWin(board) == 2) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (board[i][s] == 1) {
							scores[i][s] -= SCORE_CURRENT;
						} else if (board[i][s] == 2) {
							scores[i][s] += SCORE_OTHER;
						} else {
							scores[i][s] += SCORE_EMPTY;
						}
					}
				}
			}
		} else {
			if (checkWin(board) == 2) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (board[i][s] == 2) {
							scores[i][s] += SCORE_CURRENT;
						} else if (board[i][s] == 1) {
							scores[i][s] -= SCORE_OTHER;
						} else {
							scores[i][s] += SCORE_EMPTY;
						}
					}
				}
			} else if (checkWin(board) == 1) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (board[i][s] == 2) {
							scores[i][s] -= SCORE_CURRENT;
						} else if (board[i][s] == 1) {
							scores[i][s] += SCORE_OTHER;
						} else {
							scores[i][s] += SCORE_EMPTY;
						}
					}
				}
			}
		}
	}

	public static int[] get_best_move() {

		int bestValue = 0;
		int bestIndex1 = 0;
		int bestIndex2 = 0;
		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				if (scores[i][s] > bestValue) {
					bestValue = scores[i][s];
					bestIndex1 = i;
					bestIndex2 = s;
				}
			}
		}

		int[] position = { bestIndex1, bestIndex2 };
		return position;
	}

	public static int[] mc_move(int trials) {

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				scores[i][s] = 0;
			}
		}

		saveBoard();

		for (int i = 0; i < trials; i++) {
			mc_trial();
			mc_update_scores();		
			loadBoard();
		}
		
		p1Turn = true;

		return get_best_move();
	}

}
