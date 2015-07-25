package gfx;

public class ThreadUpdate implements Runnable {
	ScreenManager manager;
	Thread draw;

	public ThreadUpdate(ScreenManager manager) {
		this.manager = manager;
		draw = new Thread(this);
		draw.start();
	}

	@Override
	public void run() {
		while (true) {
			manager.repaint();
			manager.update();
		}

	}

}
