package Buoyancy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Buoyancy {

	public static void main(String[] args) {

		mainGUI();

	}

	public static void mainGUI() {
		JFrame frame = new JFrame("Buoyancy");
		frame.setSize(1000, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Move());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

@SuppressWarnings("serial")
class Move extends JComponent implements KeyListener {

	static boolean down = true;
	static boolean waterdown = true;
	static int speed = 1;
	static int ballY = 180;
	static int ballX = 10;
	static int waterspeed = 0;
	static double velx = 0;
	static boolean stop = false;
	static boolean waterstop = false;

	public Move() {
		addKeyListener(this);
		setFocusable(true);
		Thread animatedThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
			}
		});
		animatedThread.start();
	}

	public void paint(Graphics g) {
		Graphics2D circle = (Graphics2D) g;
		Graphics2D ground1 = (Graphics2D) g;
		Graphics2D ground2 = (Graphics2D) g;
		Graphics2D water = (Graphics2D) g;

		ground1.setColor(Color.lightGray);
		ground1.fillRect(0, 200, 275, 200);
		ground2.fillRect(575, 200, 450, 200);
		water.setColor(Color.cyan);
		water.fillRect(275, 200, 300, 200);

		if (ballY >= 180
				&& (ballX >= 0 && ballX <= 275 || ballX >= 575 && ballX <= 980)) {
			down = false;
		} else if (speed < 1) {
			down = true;
		}

		if (ballX > 275 && ballX < 575) {
			stop = false;
			if (ballY > 200) {
				if (!waterstop) {
					if (waterdown) {
						speed = 0;
						waterspeed += 1;
						ballY += waterspeed;
						System.out.println(waterspeed);
						System.out.println(waterdown);
						System.out.println(ballY);
						System.out.println(speed);
					} else {
						speed = 0;
						waterspeed -= 1;
						ballY += waterspeed;
						System.out.println(waterspeed);
						System.out.println(waterdown);
						System.out.println(ballY);
						System.out.println(speed);
					}

				}
			}

			if (waterspeed == 0) {
				waterdown = true;
			}
			if (ballY > 220) {
				waterdown = false;
			}
			if(ballY == 123){
				waterstop = true;
			}

		}

		if (speed < 0) {
			stop = true;
			speed = 0;
		}

		if (!stop) {
			if (down) {
				speed++;
				ballY += speed;
			} else {
				speed--;
				ballY -= speed;
			}
		}

		ballX += velx;

		circle.setColor(Color.magenta);
		circle.fillOval(ballX, ballY, 20, 20);

	}

	public static void left() {
		if (Move.ballX > 0) {
			velx = -10;
		} else {
			velx = 0;
		}
	}

	public static void right() {
		if (Move.ballX < 980) {
			velx = 10;
		} else {
			velx = 0;
		}
	}

	public static void jump() {
		speed = 16;
		down = false;
		stop = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (Move.ballX > 10) {
				Move.left();
			} else {
				velx = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			if (Move.ballX < 980) {
				Move.right();
			} else {
				velx = 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			if (Move.speed == 0) {
				jump();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			velx = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			velx = 0;
		}
	}

}
