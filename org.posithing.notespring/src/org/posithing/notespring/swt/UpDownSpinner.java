package org.posithing.notespring.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TypedListener;

/**
 * Spinner with up/down arrows.
 */
public class UpDownSpinner extends Composite
{
	private int BUTTON_WIDTH = 20;
	private int BUTTON_HEIGHT = 20;

	Button up, down;
	int counter = 0;

	/**
	 * UpDownSpinner with button Width and Height.
	 * 
	 * @param parent Composite
	 * @param style SWT Style
	 * @param buttonWidth button width
	 * @param buttonHeight button height
	 */
	public UpDownSpinner(Composite parent, int style, int buttonWidth, int buttonHeight)
	{
		super(parent, style);
		BUTTON_WIDTH = buttonWidth;
		BUTTON_HEIGHT = buttonHeight;
		init(style);
	}

	/**
	 * UpDownSpinner.
	 * 
	 * @param parent Composite
	 * @param style SWT Style
	 */
	public UpDownSpinner(Composite parent, int style)
	{
		super(parent, style);
		init(style);
	}

	private void init(int style)
	{
		up = new Button(this, style | SWT.ARROW | SWT.UP);
		down = new Button(this, style | SWT.ARROW | SWT.DOWN);

		addListener(SWT.Resize, new Listener()
		{
			public void handleEvent(Event e)
			{
				resize();
			}
		});

		addListener(SWT.FocusIn, new Listener()
		{
			public void handleEvent(Event e)
			{
				focusIn();
			}
		});
	}
	
	public void addUpListener(int eventType, Listener listener)
	{
		up.addListener(eventType, listener);
	}
	
	public void addDownListener(int eventType, Listener listener)
	{
		down.addListener(eventType, listener);
	}

	void verify(Event e)
	{
		try
		{
			Integer.parseInt(e.text);
		}
		catch (NumberFormatException ex)
		{
			e.doit = false;
		}
	}

	void focusIn()
	{
	}

	void resize()
	{
		Point pt = computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int textWidth = pt.x - BUTTON_WIDTH;
		int buttonHeight = pt.y / 2;

		up.setBounds(textWidth, 0, BUTTON_WIDTH, buttonHeight);
		down.setBounds(textWidth, pt.y - buttonHeight, BUTTON_WIDTH, buttonHeight);
	}

	public Point computeSize(int wHint, int hHint, boolean changed)
	{
		int width = BUTTON_WIDTH;
		int height = BUTTON_HEIGHT;

		if (wHint != SWT.DEFAULT)
			width = wHint;

		if (hHint != SWT.DEFAULT)
			height = hHint;

		return new Point(width, height);
	}

	public void addSelectionListener(SelectionListener listener)
	{
		if (listener == null)
			throw new SWTError(SWT.ERROR_NULL_ARGUMENT);
		addListener(SWT.Selection, new TypedListener(listener));
	}
}
