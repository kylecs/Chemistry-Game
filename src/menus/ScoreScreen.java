package menus;

import game.ElementDatabase;
import game.Game;
import gfx.Screen;
import gfx.ScreenManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class ScoreScreen implements Screen {
	ElementDatabase ed;
	int correct = 0;
	int incorrect = 0;
	double percent = 0;
	ScreenManager manager;
	FontMetrics fm;
	Font defaultFont;
	int mode;
	boolean timed;

	int btnwidth = 300;
	int btnheight = 80;
	Rectangle restart = new Rectangle((800 / 2) - (btnwidth / 2), 300,
			btnwidth, btnheight);
	Rectangle menu = new Rectangle((800 / 2) - (btnwidth / 2), 400, btnwidth,
			btnheight);

	public ScoreScreen(int correct, int incorrect, int mode, boolean timed,
			ScreenManager manager, ElementDatabase ed) {
		this.ed = ed;
		this.correct = correct;
		this.incorrect = incorrect;
		this.defaultFont = new Font("Verdana", Font.BOLD, 16);
		this.manager = manager;
		this.mode = mode;
		this.timed = timed;
	}

	@Override
	public void mouseClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePress(MouseEvent e) {
		if (restart.contains(e.getPoint())) {
			manager.setScreen(new Game(manager, 1, false, ed));
		} else if (menu.contains(e.getPoint())) {
			manager.setScreen(new MainMenu(manager, ed));
		}

	}

	@Override
	public void mouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		if (fm == null) {
			fm = g.getFontMetrics(defaultFont);

		}
		g.setFont(defaultFont);
		g.setColor(Color.BLACK);
		g.fillRect(restart.x, restart.y, restart.width, restart.height);
		g.fillRect(menu.x, menu.y, menu.width, menu.height);

		String top = "";
		switch (mode) {
		case Game.ATOMIC_MODE:
			top = "Atomic Number Mode";
			break;
		case Game.ELECTRON_CONFIG_MODE:
			top = "Electron Configuration Mode";
			break;
		case Game.MOLAR_MASS_MODE:
			top = "Molar Mass Mode";
			break;
		case Game.SYMBOL_MODE:
			top = "Atomic Symbol Mode";
			break;
		}
		top = top + " (" + (timed ? "timed" : "untimed") + ")";

		g.drawString(top, (800 / 2) - (fm.stringWidth(top) / 2), 70);

		String correctstring = "Correct: " + correct;
		String incorrectstring = "Incorrect: " + incorrect;
		g.setColor(Color.BLACK);
		g.drawString(correctstring,
				(restart.x + (btnwidth / 2))
						- (fm.stringWidth(correctstring) / 2), 200);
		g.drawString(incorrectstring,
				(restart.x + (btnwidth / 2))
						- (fm.stringWidth(incorrectstring) / 2),
				200 + fm.getHeight() + 5);
		g.setColor(Color.WHITE);
		g.drawString(
				"Restart?",
				(restart.x + (btnwidth / 2)) - (fm.stringWidth("Restart?") / 2),
				restart.y + (btnheight / 2));
		g.drawString("Main Menu",
				(menu.x + (btnwidth / 2)) - (fm.stringWidth("Main Menu") / 2),
				menu.y + (btnheight / 2));

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
