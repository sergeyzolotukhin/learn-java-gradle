package jcurses.widgets.window.file;

import java.io.FileFilter;

public interface JCursesFileFilterFactory {
	
	public FileFilter generateFileFilter(String filterString);

}
