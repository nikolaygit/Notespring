package org.posithing.notespring.helpers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Colors
{
	/**
	 * The Default Display
	 */
	public final static Display display = Display.getDefault();

	public final static Color WHITE = display.getSystemColor(SWT.COLOR_WHITE);
	public final static Color BLACK = display.getSystemColor(SWT.COLOR_BLACK);
	public final static Color SOFT_BLUE = new Color(display, 158, 191, 245);
	public final static Color SOFT_ORANGE = new Color(display, 245, 212, 158);
	public final static Color SOFT_GREEN = new Color(display, 158, 245, 168);
	public final static Color[] LINE_COLORS = new Color[] {
		new Color(display, 227, 237, 252),
		new Color(display, 205,222,249),
		new Color(display, 136, 176, 242),
		new Color(display, 67, 131, 234)};
	public final static int[] LINE_PERCENTAGES = new int[] {10, 67, 100};
	
	public final static Color INFO_BACKGROUND = display.getSystemColor(SWT.COLOR_INFO_BACKGROUND);
	public final static Color INFO_FOREGROUND = display.getSystemColor(SWT.COLOR_INFO_FOREGROUND);
	public final static Color LIST_SELECTION = display.getSystemColor(SWT.COLOR_LIST_SELECTION);
	public final static Color LIST_SELECTION_TEXT = display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT);
	//public final static Color LIST_SELECTION = new Color(display, 153, 204, 255);
	
	public static Color getColor(int R, int G, int B)
	{
		return new Color(display, R, G, B);
	}
}
