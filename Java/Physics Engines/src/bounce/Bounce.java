package bounce;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Bounce {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.add(new Move());
		frame.setVisible(true);
	}

}

class Move extends JComponent {

	public Move() {
		Thread animationThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(2);
					} catch (Exception ex) {
					}
				}
			}
		});

		animationThread.start();
	}

	static int ballX = 69;
	static int ballY = 42;
	static int speed = 0;
	static boolean down = true;
	static boolean right = true;
	
	public void paintComponent(Graphics g) {

		Graphics2D ball = (Graphics2D) g;
		
		if(down){
			ballY++;
		}else{
			ballY--;
		}
		
		if(right){
			ballX+=speed;
		}else{
			ballX--;
		}
		if(ballX>=280||ballX<=0){
			right=!right;
		}
		if(ballY>=280||ballY<=0){
			down=!down;
		}
		
		
		ball.fillOval(ballX, ballY, 20, 20);
		
	}

}