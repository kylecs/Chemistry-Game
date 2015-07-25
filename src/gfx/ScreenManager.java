package gfx;

import input.Mouse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager extends JPanel {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	Screen screen;
	Mouse mouse;
	ThreadUpdate draw;

	public ScreenManager() {

		mouse = new Mouse(this);

		frame = new JFrame("Chemistry Game  --  by Kyle Spencer");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(this);
		frame.addMouseListener(mouse);
		frame.setIconImage(new ImageIcon(ScreenManager.class
				.getResource("/mole.jpg")).getImage());

		draw = new ThreadUpdate(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		if (screen == null)
			return;
		screen.paint(g);

	}

	void update() {
		if (screen == null)
			return;

		screen.update();
	}

	public void setScreen(Screen s) {
		this.screen = s;
		s.init();
	}

	public void mouseClick(MouseEvent e) {
		if (screen == null)
			return;

		screen.mouseClick(e);
	}

	public void mousePress(MouseEvent e) {
		if (screen == null)
			return;

		screen.mousePress(e);
	}

	public void mouseRelease(MouseEvent e) {
		if (screen == null)
			return;

		screen.mouseRelease(e);
	}
}
