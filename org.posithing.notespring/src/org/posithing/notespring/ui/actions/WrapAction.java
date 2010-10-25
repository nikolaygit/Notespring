package org.posithing.notespring.ui.actions;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.posithing.notespring.NotespringConstants;
import org.posithing.notespring.NotespringPlugIn;


public class WrapAction implements IWorkbenchWindowActionDelegate
{
	private static NotespringPlugIn plugin;
	
	IWorkbenchWindow activeWindow = null;

	public void run(IAction proxyAction)
	{
		// change preferences
		Preferences prefs = plugin.getPluginPreferences();
		boolean isWrapped = prefs.getBoolean(NotespringConstants.P_WRAP);
		prefs.setValue(NotespringConstants.P_WRAP, !isWrapped);
		plugin.savePluginPreferences();

//		System.out.println("CLICK isWrapped = "+!isWrapped);
		// change tooltip
		if(isWrapped)
			proxyAction.setToolTipText("Wrap text");
		else
			proxyAction.setToolTipText("Unwrap text");
	}

	// IActionDelegate method
	public void selectionChanged(IAction proxyAction, ISelection selection)
	{
		// do nothing, action is not dependent on the selection
	}

	// IWorkbenchWindowActionDelegate method
	public void init(IWorkbenchWindow window)
	{
		activeWindow = window;
		plugin = NotespringPlugIn.getDefault();
	}

	// IWorkbenchWindowActionDelegate method
	public void dispose()
	{
		// nothing to do
	}
}
