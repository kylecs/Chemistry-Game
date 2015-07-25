package init;

import menus.MainMenu;
import game.ElementDatabase;
import gfx.ScreenManager;

public class Init {
	public Init() {
		//testing
		ScreenManager screen = new ScreenManager();
		screen.setScreen(new MainMenu(screen, new ElementDatabase()));
	}

	public static void main(String[] args) {
		new Init();
	}
}
