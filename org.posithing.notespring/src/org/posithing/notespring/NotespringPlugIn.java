package org.posithing.notespring;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.posithing.notespring.swt.ImageDisposer;
import org.posithing.notespring.views.CalendarData;


/**
 * The activator class controls the plug-in life cycle
 */
public class NotespringPlugIn extends AbstractUIPlugin
{
	// The plug-in ID
	public static final String PLUGIN_ID = "org.posithing.notespring";

	private static final String BINDING_SCHEME = "org.posithing.notespring.scheme";
	
	// The shared instance
	private static NotespringPlugIn plugin;

	/**
	 * The constructor
	 */
	public NotespringPlugIn()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;

		CalendarData.synchronizeData();
		
		PlatformUI.getPreferenceStore().setDefault(
				IWorkbenchPreferenceConstants.KEY_CONFIGURATION_ID, 
				BINDING_SCHEME);

		addInitialPreferencesValues();
	}

	private void addInitialPreferencesValues()
	{
		Preferences prefs = NotespringPlugIn.getDefault().getPluginPreferences();
		prefs.setValue(NotespringConstants.P_WRAP, true);
		
		prefs.setDefault(NotespringConstants.P_INITIAL_TEXT_ENTRY, 
				NotespringConstants.INITIAL_DEFAULT_TEXT_ENTRY);
		prefs.setDefault(NotespringConstants.P_INITIAL_TEXT_DREAM, 
				NotespringConstants.INITIAL_DEFAULT_TEXT_DREAM);

		plugin.savePluginPreferences();
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static NotespringPlugIn getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Returns the image for the given relative path. Takes care of Disposing it.
	 * 
	 * @param path
	 *            the relative image path after <code>/icons/</code>
	 * @return Image
	 */
	public static Image getImage(final Shell shell, final String path)
	{
		Image image = getImageDescriptor("/icons/"+path).createImage();
		new ImageDisposer(shell, image);
		return image;
	}
}
