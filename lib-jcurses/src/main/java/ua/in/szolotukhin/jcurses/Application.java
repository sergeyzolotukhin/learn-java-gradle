package ua.in.szolotukhin.jcurses;

import jcurses.event.item.ItemEvent;
import jcurses.event.item.ItemListener;
import jcurses.layout.DefaultLayoutManager;
import jcurses.system.CharColor;
import jcurses.system.Toolkit;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.component.menu.MenuList;
import jcurses.widgets.component.menu.PopUpMenu;
import jcurses.widgets.window.Message;
import jcurses.widgets.window.Window;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		final int screenWidth = Toolkit.getScreenWidth();
		final int screenHeight = Toolkit.getScreenHeight();

		Window window = new Window(screenWidth, screenHeight, true, "Schedule manager");
		window.show();

		Thread.sleep(5_000);

		Window window2 = new Window( 5, 5, screenWidth - 10, screenHeight - 10, true, "Standing data");
		window2.getRootPanel().setPanelColors(new CharColor(CharColor.BLUE, CharColor.YELLOW));
		window2.setBorderColors(new CharColor(CharColor.BLUE, CharColor.RED));
		window2.setTitleColors(new CharColor(CharColor.GREEN, CharColor.MAGENTA));
		window2.show();

		Thread.sleep(5_000);

		window2.close();

		Thread.sleep(5_000);

		window.close();

		Thread.sleep(5_000);
	}

	private static void tutorialOne() throws InterruptedException {
		Window window = new Window(100, 50, true, "Hai hai!");

		DefaultLayoutManager mgr = new DefaultLayoutManager();
		mgr.bindToContainer(window.getRootPanel());

		/*TextArea textArea = new TextArea();
		String text = "The runtime configuration has been deprecated for resolution. \n";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append(text);
		}
		textArea.setText(sb.toString());

		mgr.addWidget(textArea, 2, 2, 50, 20,
				WidgetsConstants.ALIGNMENT_CENTER, WidgetsConstants.ALIGNMENT_CENTER);
*/

		MenuList menu = new MenuList();
		menu.add("Command 1");
		menu.add("Command 2");
		menu.add("Command 3");
		menu.addListener(new ItemListener() {
			@Override
			public void stateChanged(ItemEvent event) {
				if(event.getId() == 0) {
					Message message = new Message("Message", "Pressed command " + event.getItem(), "OK");
					message.show();
				} else if (event.getId() == 1) {
					PopUpMenu popUpMenu = new PopUpMenu(60, 30, "Event");
					popUpMenu.add("Item selected " + event.getId() + " " + event.getItem());
					popUpMenu.show();
				} else {
					window.close();
					System.exit(0);
				}
			}
		});

		mgr.addWidget(menu, 0, 1, 50, 20,
				WidgetsConstants.ALIGNMENT_LEFT, WidgetsConstants.ALIGNMENT_TOP);

		window.show();

		Thread.currentThread();
		Thread.sleep(30000);

		window.close();
	}
}
