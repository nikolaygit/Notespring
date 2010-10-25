package org.posithing.notespring.views;

import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.posithing.notespring.helpers.Colors;
import org.posithing.notespring.swt.Clock;


public class ClockView extends ViewPart
{
	public static final String VIEW_ID = "org.posithing.notespring.views.ClockView";

	Composite comp;
	Label clockLabel;
	Clock clock;
	
	public ClockView()
	{
	}

	@Override
	public void createPartControl(final Composite parent)
	{
		final Display display = parent.getDisplay();
		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(
				GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		comp = new Composite(parent, SWT.BORDER);
		comp.setLayout(new RowLayout());
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		clockLabel = new Label(comp, SWT.NONE);
		clockLabel.setText("00:00:00");
		
		setClockBackground(Colors.BLACK);
		setClockBackground(Colors.WHITE);
		
	    clock = new Clock(display, clockLabel, formatter);
	    display.timerExec(0, clock);
	}
	
	public void setClockBackground(Color color)
	{
		comp.setBackground(color);
		clockLabel.setBackground(color);
	}
	
	public void setClockForeground(Color color)
	{
		clockLabel.setForeground(color);
	}

	public Clock getClock()
	{
		return clock;
	}

	@Override
	public void setFocus()
	{
	}
}
