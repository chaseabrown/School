/*
 *	Contains StudentPicker, PickerGUI, and SpinnerPanel classes
 *	Creates a GUI that allows for choosing at random a student to win prize for selling appropriate amount
 *	
 *	Format for StudentList.txt must be: firstName#lastName#grade#homeroom
 *	Easily done by copying from excel sheet and replacing the special space character with "#"
 *
 *	Created by Robert Somma 
 *	2/17/14
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StudentPicker {

	public static Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();
	public static int width = (int) screenSize.getWidth();
	public static int height = (int) screenSize.getHeight() - 100;
	public static boolean teacherSpin = false;
	public static boolean showPicture = false;

	public static void main(String[] args) throws IOException {

		// Place gui thread on Event Dispatch Thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// try/catch for IOException
				try {
					PickerGUI gui = new PickerGUI();
				} catch (Exception e) {
				}
			}
		});

	}

}

class PickerGUI extends JFrame {

	// Chase Edit
	public static JFrame GUI = new JFrame("RoundUp Wheel");
	public static Container pane = GUI.getContentPane();
	public static JPanel background = new JPanel();
	public static boolean ranOnce = false;
	public static JLabel teacherLogo = null;
	public static JPanel controlPanel = new JPanel(new GridLayout(2, 1, 15, 15));

	private int[][] hrNumbers = {
			{ 2101, 2201, 2203, 2208, 2217, 5200, 5217, 6213 },
			{ 2104, 2127, 2219, 4202, 4205, 5201, 5202, 5204, 6203 },
			{ 2102, 2202, 2204, 2210, 4200, 4203, 6200, 6201 },
			{ 2116, 2125, 2200, 2207, 2212, 4204, 5207, 5226 }, { 0 } };

	private HashMap<String, Integer> hrNumberMap = new HashMap<String, Integer>();

	private JLabel[] labels = { new JLabel("Freshman Weight"),
			new JLabel("Sophomore Weight"), new JLabel("Junior Weight"),
			new JLabel("Senior Weight"), new JLabel("Top homeroom"),
			new JLabel("Top grade"), };

	private JTextField[] inputBoxes = { new JTextField("25"),
			new JTextField("25"), new JTextField("25"), new JTextField("25"),
			new JTextField("12C"), new JTextField("12"), };

	private JButton[] buttons = { new JButton("Homeroom"),
			new JButton("Grade"), new JButton("Free"), new JButton("Reset") };

	private double[] weights = { 25, 25, 25, 25 };

	private int topHomeroom = 2208, topGrade = 12;

	private HashMap<Integer, ArrayList<String>> gradeMap, // Key is grade# or
															// homeroom# and
															// stores respective
															// students
			homeroomMap;

	private ArrayList<String> allStudents; // Purely for helping sort into grade
											// and homeroom maps

	private Random numGen = new Random();

	private SpinnerPanel spinnerPanel;

	private Executor executor = Executors.newSingleThreadExecutor(); // For
																		// executing
																		// threads
																		// in
																		// sequence

	private String infoString = "";

	/*
	 * Constructors
	 */

	public PickerGUI() throws IOException {

		// Chase Edit
		GUI.setSize(StudentPicker.width, StudentPicker.height);
		GUI.setResizable(false);

		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GUI.setLocationRelativeTo(null);

		JPanel panel = new JPanel();

		// pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS)); // Boxlayout
		// allows for
		// strict sizing
		// of panels
		// according to
		// preferedSize

		// SPINNER PANEL
		spinnerPanel = new SpinnerPanel();
		setAbsoluteSize(spinnerPanel, GUI.getWidth(), 450);

		// CONTROL PANEL
		
		setAbsoluteSize(controlPanel, 875, 100);

		// Manage label and box portion of controlPanel
		JPanel tempPanel = new JPanel(new GridLayout(2, labels.length, 10, 10));

		for (int i = 0; i < labels.length * 2; i++) {
			if (i < labels.length) {
				labels[i].setHorizontalAlignment(SwingConstants.CENTER);
				tempPanel.add(labels[i]);
			} else { // Places boxes below respective label
				inputBoxes[i - labels.length]
						.setHorizontalAlignment(JTextField.CENTER);
				tempPanel.add(inputBoxes[i - labels.length]);
			}
		}

		controlPanel.add(tempPanel);

		// Manage button portion of controlPanel
		for (JButton button : buttons) {
			button.addActionListener(new SpinListener());
		}

		tempPanel = new JPanel(new GridLayout(1, buttons.length + 2, 20, 0)); // length
																				// +
																				// 2
																				// for
																				// panels
																				// on
																				// ends
																				// for
																				// spacing
		tempPanel.add(new JPanel()); // Spacing panel

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
			tempPanel.add(buttons[i]);
		}

		tempPanel.add(new JPanel()); // Spacing panel

		controlPanel.add(tempPanel);

		// Load student data into appropriate lists and maps
		try {
			getStudentData();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading student data");
		}

		// Put hrCodes in map
		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < hrNumbers[i].length; j++) {

				hrNumberMap
						.put((i + 9) + "" + (char) (j + 65), hrNumbers[i][j]);
				// System.out.println((i+9) + "" + (char)(j+65) + ", " +
				// hrNumbers[i][j]);
			}

		}

		/*
		 * for (String key: hrNumberMap.keySet()){
		 * System.out.println(key+","+hrNumberMap.get(key)); }
		 */

		// Chase Edit
		
		System.out.println(PickerGUI.GUI.getBackground());
		
		Image teacherLogoPicture = null;
		try {
			teacherLogoPicture = ImageIO.read(new File("TeacherLogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon teacherLogoLabelPicture = new ImageIcon(teacherLogoPicture);
		

		teacherLogo = new JLabel(teacherLogoLabelPicture);
		teacherLogo.setVisible(false);
		teacherLogo.setBounds(420, 0, 400, 150);
		PickerGUI.pane.add(teacherLogo);
		PickerGUI.pane.add(PickerGUI.background);

		spinnerPanel.setBounds(0, 0, StudentPicker.width,
				StudentPicker.height - 100);
		controlPanel.setBounds(0, StudentPicker.height - 150,
				StudentPicker.width, 100);
		spinnerPanel.setOpaque(false);

		// ADD PANELS

		pane.add(spinnerPanel);
		pane.add(controlPanel);

		pane.add(background);

		GUI.setVisible(true);

	}

	/*
	 * Methods
	 */

	public void enableButtons(boolean enabled) {
		for (JButton button : buttons) {
			// Chase Edit
			StudentPicker.showPicture = !StudentPicker.showPicture;
			button.setEnabled(enabled);
		}
	}

	public boolean getData() {

		/*
		 * Takes data relavent to spins from the input boxes returns false if
		 * gathering any element fails, true otherwise
		 */

		// Ensure weights sum to 100%
		double sum = 0.0;

		for (int weight = 0; weight < 4; weight++) {
			try {
				weights[weight] = Double.parseDouble(inputBoxes[weight]
						.getText());
				sum += weights[weight];
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Please make sure all weight boxes contain a number");
				return false;
			}
		}

		if (sum != 100.0) {
			JOptionPane.showMessageDialog(null,
					"The weights must add to 100! \nThe sum is currently "
							+ sum);
			return false;
		}

		// check valid homeroom
		try {
			topHomeroom = hrNumberMap
					.get(inputBoxes[4].getText().toUpperCase());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid homeroom");
			e.printStackTrace();
			return false;
		}

		// check valid top grade
		try {
			int grade = Integer.parseInt(inputBoxes[5].getText());
			if (grade < 9 || grade > 13) {
				throw new Exception();
			}
			topGrade = grade;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid grade");
			return false;
		}

		return true;
	}

	// public void getHRNums(){

	// /*
	// * Get homerooms numbers
	// * HOMEROOMS array will need to be manually updated as of this version
	// */

	// try{

	// Scanner scn = new Scanner(new File("StudentList.txt"));
	// PrintWriter writer = new PrintWriter(new File("Homerooms.txt"));

	// ArrayList<Integer> homerooms = new ArrayList<Integer>();

	// while (scn.hasNext()){

	// String[] s = scn.nextLine().split("#");

	// int hrNumber = Integer.parseInt(s[s.length-1]); // last index of the
	// split string should be homeroom#

	// if (!homerooms.contains(hrNumber)){
	// homerooms.add(hrNumber);
	// writer.println(hrNumber);
	// }
	// }

	// writer.close();
	// }
	// catch(Exception e){
	// JOptionPane.showMessageDialog(null,"Error getting homeroom numbers");
	// return;
	// }
	// }

	public void getStudentData() throws IOException {

		/*
		 * Takes data from StudentList.txt and sorts student strings into
		 * appropriate maps
		 */

		Scanner listScanner = new Scanner(new File("StudentList.txt"));

		allStudents = new ArrayList<String>();

		while (listScanner.hasNext()) {
			allStudents.add(listScanner.nextLine());
		}

		// fill maps with new Lists

		gradeMap = new HashMap<Integer, ArrayList<String>>();

		for (int grade = 9; grade < 14; grade++) {
			gradeMap.put(grade, new ArrayList<String>());
		}

		homeroomMap = new HashMap<Integer, ArrayList<String>>();

		for (int[] gradeHomeroom : hrNumbers) {
			for (int homeroom : gradeHomeroom) {
				homeroomMap.put(homeroom, new ArrayList<String>());
			}
		}
		// place students in appropriate maps

		for (String student : allStudents) {

			String[] studentArray = student.split("#");

			gradeMap.get(
					Integer.parseInt(studentArray[studentArray.length - 2]))
					.add(student); // 2nd last index should be grade
			homeroomMap.get(
					Integer.parseInt(studentArray[studentArray.length - 1]))
					.add(student); // last index should be homeroom

		}

		// for(String student: gradeMap.get(12)){
		// System.out.println(student);
		// }
	}

	private void setAbsoluteSize(JPanel panel, int width, int height) {

		/*
		 * Sets sizes for boxlayout probably not the most professional way
		 */

		panel.setMinimumSize(new Dimension(width, height));
		panel.setPreferredSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
	}

	/*
	 * Action Listeners
	 */

	private class SpinListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (getData()) { // will be true only if data retrieval is
								// successful
				try { // better to have try/catch and not need it than need it
						// and not have it

					enableButtons(false);
					// Chase Edit
					if (PickerGUI.ranOnce) {
						PickerGUI.pane.remove(SpinnerPanel.picLabel);
						PickerGUI.pane.remove(PickerGUI.background);
					}

					String spinType = ((JButton) e.getSource()).getText(); // getSource
																			// returns
																			// Object,
																			// must
																			// cast
																			// to
																			// JButton
					
					if(topGrade == 13){
						StudentPicker.teacherSpin = true;
						//startTeacherSpin();
					}else{
						StudentPicker.teacherSpin = false;
						//endTeacherSpin();
					}
					

					spinnerPanel.updateMaps(gradeMap, homeroomMap, hrNumberMap); // send
																					// info
																					// to
																					// spinner
					spinnerPanel.updateInfo(weights, topHomeroom, topGrade,
							spinType);

					executor.execute(spinnerPanel);
					executor.execute(new Runnable() { // renables buttons only
														// after spin is
														// finished
						public void run() {
							enableButtons(true);
						}
					});

				} catch (Exception ex) {
				}
			}

		}

	}
	
	//Chase Edit
	public static void startTeacherSpin(){
		SpinnerPanel.lightSabers.setVisible(true);
		PickerGUI.teacherLogo.setVisible(true);
	}
	
	public static void endTeacherSpin(){
		SpinnerPanel.lightSabers.setVisible(false);
		PickerGUI.teacherLogo.setVisible(false);
	}

}

