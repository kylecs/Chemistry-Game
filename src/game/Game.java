package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Random;

import menus.ScoreScreen;
import gfx.Screen;
import gfx.ScreenManager;

public class Game implements Screen {

	Random rand;
	Font defaultFont;
	FontMetrics fm;

	ElementDatabase elements;
	int correct = 0;
	int incorrect = 0;
	Element target;
	Element opt1;
	Element opt2;
	Element opt3;
	Element opt4;

	Rectangle btn1;
	Rectangle btn2;
	Rectangle btn3;
	Rectangle btn4;

	// gui stuff
	int row1y = 300;
	int row2y = 450;
	int col1x = 80;
	int col2x = 475;

	int btnwidth = 250;
	int btnheight = 80;

	// game stuff

	int starttime;
	int timelimitseconds = 120;
	int timeremaining = 0;
	int timespent = 0;
	boolean timeLimit = false;

	ScreenManager manager;

	int mode = 0;
	public static final int MOLAR_MASS_MODE = 0;
	public static final int SYMBOL_MODE = 1;
	public static final int ATOMIC_MODE = 2;
	public static final int ELECTRON_CONFIG_MODE = 3;

	Rectangle exit;
	Rectangle fixedHitbox;

	public Game(ScreenManager manager, int mode, boolean timed,
			ElementDatabase ed) {
		this.elements = ed;
		this.manager = manager;
		this.defaultFont = new Font("Verdana", Font.BOLD, 14);
		this.mode = mode;
		this.timeLimit = timed;

		exit = new Rectangle(0, 0, 100, 30);
		fixedHitbox = new Rectangle(0, 0, 120, 70);

		btn1 = new Rectangle(col1x, row1y, btnwidth, btnheight);
		btn2 = new Rectangle(col1x, row2y, btnwidth, btnheight);
		btn3 = new Rectangle(col2x, row1y, btnwidth, btnheight);
		btn4 = new Rectangle(col2x, row2y, btnwidth, btnheight);

		rand = new Random();

		reset();
		cycle();
	}

	@Override
	public void mouseClick(MouseEvent e) {

	}

	@Override
	public void mouseRelease(MouseEvent e) {
	}

	@Override
	public void paint(Graphics g) {
		if (fm == null) {
			fm = g.getFontMetrics(defaultFont);
		}

		g.setColor(Color.BLACK);

		g.fillRect(btn1.x, btn1.y, (int) btn1.getWidth(),
				(int) btn1.getHeight());
		g.fillRect(btn2.x, btn2.y, (int) btn2.getWidth(),
				(int) btn2.getHeight());
		g.fillRect(btn3.x, btn3.y, (int) btn3.getWidth(),
				(int) btn3.getHeight());
		g.fillRect(btn4.x, btn4.y, (int) btn4.getWidth(),
				(int) btn4.getHeight());

		g.setFont(defaultFont);

		g.setColor(Color.WHITE);
		g.drawString(opt1.getDisplayString(mode), (btn1.x + (btnwidth / 2))
				- (fm.stringWidth(opt1.getDisplayString(mode)) / 2),
				(btn1.y + (btnheight / 2)));
		g.drawString(opt2.getDisplayString(mode), (btn2.x + (btnwidth / 2))
				- (fm.stringWidth(opt2.getDisplayString(mode)) / 2),
				(btn2.y + (btnheight / 2)));
		g.drawString(opt3.getDisplayString(mode), (btn3.x + (btnwidth / 2))
				- (fm.stringWidth(opt3.getDisplayString(mode)) / 2),
				(btn3.y + (btnheight / 2)));
		g.drawString(opt4.getDisplayString(mode), (btn4.x + (btnwidth / 2))
				- (fm.stringWidth(opt4.getDisplayString(mode)) / 2),
				(btn4.y + (btnheight / 2)));

		g.setColor(Color.ORANGE);
		g.fillRect((800 - btnwidth) / 2, 75, btnwidth, btnheight);
		g.setColor(Color.BLACK);
		g.drawString(
				target.getTargetDisplayString(mode),
				(((800 - btnwidth) / 2) + (btnwidth / 2))
						- (fm.stringWidth(target.getTargetDisplayString(mode)) / 2),
				(75 + (btnheight / 2)));

		g.setColor(Color.BLACK);
		String inst = "";
		switch (mode) {
		case Game.MOLAR_MASS_MODE:
			inst = "Select the element with molar mass provided in the yellow box below.";
			break;
		case Game.SYMBOL_MODE:
			inst = "Select the element with the symbol provided in the yellow box below.";
			break;
		case Game.ATOMIC_MODE:
			inst = "Select the element with the atomic number provided in the yellow box below.";
			break;
		case Game.ELECTRON_CONFIG_MODE:
			inst = "Select the electron configuration corresponding to the element provided in the yellow box below.";
			break;
		}
		g.drawString(inst, (800 / 2) - (fm.stringWidth(inst) / 2), 50);

		String timestring = "";
		if (timeLimit) {
			if (timeremaining < 15) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			timestring = "Time remaining: " + timeremaining;

		} else {
			timestring = "Time spent: " + timespent;
		}
		g.drawString(timestring,
				((80 + (btnwidth / 2)) - (fm.stringWidth(timestring) / 2)), 230);
		String correctstring = "Correct: " + correct;
		String incorrectstring = "Incorrect: " + incorrect;
		g.setColor(Color.BLACK);
		g.drawString(correctstring,
				((475 + (btnwidth / 2)) - (fm.stringWidth(correctstring) / 2)),
				230);
		g.drawString(
				incorrectstring,
				((475 + (btnwidth / 2)) - (fm.stringWidth(incorrectstring) / 2)),
				230 + fm.getHeight() + 5);

		g.setColor(Color.ORANGE);
		g.fillRect(exit.x, exit.y, exit.width, exit.height);
		g.setColor(Color.BLACK);
		g.drawString("End Game", 5, 18);

	}

