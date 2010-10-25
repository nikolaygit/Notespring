package org.posithing.notespring;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class NotespringApplication implements IApplication
{
	private static final String PROP_EXIT_CODE = "eclipse.exitcode"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext context)
	{
		Display display = PlatformUI.createDisplay();
		try
		{
			int returnCode = PlatformUI.createAndRunWorkbench(display, new NotespringWorkbenchAdvisor());

			if (returnCode != PlatformUI.RETURN_RESTART)
				return EXIT_OK;
			
			// if the exit code property has been set to the relaunch //code, then            
			// return that code now, otherwise this is a normal //restart                        
			return EXIT_RELAUNCH.equals(Integer.getInteger(PROP_EXIT_CODE)) ? 
					EXIT_RELAUNCH : EXIT_RESTART;

//			// NOT WORKING
//			if (returnCode == PlatformUI.RETURN_RESTART)
//			{
//				System.out.println("IApplication.EXIT_RESTART");
//				return IApplication.EXIT_RESTART;
//			}
//			else
//			{
//				System.out.println("IApplication.EXIT_OK");
//				return IApplication.EXIT_OK;
//			}

		}
		finally
		{
			display.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop()
	{
		System.out.println("STOPPING ...");
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();

		System.out.println("CLOSING WORKBENCH ...");
		display.syncExec(new Runnable()
		{
			public void run()
			{
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
