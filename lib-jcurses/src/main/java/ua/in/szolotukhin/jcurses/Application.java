package ua.in.szolotukhin.jcurses;

import jcurses.system.CharColor;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Label;
import jcurses.widgets.WidgetsConstants;
import jcurses.widgets.Window;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static void main(String[] args) throws InterruptedException {
		log.info("lib path: [{}]", System.getProperty("java.library.path"));

		Window w = new Window(100, 100, true, "Hai hai!");
		Label label = new Label("Hai hai!", new CharColor(CharColor.BLACK, CharColor.GREEN));

		DefaultLayoutManager mgr = new DefaultLayoutManager();
		mgr.bindToContainer(w.getRootPanel());
		mgr.addWidget(label, 0, 0, 100, 100, WidgetsConstants.ALIGNMENT_CENTER, WidgetsConstants.ALIGNMENT_CENTER);
		w.show();

		Thread.currentThread();
		Thread.sleep(3000);

		w.close();
	}
}
