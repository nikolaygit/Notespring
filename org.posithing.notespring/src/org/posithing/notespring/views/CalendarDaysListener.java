package org.posithing.notespring.views;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.posithing.notespring.helpers.Colors;


public class CalendarDaysListener implements Listener
{
	private final static Color backGroundColor = Colors.INFO_BACKGROUND;
	private final static Color foreGroundColor = Colors.INFO_FOREGROUND;
	private final static Color selectionColor = Colors.LIST_SELECTION;
	private final static Color selectionTextColor = Colors.LIST_SELECTION_TEXT;
	
	Composite composite;
	Display display;
	
	private static int lastTimeEvent;
	
	private static Shell newShell;
	private static Table table;
	private static TableItem lastHoverItem;
	private static IFile lastSelectedFileName;
	
	public CalendarDaysListener(Composite composite)
	{
		this.composite = composite;
		this.display = composite.getDisplay();
	}
	
	@SuppressWarnings("unchecked")
	public void handleEvent(Event event)
	{
		if(event.time == lastTimeEvent)
			return;
		else
			lastTimeEvent = event.time;
		
		switch (event.type)
		{
			case SWT.Dispose:
			{
				disposeOpenedPopup();
				break;
			}
			case SWT.MouseUp:
			{
				disposeOpenedPopup();
				
				Object widgetData = event.widget.getData();
				if(widgetData == null) break;
				
				newShell = new Shell(display, SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
				newShell.setBackground(backGroundColor);
				newShell.setLayout(new FillLayout());
			   
				List<IFile> curDayFiles = (List<IFile>) widgetData;
				table = new Table(newShell, SWT.BORDER);
			    for (int i = 0; i < curDayFiles.size(); i++) {
			      TableItem item = new TableItem(table, SWT.NONE);
			      IFile file = curDayFiles.get(i);
			      String fileName = file.getName();
			      item.setText(fileName);
			      item.setData(file);
			    }
			    // Disable native tooltip
			    table.setToolTipText("");
			    table.setBackground(backGroundColor);
			    table.setForeground(foreGroundColor);
			    
			    // MOUSE HOVER LISTENER - to change the colors
			    Listener listener = new Listener(){
					public void handleEvent(Event event){
						if(!event.widget.isDisposed())
						{
							switch (event.type){
								case SWT.MouseHover:
								{
									TableItem item = table.getItem(new Point(event.x, event.y));
									if (item != null)
									{
										if(item != lastHoverItem)
										{
											// change hover colors
											if(lastHoverItem != null)
											{
												lastHoverItem.setBackground(backGroundColor);
												lastHoverItem.setForeground(foreGroundColor);
											}
											
											item.setBackground(selectionColor);
											item.setForeground(selectionTextColor);
											
											lastHoverItem = item;
										}
									}
								}
							}
						}}};
				table.addListener(SWT.MouseHover, listener);
			    table.addListener(SWT.MouseExit, new Listener(){
					public void handleEvent(Event event){disposeOpenedPopup();}}
				);
			    
			    table.addListener(SWT.MouseUp, new Listener(){
			    	public void handleEvent(Event event){
			    		Table table = (Table) event.widget;
			    		switch (event.type)
			    		{
			    			case SWT.MouseUp:
			    				TableItem item = table.getItem(new Point(event.x, event.y));
			    				if (item != null)
								{
				    				// Assuming table is single select, set the selection as if
				    				// the mouse down event went through to the table
			    					lastSelectedFileName = (IFile) item.getData();
			    					table.notifyListeners(SWT.Selection, event);
								}
			    				// fall through
			    			case SWT.MouseExit:
			    				disposeOpenedPopup();
			    				break;
			    		}
			    	}});
			    
			    table.addListener(SWT.Selection, new Listener(){
			    	public void handleEvent(Event event){
			    		switch (event.type)
			    		{
				    		case SWT.Selection:
				    			if(event.x > 0 && !event.widget.isDisposed())
				    			{
				    				CalendarView.openFile(lastSelectedFileName);
					    			break;
				    			}
			    		}}});
			    
			    
				Point size = newShell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				newShell.setBounds(
						Display.getCurrent().getCursorLocation().x, 
						Display.getCurrent().getCursorLocation().y, 
						size.x, 
						size.y);
				newShell.setVisible(true);
			}
		}
	}

	private void disposeOpenedPopup()
	{
		if(newShell!=null)
		{
			newShell.dispose();
			newShell = null;
		}
		table = null;
		lastHoverItem = null;
	}
}
