package org.posithing.notespring.swt;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

/**
 * SwitchButtonListener with a Runnable
 */
public class RunnableSwitchButtonListener extends SwitchButtonListener
{
	protected Display display;
	protected Runnable runnable;
	protected int time;

	/**
	 * Switches the button text on clicking and runs the Runnable.
	 * 
	 * @param display the Display
	 * @param runnable the Runnable which will be executed on switch
	 * @param time milliseconds, how often to run the Runnable
	 * @param button the button
	 * @param newText the new text
	 * @param toolTipText the new toolTip text
	 * @param newImage the new image (may be <code>null</code>)
	 */
	public RunnableSwitchButtonListener(Display display, 
			Runnable runnable, int time, Button button, 
			String newText, String toolTipText, Image newImage)
	{
		super(button, newText, toolTipText, newImage);
		this.display = display;
		this.runnable = runnable;
		this.time = time;
	}
	
	@Override
	public void handleEvent(Event event)
	{
		super.handleEvent(event);
		if(display!=null)
			display.timerExec(time, runnable);
	}

}
