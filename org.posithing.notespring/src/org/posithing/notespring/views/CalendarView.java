package org.posithing.notespring.views;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISizeProvider;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

public class CalendarView extends ViewPart implements ISizeProvider
{
	public static final String VIEW_ID = "org.posithing.notespring.views.CalendarView";

	private static SWTCalendar calendar;
	private static IWorkbenchPage page;
	
	/**
	 * The constructor.
	 */
	public CalendarView()
	{
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.wrap = false;
		parent.setLayout(rowLayout);
		
		calendar = new SWTCalendar(parent);
		page = getSite().getPage();
	}
	
	public static void openFile(IFile file)
	{
		try
		{
			IDE.openEditor(page, file);
		}
		catch (PartInitException e)
		{
			showError("The File \""+file.getName()+"\" could not be opened:\n\n"
					+e.getMessage());
			e.printStackTrace();
		}
	}

	protected static void showError(String message)
	{
		MessageDialog.openError(calendar.getParent().getShell(),
				"Calendar View", message);
	}
	
	protected static void showMessage(String message)
	{
		MessageDialog.openInformation(calendar.getParent().getShell(),
				"Calendar View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
		calendar.setFocus();
	}

	public int computePreferredSize(boolean width, int availableParallel, int availablePerpendicular,
			int preferredResult)
	{
		return width ? 40 : 145;
	}

	public int getSizeFlags(boolean width)
	{
		return SWT.MIN | SWT.MAX;
	}

	public SWTCalendar getCalendar()
	{
		return calendar;
	}
}
