package ex7.main.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import ex7.main.MainFrame;
import ex7.main.SnakeGame;

public class OptionListener implements ActionListener {

	private SnakeGame theGame;

	public OptionListener(SnakeGame theGame) {
		this.theGame = theGame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case MainFrame.ACTION_ENABLE_UHD:
			System.out.println("UHD");
			boolean b = ((JCheckBoxMenuItem) e.getSource()).getState();
			System.out.println(b);

			if (((JCheckBoxMenuItem) e.getSource()).getState()) {
				theGame.enableUHD(true);
			} else {
				theGame.enableUHD(false);
			}
			break;

		default:
			System.out.println("Anything!");
			break;
		}
	}

}