class SpinnerPanel extends JPanel implements Runnable {

	//Chase Edit
	public static JLabel picLabel = null;
	public static JLabel lightSabers = null;
	
	int[] sectorSizes = new int[4];

	private int theta = 0, // in degrees
			diameter = 400, radius = diameter / 2,
			sectors = 12,
			delta = 360 / sectors, gradeTheta = theta, num = 3, // numerator of
																// inner circle
																// scale factor
			den = 5, // denominator of inner circle scale factor
			nameIndex = 0, gradeIndex, topHomeroom, topGrade;

	private ArrayList<String> currentList = new ArrayList<String>();

	private HashMap<Integer, ArrayList<String>> gradeMap = new HashMap<Integer, ArrayList<String>>(),
			homeroomMap = new HashMap<Integer, ArrayList<String>>();

	private HashMap<String, Integer> hrNumberMap;

	private String[] currentListNames = {}; // holds displayabled representation
											// of student strings based on
											// currentList

	private String spinType = "Homeroom", infoString = "", hrCode = "";

	private boolean gradeSelected = true;

	private double[] weights = new double[4];

	/*
	 * Constructors
	 */

	public SpinnerPanel() {
		//Chase Edit
				Image lightSaberPicture = null;
				try {
					lightSaberPicture = ImageIO.read(new File("lightsabers.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ImageIcon saberLabelPicture = new ImageIcon(lightSaberPicture);
				lightSabers = new JLabel(saberLabelPicture);
				lightSabers.setVisible(false);
				
				
	}

	/*
	 * Methods
	 */

	public void paint(Graphics g) {

		/*
		 * Paints the spinner as well as names/grades according to spinType Note
		 * on circle drawing: X coordinates on a circle are given by
		 * r*cos(theta) Y coordinates by r*sin(theta) Where r = radius and theta
		 * is in radians Adding the X and Y coordinate of the center of the
		 * circle accomplishes a horizontal/vertical shift
		 */

		Graphics2D g2 = (Graphics2D) g;
		
		
		
		
		RenderingHints rh = new RenderingHints(
				// Rendering blurs sharp edges, making it look smoother
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHints(rh);

		super.paint(g2); // necessary for override

		int middleX = this.getWidth() / 5 - 25, // X and Y coordinates for the
												// center of the spinner circles
		middleY = this.getHeight() / 2;

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Default", Font.PLAIN, 25));
		g2.drawString(infoString, getWidth() / 2 + 200, 50);

		// Determine grade winner to pass to currentList
		// draws static outer circle with dynamic inner circle with sectors
		// based on the class weights
		if (this.spinType.equals("Free") && !gradeSelected) {

			g2.setColor(Color.RED);

			g2.fillOval(middleX - radius, // Draw outer circle
					middleY - radius, diameter, diameter);

			g2.setColor(Color.BLACK);

			// Draw static lines wihtin outer cicle
			for (int sector = 0; sector < sectors; sector++) {
				g2.drawLine(
						middleX,
						middleY,
						(int) (radius * Math.cos(Math.toRadians(sector * delta)))
								+ middleX, // sector*delta will evenly space
											// lines
						(int) (radius * Math.sin(Math.toRadians(sector * delta)))
								+ middleY); // and keep them static
			}

			g2.setColor(Color.GRAY);

			g2.fillOval(middleX - num * radius / den, // draw inner circle
					middleY - num * radius / den, num * diameter / den, num
							* diameter / den);

			g2.setColor(Color.BLACK);

			// Draw dynamic lines wihtin inner cicle
			for (int sector = 0; sector < 4; sector++) {
				g2.drawLine(
						middleX,
						middleY,
						(int) ((num * radius / den) * Math.cos(Math
								.toRadians(theta))) + middleX, // num*radius/den
																// for scaling
																// of inner
																// circle
						(int) ((num * radius / den) * Math.sin(Math
								.toRadians(theta))) + middleY);
				theta -= sectorSizes[sector]; // -= to keep triangle in sync
												// with theta since theta
												// "resets" after 360 degrees
			}

			// Draw pointer of outer circle
			g2.setColor(Color.BLACK);

			int[] xCords = { middleX - 6, middleX + 6, middleX },

			yCords = { middleY - radius - 6, middleY - radius - 6,
					middleY - radius + 12 };

			g2.fillPolygon(xCords, yCords, 3);

			g2.setFont(new Font("Default", Font.PLAIN, 50));

			int stringYPos = middleY - g2.getFont().getSize(); // initial Y
																// coordinate
																// for string to
																// be drawn at

			// draw grade#s
			for (int grade = 9; grade < 13; grade++) {

				g2.drawString("" + grade, middleX + radius + 75, // 75 pixels
																	// away from
																	// edge of
																	// circle
						stringYPos);

				stringYPos += g2.getFont().getSize();

			}

			if (gradeIndex > 12) { // "reset"
				gradeIndex = 9;
			}

			// Draw triangle pointer
			xCords = new int[] { middleX + radius + 45, middleX + radius + 65, // +##
																				// =
																				// pixels
																				// from
																				// edge
																				// of
																				// outer
																				// circle
					middleX + radius + 45 };

			yCords = new int[] {
					middleY - g2.getFont().getSize() / 2 - (10 - gradeIndex)
							* g2.getFont().getSize() - 5, // fontSize/2 to place
															// in middle of #
					middleY - g2.getFont().getSize() / 2 - (10 - gradeIndex)
							* g2.getFont().getSize() + 5, // 10-gradeIndex =
					middleY - g2.getFont().getSize() / 2 - (10 - gradeIndex)
							* g2.getFont().getSize() + 15 }; // appropriate
																// place for
																// pointer

			g2.fillPolygon(xCords, yCords, 3);

		} else { // normal procedure

			// Draw circles
			g2.setColor(Color.RED);

			g2.fillOval(middleX - radius, // outer circle
					middleY - radius, diameter, diameter);
			
			
			//Chase Edit
			lightSabers.setBounds(middleX - radius - 20, middleY - radius - 20, diameter + 40, diameter + 40);
			PickerGUI.pane.add(lightSabers);
			PickerGUI.pane.add(PickerGUI.background);
			
			
			g2.setColor(Color.GRAY);

			g2.fillOval(middleX - num * radius / den, // inner circle * scale as
														// fraction
					middleY - num * radius / den, num * diameter / den, num
							* diameter / den);

			g2.setColor(Color.BLACK);

			// Draw dynamic lines of outer circle
			for (int sector = 0; sector < sectors; sector++) {
				g2.drawLine(middleX, middleY,
						(int) (radius * Math.cos(Math.toRadians(theta)))
								+ middleX,
						(int) (radius * Math.sin(Math.toRadians(theta)))
								+ middleY);
				theta += delta;
			}

			if (spinType.equals("Free")) { // keep inner circle static if it
											// spun for picking a grade

				g2.setColor(Color.GRAY);

				g2.fillOval(middleX - num * radius / den, middleY - num
						* radius / den, num * diameter / den, num * diameter
						/ den);

				g2.setColor(Color.BLACK);

				int tempTheta = gradeTheta; // gradeTheta holds initial degree
											// when the inner spinner stopped
											// tempTheta keeps inner circle
											// static

				// Draw static lines of inner circle
				for (int sector = 0; sector < 4; sector++) {
					g2.drawLine(
							middleX,
							middleY,
							(int) ((num * radius / den) * Math.cos(Math
									.toRadians(tempTheta))) + middleX,
							(int) ((num * radius / den) * Math.sin(Math
									.toRadians(tempTheta))) + middleY);
					tempTheta -= sectorSizes[sector];
				}
			}

			// Draw pointer of outer circle
			g2.setColor(Color.BLACK);

			int[] xCords = { middleX - 6, middleX + 6, middleX },

			yCords = { middleY - radius - 6, middleY - radius - 6,
					middleY - radius + 12 };

			g2.fillPolygon(xCords, yCords, 3);

			if (gradeMap.size() != 0) { // to keep from spinning if no data
				// Cycle through homeroomList names
				try {

					if (nameIndex == currentList.size()) { // reset
						nameIndex = 0;
					}

					int[] fontSizes = { 16, 20, 28, 36, 50 };

					Font[] fonts = new Font[fontSizes.length];

					Color[] fontColors = { Color.RED, Color.BLACK };

					int sizeSum = 0, gap = 5; // gap in pixels between names,
												// sizeSum of pixels of fonts

					for (int fontIndex = 0; fontIndex < fontSizes.length; fontIndex++) {
						fonts[fontIndex] = new Font("Default", Font.PLAIN,
								fontSizes[fontIndex]);
						sizeSum += fontSizes[fontIndex];
					}

					/*
					 * 
					 * nameIndex represents the position in the List of the
					 * current student who is next to the pointer tempIndex
					 * holds place of where to start in the List based on the
					 * number of names displayed which is determined by the
					 * number of unique fonts
					 * 
					 * fonts.length-1 represents the number of non-winner fonts
					 * before the last font
					 * 
					 * nameIndex - (fonts.length-1) represents where tempIndex
					 * must start in the list for the nameIndex student to be in
					 * winner slot
					 * 
					 * if the tempIndex would be negative, we must subtract the
					 * difference from the size of the List so the tempIndex
					 * will wrap around when it is reset
					 * 
					 * ==========================================================
					 * =======================================================
					 * 
					 * stringYCord is where to start vertically drawing the
					 * names according to the various font sizes and is half of
					 * the total pixels of the all the names and spaces drawn at
					 * a time so that the middle name and biggest font will
					 * appear in the middle of the panel
					 * 
					 * 
					 * fontSizes[fontSizes.length-1]/2 adjusts for placing
					 * pointer in middle of largest font
					 * 
					 * +(this.getHeight() - fontSizes[fontSizes.length-1])/2
					 * places pointer at nameIndex winner
					 * 
					 * sizeSum - fontSizes[fontSizes.length-1] moves strings to
					 * be drawn up by sum of non-winning fonts
					 * 
					 * fonts.length * gap accounts for the space between names
					 * 
					 * ==========================================================
					 * =======================================================
					 * 
					 * Don't feel bad if you don't get it, its hard to explain
					 * in words without talking personally, or maybe I'm just
					 * bad at explaining it. Either way, I'm just amazed I came
					 * up with it to be honest
					 */

					int tempIndex = (nameIndex >= fonts.length - 1 ? nameIndex
							- (fonts.length - 1) : currentList.size()
							- (fonts.length - 1 - nameIndex)),

					stringYCord = fontSizes[fontSizes.length - 1]
							/ 2
							+ (this.getHeight() - fontSizes[fontSizes.length - 1])
							/ 2 + sizeSum - fontSizes[fontSizes.length - 1]
							+ fonts.length * gap;

					// Paint names
					for (int fontIndex = 0; fontIndex < fonts.length * 2 - 1; fontIndex++, tempIndex++) {
						// fonts.length * 2 - 1 for twice the non-winning fonts
						// plus the winner font
						// also could be written (fonts.length-1)*2 + 1
						if (tempIndex >= currentList.size()) {
							tempIndex = 0; // reset
						}

						String toDraw = currentListNames[tempIndex];

						/*
						 * if/else works so index goes
						 * 0,1,2,...fonts.length-2,fonts
						 * .length-1,fonts.length-2...2,1,0
						 */

						if (fontIndex < fonts.length) {
							g2.setFont(fonts[fontIndex]);
						} else {
							g2.setFont(fonts[fonts.length * 2 - 2 - fontIndex]);
						}

						g2.setColor(fontColors[fontIndex % 2]); // alternates
																// between red
																// and black

						g2.drawString(toDraw, middleX + radius + 75, // 75
																		// pixels
																		// away
																		// from
																		// outer
																		// circle
								stringYCord);

						stringYCord -= g2.getFont().getSize() + gap;

					}

					// Draw pointer that stays static in middle
					g2.setColor(Color.BLACK);

					xCords = new int[] { middleX + radius + 45,
							middleX + radius + 65, middleX + radius + 45 };

					yCords = new int[] {
							middleY - fonts[fonts.length - 1].getSize() / 2
									+ (fonts.length - 2) * gap - 10,
							middleY - fonts[fonts.length - 1].getSize() / 2
									+ (fonts.length - 2) * gap,
							middleY - fonts[fonts.length - 1].getSize() / 2
									+ (fonts.length - 2) * gap + 10 };

					g2.fillPolygon(xCords, yCords, 3);
				} catch (Exception e) {
					// JOptionPane.showMessageDialog(null,"Error with spin");
					// System.out.println(e.toString());
					e.printStackTrace();
				}
			}

		}
	}

	public void updateInfo(double[] w, int topHR, int topG, String type) {
		weights = w;
		topHomeroom = topHR;
		topGrade = topG;
		spinType = type;

	}

	public void updateMaps(HashMap<Integer, ArrayList<String>> grades,
			HashMap<Integer, ArrayList<String>> homerooms,
			HashMap<String, Integer> hrNums) {

		gradeMap = grades;
		homeroomMap = homerooms;
		hrNumberMap = hrNums;

	}

	public void updateNameArray() {
		currentListNames = new String[currentList.size()];

		for (int student = 0; student < currentList.size(); student++) {
			String[] temp = currentList.get(student).split("#");
			currentListNames[student] = temp[0] + " " + temp[1];
		}
	}

	public String getHRCode(int homeroom) {
		for (String key : hrNumberMap.keySet()) {
			if (hrNumberMap.get(key) == homeroom) {
				return key;
			}
		}
		return "";
	}

	public void run() {

		Random numGen = new Random();
		int factor = 2, limit = 1000 + numGen.nextInt(delta);

		gradeIndex = 9;

		int[] bounds = { 0, 500, 750, 875, 937, 968, 983, 990, 994, limit };
		int lowerBound = bounds[0], upperBound = bounds[1], boundIndex = 1, oldFib = 1, newFib = 1;

		theta = 270;
		repaint();

		if (spinType.equals("Homeroom")) {
			currentList = homeroomMap.get(topHomeroom);
			updateNameArray();
		} else if (spinType.equals("Grade")) {
			currentList = gradeMap.get(topGrade);
			updateNameArray();
		} else if (spinType.equals("Free")) {
			gradeSelected = false;
			currentList = new ArrayList<String>();

		} else if (spinType.equals("Reset")) {
			gradeSelected = false;
			currentList = new ArrayList<String>();
			updateNameArray();
			infoString = "";
		}

		hrCode = getHRCode(topHomeroom);

		if (spinType.equalsIgnoreCase("Homeroom")) {
			infoString = "Spinning for homeroom: " + hrCode;
		} else if (spinType.equalsIgnoreCase("Grade")) {
			//Chase Edit
			if (StudentPicker.teacherSpin) {
				infoString = "Spinning for Teachers";
			} else {
				infoString = "Spinning for grade: " + topGrade;
			}
		} else if (spinType.equalsIgnoreCase("Free")) {
			infoString = "Selecting grade";
		}

		if (this.spinType.equals("Free") && !gradeSelected) {

			int sum = 0;
			for (int i = 0; i < sectorSizes.length; i++) {
				sectorSizes[i] = (int) (weights[i] * 3.6);
				sum += sectorSizes[i];
			}
			if (sum < 360) {
				sectorSizes[sectorSizes.length - 1] += 360 - sum;
			}

			int sectorIndex = 0, degreeCounter = 0, circleCounter = 0;

			int[] gradeBounds = new int[bounds.length];
			gradeBounds[0] = 0;
			int shift = numGen.nextInt(361);
			System.out.println("Shift: " + shift);
			for (int i = 1; i < bounds.length; i++) {
				gradeBounds[i] = bounds[i] + shift;
			}
			upperBound = gradeBounds[1];

			for (int i = 0; i < gradeBounds[gradeBounds.length - 1] * factor; i++) {
				repaint();
				try {
					if (i >= lowerBound * factor && i < upperBound * factor) {
						Thread.sleep(newFib);
					} else {
						lowerBound = upperBound;
						upperBound = gradeBounds[++boundIndex];
						int temp = newFib;
						newFib += oldFib;
						oldFib = temp;
						// if (boundIndex == 2){
						// System.out.println("\tDone with bound"+
						// }
					}

					if (circleCounter == 360) {
						circleCounter = 0;
						sectorIndex = 0;
						degreeCounter = 0;
						gradeIndex = 9;
						theta = 270;
					}

					if (sectorIndex == sectorSizes.length) {
						sectorIndex = 0;
					}
					// JOptionPane.showMessageDialog(null,
					if (degreeCounter == (int) (sectorSizes[sectorIndex])) {
						sectorIndex++;
						gradeIndex++;
						degreeCounter = 0;
					} else {
						degreeCounter++;
					}
					circleCounter++;
				} catch (Exception e) {
				}
				theta++;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			this.currentList = gradeMap.get(gradeIndex);
			gradeSelected = true;
			updateNameArray();
			gradeTheta = theta;
			infoString = "Spinning for grade: " + gradeIndex;
		}

		factor = 3;
		nameIndex = numGen.nextInt(currentList.size());
		theta = 0;
		lowerBound = bounds[0];
		upperBound = bounds[1];
		boundIndex = 1;
		oldFib = 1;
		newFib = 1;

		for (int i = 0; i < bounds[bounds.length - 1] * factor; i++) {
			repaint();
			try {
				if (i >= lowerBound * factor && i < upperBound * factor) {
					Thread.sleep(newFib);
				} else {
					lowerBound = upperBound;
					upperBound = bounds[++boundIndex];
					int temp = newFib;
					newFib += oldFib;
					oldFib = temp;
				}

				if (i % delta == 0) {
					nameIndex++;
				}
			} catch (Exception e) {
			}
			theta++;
		}

		// Chase Edit
		if (!StudentPicker.teacherSpin) {
			infoString = "Winner homeroom: "
					+ getHRCode(Integer.parseInt(currentList.get(nameIndex)
							.split("#")[3]));
		} else {
			infoString = "";
		}

		// Chase Edit
		Image myPicture = null;
		String studentCode = getStudentCode(
				currentList.get(nameIndex).split("#")[0],
				currentList.get(nameIndex).split("#")[1]);
		try {
			myPicture = ImageIO.read(new File("Pictures/" + studentCode
					+ ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon picture = new ImageIcon(myPicture);

		if (!PickerGUI.ranOnce) {
			picLabel = new JLabel(picture);
			picLabel.setBounds(StudentPicker.width - 350, 90, 300, 400);
			PickerGUI.pane.add(picLabel);
			PickerGUI.pane.add(PickerGUI.background);
			PickerGUI.ranOnce = true;
		} else {
			picture.setImage(myPicture);
			picLabel = new JLabel(picture);
			picLabel.setBounds(StudentPicker.width - 350, 90, 300, 400);
			PickerGUI.pane.add(picLabel);
			PickerGUI.pane.add(PickerGUI.background);
		}

		repaint();

	}

	// Chase Edit
	public static String getStudentCode(String first, String last) {
		String fileName = "StudentListWithCodes.txt";
		String name = first + last;
		try {
			Scanner reader = new Scanner(new File(fileName));

			while (reader.hasNext()) {
				String line = reader.nextLine();
				// System.out.println(name + "  " + line);
				if (name.equals(line)) {
					return reader.nextLine();
				} else {
					reader.nextLine();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return null;
	}

}
