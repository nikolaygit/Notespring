package org.posithing.notespring.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.posithing.notespring.views.CalendarData;
import org.posithing.notespring.views.CalendarView;
import org.posithing.notespring.views.SWTCalendar;


public class SynchronizeAction implements IViewActionDelegate
{
	CalendarView calendarView;

	public SynchronizeAction()
	{
		super();
	}

	public void init(IViewPart view)
	{
		calendarView = (CalendarView) view;
	}

	public void run(IAction action)
	{
		CalendarData.synchronizeData();
		SWTCalendar calendar = calendarView.getCalendar();
		calendar.refreshData();
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
	}
}
