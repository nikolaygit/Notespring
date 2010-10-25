package org.posithing.notespring.swt;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class ImageDisposer implements DisposeListener
{
	final Image image;

	public ImageDisposer(Shell shell, Image image)
	{
		this.image = image;
		shell.addDisposeListener(this);
	}

	public void widgetDisposed(DisposeEvent e)
	{
		if (this.image != null && !this.image.isDisposed())
			this.image.dispose();
	}
}
