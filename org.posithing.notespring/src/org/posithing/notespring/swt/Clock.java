package org.posithing.notespring.swt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class Clock implements Runnable
{
	Display display;
	Label label;
	SimpleDateFormat formatter;
	int delay;
	
	Calendar calendar;

	/**
	 * Shows the clock time. The given label will be updated every second.
	 * 
	 * @param display the Display
	 * @param label the label on which to show it
	 * @param formatter clock formatter
	 */
	public Clock(Display display, Label label, SimpleDateFormat formatter)
	{
		this.delay = 1000;
		init(display, label, formatter);
	}
	
	/**
	 * Shows the clock time.
	 * 
	 * @param display the Display
	 * @param label the label on which to show it
	 * @param formatter clock formatter
	 * @param delay milliseconds, how often to update the label
	 */
	public Clock(Display display, Label label, SimpleDateFormat formatter, int delay)
	{
		this.delay = delay;
		init(display, label, formatter);
	}

	private void init(Display display, Label label, SimpleDateFormat formatter)
	{
		this.label = label;
		this.display = display;
		this.formatter = formatter;
	}

	public void run()
	{
        if (label.isDisposed())
          return;
		calendar = Calendar.getInstance();
		
        // format and display the timer
        Date curCounterDate = calendar.getTime();
		String formattedDate = formatter.format(curCounterDate);
        label.setText(formattedDate);
        display.timerExec(delay, this);
	}

	public void setDelay(int delay)
	{
		this.delay = delay;
	}

	/**
	 * Applies the given pattern to the label formatter.
	 * 
	 * @param pattern
	 */
	public void applyPattern(String pattern)
	{
		formatter.applyPattern(pattern);
	}
}
