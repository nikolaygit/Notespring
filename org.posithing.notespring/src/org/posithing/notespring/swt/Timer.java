package org.posithing.notespring.swt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class Timer implements Runnable
{
	Display display;
	Label label;
	SimpleDateFormat formatter;
	int delay;
	
	Calendar calendar;
	private boolean rollHour;

	/**
	 * Shows a timer. The given label will be updated every second.
	 * 
	 * @param display the Display
	 * @param label the label on which to show it
	 * @param formatter timer formatter
	 */
	public Timer(Display display, Label label, SimpleDateFormat formatter)
	{
		this.display = display;
		this.label = label;
		this.formatter = formatter;
		this.delay = 1000;
        resetTimer();
	}

	/**
	 * Shows a timer
	 * 
	 * @param display the Display
	 * @param label the label on which to show it
	 * @param formatter timer formatter
	 * @param delay milliseconds, how often to update the label
	 */
	public Timer(Display display, Label label, SimpleDateFormat formatter, int delay)
	{
		this.display = display;
		this.label = label;
		this.formatter = formatter;
		this.delay = delay;
        resetTimer();
	}
	
	/**
	 * Resets the timer and shows the time on the label.
	 */
	public void resetTimer()
	{
		calendar = Calendar.getInstance();
		calendar.clear();
		showTime();
	}

	public void run()
	{
        if (label.isDisposed())
          return;
        long secLapsed = delay/1000L;
        calendar.roll(Calendar.SECOND, (int) secLapsed);
        
        // Roll others
        int calSecond = calendar.get(Calendar.SECOND);
        int calMinute = calendar.get(Calendar.MINUTE);
        if(calSecond == 0)
        {
        	calendar.roll(Calendar.MINUTE, 1);
        }
        if(rollHour)
        {
        	calendar.roll(Calendar.HOUR_OF_DAY, (int) 1);
        	rollHour = false;
        }
        if(calMinute == 59 && calSecond == 59)
        {
        	rollHour = true;
        }
        
        showTime();
        display.timerExec(delay, this);
	}

	public int getDelay()
	{
		return delay;
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
	
	/**
	 * Shows the formatted calendar Time on the label.
	 */
	protected void showTime()
	{
		Date curCounterDate = calendar.getTime();
		String formattedDate = formatter.format(curCounterDate);
        label.setText(formattedDate);
	}
}
