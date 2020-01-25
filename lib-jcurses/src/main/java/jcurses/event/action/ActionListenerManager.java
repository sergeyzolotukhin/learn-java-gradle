package jcurses.event.action;


import jcurses.event.Event;
import jcurses.event.ListenerManager;

/**
 * This class implements a listener manager to manage <code>jcurses.event.action.ActionEvent</code> instances and listener
 * on these. Only possible type of handled events is <code>jcurses.event.action.ActionEvent<code>,
 * of managed listeners id <code>jcurses.event.action.ActionListener</code>
 */
public class ActionListenerManager extends ListenerManager {

	protected void doHandleEvent(Event event, Object listener) {
		((ActionListener) listener).actionPerformed((ActionEvent) event);
	}

	protected void verifyListener(Object listener) {
		if (!(listener instanceof ActionListener)) {
			throw new RuntimeException("illegal listener type");
		}
	}

	protected void verifyEvent(Event event) {
		if (!(event instanceof ActionEvent)) {
			throw new RuntimeException("illegal event type");
		}
	}

}
