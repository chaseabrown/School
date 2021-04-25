import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Ship {

	public static void main(String[] args) {

		mainGUI();

	}

	public static void mainGUI() {
		JFrame frame = new JFrame("Bouncing Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.add(new Move());
		frame.setVisible(true);
	}

}

class Move extends JComponent implements KeyListener {

	public Move() {
		addKeyListener(this);
		setFocusable(true);
		Thread animationThread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(20);
					} catch (Exception ex) {
					}
				}
			}
		});
	}

	static double directionOfRotation = 0;
	static double rotation = 0;

	public void paint(Graphics g) {
		Graphics2D ship = (Graphics2D) g;

		directionOfRotation += rotation;

		ship.fillOval(100, 100, 10, 35);
		ship.rotate(directionOfRotation);

	}

	public static void rotateLeft() {
		rotation -= 5;
	}

	public static void rotateRight() {
		rotation += 5;
	}

	public void keyTyped(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Move.rotateLeft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Move.rotateRight();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			rotation = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rotation = 0;
		}
	}

}
