package org.posithing.notespring.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Switches the button text on clicking.
 */
public class SwitchButtonListener implements Listener
{
	protected Button button;
	protected String newText;
	protected String toolTipText;
	protected Image newImage;
	
	protected Listener switchListener;
	
	/**
	 * Switches the button text on clicking.
	 * 
	 * @param button the button
	 * @param newText the new text
	 * @param toolTipText the new toolTip text
	 * @param newImage the new image (may be <code>null</code>)
	 */
	public SwitchButtonListener(Button button, String newText, 
			String toolTipText, Image newImage)
	{
		this.button = button;
		this.newText = newText;
		this.toolTipText = toolTipText;
		this.newImage = newImage;
	}

	public void handleEvent(Event event)
	{
		button.removeListener(SWT.Selection, this);
		button.addListener(SWT.Selection, switchListener);
		button.setText(newText);
		button.setToolTipText(toolTipText);
		button.setImage(newImage);
	}

	/**
	 * Set the new switchListener for the button.
	 * 
	 * @param switchListener new switchListener
	 */
	public void setSwitchListener(SwitchButtonListener switchListener)
	{
		this.switchListener = switchListener;
	}
}
