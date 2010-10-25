package test;

/*
 * example snippet: create a table (lazy)
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Snippet7
{

	public static void main(String[] args)
	{
		final Display display = new Display();
		final Image image = new Image(display, 16, 16);
		GC gc = new GC(image);
		gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
		gc.fillRectangle(image.getBounds());
		gc.dispose();
		final Shell shell = new Shell(display);
		shell.setText("CountDown");
		shell.setLayout(new FillLayout());

		final Label timerLabel = new Label(shell, SWT.NONE);

		Thread thread = new Thread()
		{
			public void run()
			{
				for (int i = 0; i < 20000; i++)
				{
					if (timerLabel.isDisposed())
						return;
					display.syncExec(new Runnable()
					{
						public void run()
						{
							if (timerLabel.isDisposed())
								return;
							//n = (int) ((this.targ - System.currentTimeMillis()) / (1000L));
						}
					});
				}
			}
		};
		thread.start();
		
		shell.setSize(200, 200);
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}
}
