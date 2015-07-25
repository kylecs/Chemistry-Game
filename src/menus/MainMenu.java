package menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import game.ElementDatabase;
import game.Game;
import gfx.Screen;
import gfx.ScreenManager;

public class MainMenu implements Screen {
	ElementDatabase ed;

	FontMetrics fm;
	FontMetrics tfm;
	Font defaultFont;
	Font titleFont;
	Rectangle mode1;
	Rectangle mode2;
	Rectangle mode3;
	Rectangle mode4;

	int btnwidth = 350;
	int btnheight = 80;

	int btnx = (800 - btnwidth) / 2;
	ScreenManager manager;

	boolean timed = false;
	Rectangle timedbtn;
	Rectangle fixedHitbox;

	String mode1str = "Molar Mass Mode";
	String mode2str = "Atomic Symbol Mode";
	String mode3str = "Atomic Number Mode";
	String mode4str = "Electron Configuration Mode";
	String titleString = "Chemistry Game";

	public MainMenu(ScreenManager manager, ElementDatabase ed) {
		this.ed = ed;
		this.defaultFont = new Font("Verdana", Font.BOLD, 14);
		this.titleFont = new Font("Verdana", Font.BOLD, 20);
		this.manager = manager;

		int starty = 175;
		int inc = 100;
		mode1 = new Rectangle(btnx, starty, btnwidth, btnheight);
		mode2 = new Rectangle(btnx, starty + inc * 1, btnwidth, btnheight);
		mode3 = new Rectangle(btnx, starty + inc * 2, btnwidth, btnheight);
		mode4 = new Rectangle(btnx, starty + inc * 3, btnwidth, btnheight);

		timedbtn = new Rectangle(0, 0, 105, 30);
		fixedHitbox = new Rectangle(0, 0, 120, 70);

	}

	@Override
	public void mouseClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePress(MouseEvent e) {
		if (mode1.contains(e.getPoint())) {
			manager.setScreen(new Game(manager, 0, timed, ed));
		} else if (mode2.contains(e.getPoint())) {
			manager.setScreen(new Game(manager, 1, timed, ed));
		} else if (mode3.contains(e.getPoint())) {
			manager.setScreen(new Game(manager, 2, timed, ed));
		} else if (mode4.contains(e.getPoint())) {
			manager.setScreen(new Game(manager, 3, timed, ed));
		} else if (fixedHitbox.contains(e.getPoint())) {
			timed = !timed;
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
		if (tfm == null) {
			tfm = g.getFontMetrics(titleFont);
		}
		g.setFont(defaultFont);

		// draw buttons
		g.setColor(Color.BLACK);
		g.fillRect(mode1.x, mode1.y, mode1.width, mode1.height);
		g.fillRect(mode2.x, mode2.y, mode2.width, mode2.height);
		g.fillRect(mode3.x, mode3.y, mode3.width, mode3.height);
		g.fillRect(mode4.x, mode4.y, mode4.width, mode4.height);

		g.setColor(Color.ORANGE);
		g.fillRect(timedbtn.x, timedbtn.y, timedbtn.width, timedbtn.height);
		g.setColor(Color.BLACK);
		String timedstr = "Timed? " + (timed ? "Yes" : "No");
		g.drawString(timedstr, 5, 18);

		g.setColor(Color.WHITE);
		g.drawString(mode1str, (800 / 2) - (fm.stringWidth(mode1str) / 2),
				(mode1.y) + fm.getHeight() + 25);
		g.drawString(mode2str, (800 / 2) - (fm.stringWidth(mode2str) / 2),
				(mode2.y) + fm.getHeight() + 25);
		g.drawString(mode3str, (800 / 2) - (fm.stringWidth(mode3str) / 2),
				(mode3.y) + fm.getHeight() + 25);
		g.drawString(mode4str, (800 / 2) - (fm.stringWidth(mode4str) / 2),
				(mode4.y) + fm.getHeight() + 25);

		// title
		g.setColor(Color.BLACK);
		g.setFont(titleFont);

		g.drawString(titleString, (800 / 2)
				- (tfm.stringWidth(titleString) / 2), 70);
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
