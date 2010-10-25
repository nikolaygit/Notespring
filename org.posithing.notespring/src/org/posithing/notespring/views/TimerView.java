package org.posithing.notespring.views;

import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;
import org.posithing.notespring.NotespringPlugIn;
import org.posithing.notespring.swt.RunnableSwitchButtonListener;
import org.posithing.notespring.swt.Timer;


public class TimerView extends ViewPart
{
	public static final String VIEW_ID = "org.posithing.notespring.views.TimerView";

	Composite comp;
	Label timerLabel;
	Timer timer;
	
	public TimerView()
	{
	}

	@Override
	public void createPartControl(final Composite parent)
	{
		final Display display = parent.getDisplay();
		GridLayout timerLayout = new GridLayout();
		timerLayout.numColumns = 2;
		parent.setLayout(timerLayout);
		parent.setLayoutData(new GridData(
				GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

		final String startStr = "Start";
		final String pauseStr = "Pause";
		final String stopStr = "Stop";
	    final Image startImage = NotespringPlugIn.getImage(
	    		parent.getShell(), "player_play-16x16.png");
	    final Image pauseImage = NotespringPlugIn.getImage(
	    		parent.getShell(), "player_pause-16x16.png");
	    final Image stopImage = NotespringPlugIn.getImage(
	    		parent.getShell(), "player_stop-16x16.png");


		comp = new Composite(parent, SWT.BORDER);
	    GridData timerGridData = new GridData();
	    timerGridData.horizontalSpan = 2;
	    comp.setLayoutData(timerGridData);
	    comp.setLayout(new GridLayout());
		
	    timerLabel = new Label(comp, SWT.NONE);
	    
	    final Button startButton = new Button(parent, SWT.PUSH);
	    startButton.setImage(startImage);
	    startButton.setToolTipText(startStr);
	    
	    Button stopButton = new Button(parent, SWT.PUSH);
	    stopButton.setImage(stopImage);
	    stopButton.setToolTipText(stopStr);
	    
	    timer = new Timer(display, timerLabel, formatter);
	    
	    final RunnableSwitchButtonListener switchListener1 = new RunnableSwitchButtonListener(
	    		display, timer, timer.getDelay(), startButton, 
	    		pauseStr, pauseStr, pauseImage);
	    final RunnableSwitchButtonListener switchListener2 = new RunnableSwitchButtonListener(
	    		display, timer, -1, startButton, 
	    		startStr, startStr, startImage);
	    switchListener1.setSwitchListener(switchListener2);
	    switchListener2.setSwitchListener(switchListener1);
	    
	    startButton.addListener(SWT.Selection, switchListener1);
	    
	    stopButton.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	timer.resetTimer();
	        	display.timerExec(-1, timer);
	        	if(startButton.getText().equals(pauseStr))
	        	{
	        		switchListener2.handleEvent(event);
	        	}
	        }
	      });
	}
	
	public void setTimerBackground(Color color)
	{
		comp.setBackground(color);
		timerLabel.setBackground(color);
	}
	
	public void setTimerForeground(Color color)
	{
		timerLabel.setForeground(color);
	}
	
	public Timer getTimer()
	{
		return timer;
	}

	@Override
	public void setFocus()
	{
	}
}
