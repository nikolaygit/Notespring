package org.posithing.notespring;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * Notespring Workbench Window Advisor.
 */
public class NotespringWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{
	public NotespringWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer)
	{
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(final IActionBarConfigurer configurer)
	{
		return new NotespringActionBuilder(configurer);
	}

	@Override
	public void preWindowOpen()
	{
		final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 600));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowFastViewBars(true);
		configurer.setShowProgressIndicator(true);
	}

	// NOT SOLVING THE PROBLEM:
	// !MESSAGE The workspace exited with unsaved changes in the 
	// previous session; refreshing workspace to recover changes.
//	@Override
//	public boolean preWindowShellClose()
//	{
//		try
//		{
//			// save the full workspace before quit
//			ResourcesPlugin.getWorkspace().save(true, null);
//		}
//		catch (final CoreException e)
//		{
//			// log exception, if required
//		}
//		return true;
//	}

}
