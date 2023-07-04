package jcurses.event.item;

import jcurses.event.Event;
import jcurses.event.ListenerManager;

/**
 * This class implements a listener manager to manage <code>jcurses.event.item.ItemEvent</code> instances and listener
 * on these. Only possible type of handled events is <code>jcurses.event.item.ItemEvent<code>,
 * of managed listeners id <code>jcurses.event.item.ItemListener</code>
 */
public class ItemListenerManager extends ListenerManager {

	protected void doHandleEvent(Event event, Object listener) {
		((ItemListener) listener).stateChanged((ItemEvent) event);
	}

	protected void verifyListener(Object listener) {
		if (!(listener instanceof ItemListener)) {
			throw new RuntimeException("illegal listener type");
		}
	}

	protected void verifyEvent(Event event) {
		if (!(event instanceof ItemEvent)) {
			throw new RuntimeException("illegal event type");
		}
	}

}
