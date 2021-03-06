package ex7.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ex7.main.log.OptionListener;

/**
 * The main game frame hosts the SnakeGame.
 * 
 * @author Informatik II, Pascal Gepperth (4005085)
 */
public class MainFrame {

	public static final String ACTION_ENABLE_UHD = "Enable UHD";

	public static final String ACTION_ENABLE_POWERUPS = "Enable PowerUp";

	public static final String ACTION_ENABLE_INFINITY_TUNNELS = "Enable Infinity-Tunnels";

	/**
	 * The Window JFrame in which the game is placed
	 */
	private JFrame gameFrame;

	/**
	 * The JMenuBar for the menus to place in.
	 */
	private JMenuBar menuBar;

	/**
	 * The JMenu for the options.
	 */
	private JMenu optionMenu;

	// TODO Further bonus content
//	/**
//	 * The different dialogue options.
//	 */
//	private JMenuItem setFoodItmes, setSize;

	/**
	 * The different toggle options
	 */
	private JCheckBoxMenuItem enableUHD, enablePowerups, enableInfinityTunnels;

	/**
	 * Frame width upon creation.
	 */
	public int initFrameWidth = 840;

	/**
	 * Frame height upon creation.
	 */
	public int initFrameHeight = 600;

	/**
	 * initial width=height of a tile
	 */
	public static int tileSize = 20;

	/**
	 * The panel within which a game is hosted.
	 */
	private MyPanel thePanel;

	/**
	 * Width of the panel for the game.
	 */
	private int panelWidth;

	/**
	 * Height of the panel for the game.
	 */
	private int panelHeight;

	/**
	 * Left-Top Area - in which the games can add game control stuff.
	 */
	private Rectangle controlArea;

	/**
	 * Right-Top Area - in which the current score is displayed.
	 */
	private Rectangle scoreArea;

	/**
	 * Game Area - in which the actual game is shown.
	 */
	private Rectangle gameArea;

	/**
	 * Reference to the current game that is currently displayed and played.
	 */
	private SnakeGame theGame;

	/**
	 * The main game frame - with which the initial game is created.
	 */
	public MainFrame() {
		// create first an empty JFrame
		gameFrame = new JFrame("MainFrame");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ensure that all processes are stopped.
		gameFrame.setSize(initFrameWidth, initFrameHeight);
		gameFrame.setLocationRelativeTo(null); // center the frame
		gameFrame.setResizable(false); // we do not allow resizing.
		gameFrame.setVisible(true);
		gameFrame.validate();

		// Determine size of the panel area (the size remaining to display the game)
		panelWidth = gameFrame.getContentPane().getWidth();
		panelHeight = gameFrame.getContentPane().getHeight();

		// start the game now, initialize the game and add it to the game panel.
		initGame();
	}

	/**
	 * Starts the game in a new panel and adds this panel to the JFrame.
	 */
	private void initGame() {
		if (thePanel != null) { // remove the old panel if one was already created.
			gameFrame.remove(thePanel);
		}
		// create option menu
		menuBar = new JMenuBar();

		// fill menu bar
		optionMenu = new JMenu("Options");
		menuBar.add(optionMenu);

		// now create a new panel and initialize the game within it.
		thePanel = new MainFrame.MyPanel();

		initFullGamePanel(panelWidth, panelHeight - menuBar.getPreferredSize().height);
		theGame = new SnakeGame(this.gameFrame, this.thePanel, this.gameArea, this.controlArea);

		// create actionListener for option menu
		OptionListener listener = new OptionListener(theGame);

		// fill option menu
		enableUHD = new JCheckBoxMenuItem(ACTION_ENABLE_UHD);
		enableUHD.addActionListener(listener);
		optionMenu.add(enableUHD);

		enablePowerups = new JCheckBoxMenuItem(ACTION_ENABLE_POWERUPS);
		enablePowerups.addActionListener(listener);
		optionMenu.add(enablePowerups);

		enableInfinityTunnels = new JCheckBoxMenuItem(ACTION_ENABLE_INFINITY_TUNNELS);
		enableInfinityTunnels.addActionListener(listener);
		optionMenu.add(enableInfinityTunnels);

		gameFrame.setTitle(theGame.getName());
		gameFrame.setJMenuBar(menuBar);
		gameFrame.add(thePanel);
		gameFrame.validate();
	}

	/**
	 * Initializes the three areas that this class hosts. controlArea and gameArea
	 * should be filled with contents by the implementing game. scoreArea is filled
	 * by ScoreObjects.
	 * 
	 * @param width           The width of the panel, wherein the game-relevant
	 *                        areas are placed.
	 * @param height          The height of the panel, wherein the game-relevant
	 *                        areas are placed.
	 * @param widthConstraint The width-constraint - typically the tile width - in
	 *                        order to center the grid-game area.
	 * @param hightConstraint The corresponding height-constraint.
	 */
	private void initFullGamePanel(int width, int height) {
		int pad = 5;
		int controlHeight = tileSize * 3;
		int gameAreaWidth = ((width) / tileSize) * tileSize;
		int gameAreaHeight = ((height - controlHeight) / tileSize) * tileSize;

		// initialization of control area (on the left top)
		controlArea = new Rectangle(pad, pad, 2 * gameAreaWidth / 3 - (pad * 2), controlHeight - (pad * 2));
		// initialization of score / progress information area
		scoreArea = new Rectangle(controlArea.width + pad * 3, controlArea.y, controlArea.width, controlHeight);
		// rest is the game area - divisible by the defined width and height constraint.
		gameArea = new Rectangle(0, controlHeight, gameAreaWidth, gameAreaHeight);
	}

	/**
	 * Inner class to give the game panel the necessary additional features.
	 */
	private class MyPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		/**
		 * Draws the game board into this Panel within the frame. This method is called
		 * indirectly via java.awt by means of the repaint() Method.
		 */
		public void paintComponent(Graphics gg) {
			// first paint the default JPanel appearance.
			super.paintComponent(gg);
			// Cast to Graphics2D - higher flexibility in the graphics.
			Graphics2D g = (Graphics2D) gg;
			// Now draw the defaults background color - here WHITE.
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, panelWidth, panelHeight);

			// paint the game
			theGame.paintGameArea(g);

			// paint the controls
			theGame.paintControlArea(g);

			// paint the scores
			theGame.getScores().paintScore(g, scoreArea, tileSize);
		}
	}

	/**
	 * The main class to start this game.
	 * 
	 * @param args Arguments are not considered here.
	 */
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// Create a class object that exists as long as its JFrame is visible.
		new MainFrame();
	}
}