	void cycle() {
		target = elements.randomElement();
		switch (rand.nextInt(4)) {
		case 0:
			opt1 = target;
			opt2 = elements.randomNot(target);
			opt3 = elements.randomNot(target);
			opt4 = elements.randomNot(target);
			break;
		case 1:
			opt1 = elements.randomNot(target);
			opt2 = target;
			opt3 = elements.randomNot(target);
			opt4 = elements.randomNot(target);
			break;
		case 2:
			opt1 = elements.randomNot(target);
			opt2 = elements.randomNot(target);
			opt3 = target;
			opt4 = elements.randomNot(target);
			break;
		case 3:
			opt1 = elements.randomNot(target);
			opt2 = elements.randomNot(target);
			opt3 = elements.randomNot(target);
			opt4 = target;
			break;
		default:
			try {
				throw new Exception("Error generating random numbers");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	void check(int opt) {
		boolean result = false;
		switch (opt) {
		case 1:
			result = (target.compare(opt1));
			break;
		case 2:
			result = (target.compare(opt2));
			break;
		case 3:
			result = (target.compare(opt3));
			break;
		case 4:
			result = (target.compare(opt4));
			break;
		}
		if (result == false) {
			incorrect++;

		} else {
			correct++;

		}
		cycle();
	}

	@Override
	public void init() {

	}

	@Override
	public void mousePress(MouseEvent e) {

		if (btn1.contains(e.getPoint())) {
			check(1);
		} else if (btn2.contains(e.getPoint())) {

			check(2);
		} else if (btn3.contains(e.getPoint())) {

			check(3);
		} else if (btn4.contains(e.getPoint())) {

			check(4);
		} else if (fixedHitbox.contains(e.getPoint())) {
			manager.setScreen(new ScoreScreen(correct, incorrect, mode,
					timeLimit, manager, elements));
		}
	}

	@Override
	public void reset() {
		starttime = (int) (System.currentTimeMillis() / 1000);
		timeremaining = timelimitseconds;
		cycle();

	}

	@Override
	public void update() {

		timespent = (int) ((System.currentTimeMillis() / 1000) - starttime);

		timeremaining = timelimitseconds - timespent;

		if (timeLimit) {
			if (timeremaining <= 0) {
				manager.setScreen(new ScoreScreen(correct, incorrect, mode,
						timeLimit, manager, elements));
			}
		}

	}

}
