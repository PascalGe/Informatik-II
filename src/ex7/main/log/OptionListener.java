package ex7.main.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import ex7.main.MainFrame;
import ex7.main.SnakeGame;

/**
 * The OptionMenu's ActionListener.
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class OptionListener implements ActionListener {

	private SnakeGame theGame;

	/**
	 * Constructor
	 * 
	 * @param theGame - the game the forward changes.
	 */
	public OptionListener(SnakeGame theGame) {
		this.theGame = theGame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case MainFrame.ACTION_ENABLE_UHD:

			if (((JCheckBoxMenuItem) e.getSource()).isSelected()) {
				theGame.enableUHD(true);
			} else {
				theGame.enableUHD(false);
			}
			break;
		case MainFrame.ACTION_ENABLE_POWERUPS:
			if (((JCheckBoxMenuItem) e.getSource()).isSelected()) {
				theGame.enablePowerups(true);
			} else {
				theGame.enablePowerups(false);
			}
			break;
		case MainFrame.ACTION_ENABLE_INFINITY_TUNNELS:
			if (((JCheckBoxMenuItem) e.getSource()).isSelected()) {
				theGame.enableInfinityTunnels(true);
			} else {
				theGame.enableInfinityTunnels(false);
			}
			break;
		default:
			System.out.println("Anything!");
			break;
		}
	}

}
