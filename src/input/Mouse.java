package input;

import gfx.ScreenManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	ScreenManager manager;

	public Mouse(ScreenManager manager) {
		this.manager = manager;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		manager.mouseClick(e);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		manager.mousePress(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		manager.mouseRelease(e);
	}

}
