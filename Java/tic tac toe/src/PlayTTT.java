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

	public static int[][] savedBoard = new int[3][3];
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
	static int[][] intBoard = new int[3][3];
	static JFrame frame = new JFrame();
	static JLabel topBar = new JLabel();
	static JButton[][] buttons = new JButton[3][3];
	static TTTBoard board = new TTTBoard();
	static char[][] savedCharBoard = new char[3][3];
	
	public static void main(String[] args) {

		mainGUI();

	}

	public static void mainGUI() {

		PlayTTT.frame.setTitle("Tic Tac Toe");
		PlayTTT.frame.setSize(315, 357);
		PlayTTT.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel backGround = new JPanel();

		PlayTTT.topBar.setBounds(0, 0, 375, 20);

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				final int t = i;
				final int y = s;
				PlayTTT.buttons[i][s] = new JButton();
				PlayTTT.buttons[i][s].setOpaque(true);
				PlayTTT.buttons[i][s].setBounds((i * 100), (s * 100) + 20, 100,
						100);
				PlayTTT.buttons[i][s].addActionListener(new ActionListener() {
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
									PlayTTT.buttons[i][s]
											.setBackground(Color.white);
								}
							}
						} else if (temp == 1) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.green);
								}
							}
						} else if (temp == 2) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.red);
								}
							}
						} else if (temp == 3) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.cyan);
								}
							}
						} else if (temp == 4) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.magenta);
								}
							}
						} else if (temp == 5) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.orange);
								}
							}
						} else if (temp == 6) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.pink);
								}
							}
						} else if (temp == 7) {
							for (int i = 0; i < 3; i++) {
								for (int s = 0; s < 3; s++) {
									PlayTTT.buttons[i][s]
											.setBackground(Color.yellow);
								}
							}
						}

						lastTemp = temp;

						if (!PlayTTT.used[t][y]) {
							playerMove(t, y);
							convertCharBoard();
							if(!board.full()){
								computerMove();
							}
							convertCharBoard();
							for(int i = 0; i<3; i++){
								for(int s = 0; s<3; s++){
									System.out.print(intBoard[s][i]);
								}
								System.out.println();
							}
							System.out.println(board.toString());
						}

						for (int i = 0; i < 3; i++) {
							for (int s = 0; s < 3; s++) {
								if (!PlayTTT.used[s][i]) {
									tie = false;
								}
							}
						}

						if (checkWin() == 1) {
							PlayTTT.p1Win = true;
						}
						if (checkWin() == 2) {
							PlayTTT.p2Win = true;
						}

						if (checkWin() == 3) {
							PlayTTT.frame.dispose();
							for(int i = 0; i<3; i++){
								for(int s = 0; s<3; s++){
									System.out.print(intBoard[s][i]);
								}
								System.out.println();
							}
							System.out.println(board.toString());
							System.out.println("Tie!!");
						} else if (PlayTTT.p1Win) {
							PlayTTT.frame.dispose();
							for(int i = 0; i<3; i++){
								for(int s = 0; s<3; s++){
									System.out.print(intBoard[s][i]);
								}
								System.out.println();
							}
							System.out.println(board.toString());
							System.out.println("Player 1 Wins!!");
						} else if (PlayTTT.p2Win) {
							PlayTTT.frame.dispose();
							
							for(int i = 0; i<3; i++){
								for(int s = 0; s<3; s++){
									System.out.print(intBoard[s][i]);
								}
								System.out.println();
							}
							System.out.println(board.toString());
							
							System.out.println("Player 2 Wins!!");
						}

					}
				});
				PlayTTT.frame.add(PlayTTT.buttons[i][s]);
			}
		}
		PlayTTT.frame.add(backGround);
		PlayTTT.frame.setVisible(true);

	}

	public static void swapPlayer() {
		p1Turn = !p1Turn;
	}

	public static void saveBoard() {

		savedPlayer = PlayTTT.p1Turn;

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				savedBoard[i][s] = intBoard[i][s];
				
			}
		}

	}

	public static void loadBoard() {

		PlayTTT.p1Turn = PlayTTT.savedPlayer;

		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				PlayTTT.intBoard[i][s] = PlayTTT.savedBoard[i][s];
			}
		}
	
	}

	public static void convertCharBoard() {

		board.reset();
		
		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				if (intBoard[s][i] == 1) {
					board.placeXorO('X', s + 1, i + 1);
				} else if (intBoard[s][i] == 2) {
					board.placeXorO('O', s + 1, i + 1);
				}
			}
		}

	}

	public static int checkWin() {

		if (board.getWinner() == 'X') {
			return 1;
		} else if (board.getWinner() == 'O') {
			return 2;
		}

		if (!board.full()) {
			return 0;
		}
		return 3;
	}

	public static void mc_trial(int p) {
		boolean[][] used = new boolean[3][3];

		if (p != 0) {

			for (int i = 0; i < 3; i++) {
				for (int s = 0; s < 3; s++) {
					if (PlayTTT.intBoard[i][s] != 0) {
						used[i][s] = true;
					} else {
						used[i][s] = false;
					}
				}
			}

			if (checkWin() == 0) {
				int player = 2;
				if (PlayTTT.p1Turn) {
					player = 1;
				}
				int temp1 = PlayTTT.generator.nextInt(3);
				int temp2 = PlayTTT.generator.nextInt(3);

				while (used[temp1][temp2]) {
					temp1 = PlayTTT.generator.nextInt(3);
					temp2 = PlayTTT.generator.nextInt(3);
				}
				PlayTTT.intBoard[temp1][temp2] = player;
				used[temp1][temp2] = true;
				swapPlayer();
				
				convertCharBoard();
				mc_trial(p - 1);
			}
		}

	}

	public static void mc_update_scores() {

		for (int o = 0; o < 3; o++) {
			for (int p = 0; p < 3; p++) {
				if (PlayTTT.savedBoard[o][p] != 0) {
					PlayTTT.scores[o][p] = -100000000;
				}
			}
		}

		if (PlayTTT.p1Turn) {
			if (checkWin() == 1) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (PlayTTT.intBoard[i][s] == 1) {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_CURRENT;
						} else if (PlayTTT.intBoard[i][s] == 2) {
							PlayTTT.scores[i][s] -= PlayTTT.SCORE_OTHER;
						} else {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_EMPTY;
						}
					}
				}
			} else if (checkWin() == 2) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (PlayTTT.intBoard[i][s] == 1) {
							PlayTTT.scores[i][s] -= PlayTTT.SCORE_CURRENT;
						} else if (PlayTTT.intBoard[i][s] == 2) {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_OTHER;
						} else {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_EMPTY;
						}
					}
				}
			}
		} else {
			if (checkWin() == 2) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (PlayTTT.intBoard[i][s] == 2) {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_CURRENT;
						} else if (PlayTTT.intBoard[i][s] == 1) {
							PlayTTT.scores[i][s] -= PlayTTT.SCORE_OTHER;
						} else {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_EMPTY;
						}
					}
				}
			} else if (checkWin() == 1) {
				for (int i = 0; i < 3; i++) {
					for (int s = 0; s < 3; s++) {
						if (PlayTTT.intBoard[i][s] == 2) {
							PlayTTT.scores[i][s] -= PlayTTT.SCORE_CURRENT;
						} else if (PlayTTT.intBoard[i][s] == 1) {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_OTHER;
						} else {
							PlayTTT.scores[i][s] += PlayTTT.SCORE_EMPTY;
						}
					}
				}
			}
		}
	}

	public static int[] get_best_move() {

		int bestValue = scores[0][0];
		int bestIndex1 = 0;
		int bestIndex2 = 0;
		for (int i = 0; i < 3; i++) {
			for (int s = 0; s < 3; s++) {
				if (PlayTTT.scores[i][s] > bestValue) {
					bestValue = PlayTTT.scores[i][s];
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
				PlayTTT.scores[i][s] = 0;
			}
		}

		saveBoard();

		for (int i = 0; i < trials; i++) {
			mc_trial(9);
			mc_update_scores();
			loadBoard();
			convertCharBoard();
		}

		
		PlayTTT.p1Turn = true;

		return get_best_move();
	}

	public static void playerMove(int t, int y) {

		PlayTTT.buttons[t][y].setText("x");
		PlayTTT.intBoard[t][y] = 1;
		PlayTTT.used[t][y] = true;

		PlayTTT.tie = true;
	}

	public static void computerMove() {
		int[] position = new int[2];
		position = PlayTTT.mc_move(1);
		int o = position[0];
		int p = position[1];

		PlayTTT.buttons[o][p].setText("o");
		PlayTTT.intBoard[o][p] = 2;
		PlayTTT.used[o][p] = true;
		PlayTTT.tie = true;

	}

}