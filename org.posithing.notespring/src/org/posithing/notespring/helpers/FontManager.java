package org.posithing.notespring.helpers;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

public class FontManager
{
	private static FontRegistry fontRegistry = new FontRegistry();

	static
	{
		addFont(FontSymbolicNames.DEFAULT, "Tahoma", 8);
		addFont(FontSymbolicNames.DEFAULT_BIGGER, "Tahoma", 10);
	}
	
	public static FontRegistry getFontRegistry()
	{
		return fontRegistry;
	}

	/**
	 * Adds the given font to the Manager. Font style is SWT.NORMAL.
	 * 
	 * @param symbolicName the symbolic font name
	 * @param fontName font name
	 * @param size size in pixels
	 */
	public static void addFont(String symbolicName, String fontName, int size)
	{
		addFont(symbolicName, fontName, size, SWT.NORMAL);
	}
	
	/**
	 * Adds the given font to the Manager.
	 * 
	 * @param symbolicName the symbolic font name
	 * @param fontName font name
	 * @param size size in pixels
	 * @param style A bitwise combination of NORMAL, ITALIC and BOLD 
	 */
	public static void addFont(String symbolicName, String fontName, int size, int style)
	{
		fontRegistry.put(symbolicName, new FontData[]{
				new FontData(fontName, size, style)});
	}
	
	/**
	 * @param symbolicName
	 * @return the font for this symbolicName
	 */
	public static Font getFont(String symbolicName)
	{
		return fontRegistry.get(symbolicName);
	}
}
