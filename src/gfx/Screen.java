package gfx;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface Screen {
	void mouseClick(MouseEvent e);

	void mousePress(MouseEvent e);

	void mouseRelease(MouseEvent e);

	void paint(Graphics g);

	void init();

	void reset();

	void update();
}
