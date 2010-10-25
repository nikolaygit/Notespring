package test;

import java.text.SimpleDateFormat;

import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.posithing.notespring.swt.RunnableSwitchButtonListener;
import org.posithing.notespring.swt.Timer;


public class RTextTest
{

	public static void main(String[] args)
	{
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());

		RichTextEditor editor = new RichTextEditor(
				shell, SWT.NONE, null);
		
		shell.pack();
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
