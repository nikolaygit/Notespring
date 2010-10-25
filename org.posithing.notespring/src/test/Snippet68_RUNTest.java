package test;
/*
 * Display example snippet: stop a repeating timer when a button is pressed
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.posithing.notespring.swt.Clock;
import org.posithing.notespring.swt.RunnableSwitchButtonListener;
import org.posithing.notespring.swt.SwitchButtonListener;
import org.posithing.notespring.swt.Timer;


public class Snippet68_RUNTest {

  public static void main(String[] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setLayout(new RowLayout());

	String startStr = "Start";
	String pauseStr = "Pause";
	String resetStr = "Reset";
	
    final Button startButton = new Button(shell, SWT.PUSH);
    startButton.setText(startStr);
    //startButton.setImage(image);
    
    Button resetButton = new Button(shell, SWT.PUSH);
    resetButton.setText(resetStr);
    
    Label label = new Label(shell, SWT.BORDER);
    
    final int time = 1000;
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    final Timer textClock = new Timer(display, label, formatter, time);

    RunnableSwitchButtonListener switchListener1 = new RunnableSwitchButtonListener(
    		display, textClock, time, startButton, 
    		pauseStr, null, null);
    RunnableSwitchButtonListener switchListener2 = new RunnableSwitchButtonListener(
    		display, textClock, -1, startButton, 
    		startStr, null, null);
    switchListener1.setSwitchListener(switchListener2);
    switchListener2.setSwitchListener(switchListener1);
    
    startButton.addListener(SWT.Selection, switchListener1);
    
    resetButton.addListener(SWT.Selection, new Listener() {
        public void handleEvent(Event event) {
        	textClock.resetTimer();
        }
      });

    startButton.pack();
    resetButton.pack();
    RowData rowData = new RowData(resetButton.getSize());
    rowData.width = rowData.width + 10;
    label.setLayoutData(rowData);
    
    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}