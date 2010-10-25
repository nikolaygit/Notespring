package org.posithing.notespring.views;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.posithing.notespring.helpers.Colors;
import org.posithing.notespring.helpers.FontManager;
import org.posithing.notespring.helpers.FontSymbolicNames;
import org.posithing.notespring.swt.UpDownSpinner;


/**
 * SWT Calendar with Notes
 */
public class SWTCalendar implements MouseListener
{
	private Composite composite = null;

	private Date today = null;
	private int todayYear = -1;
	private int todayMonth = -1;
	private int todayDay = -1;
	private Date newDate = null; // new Date
	private String selectedDate = null; // selected date

	private long curFirstMonthDay;
	private long curLastMonthDay;
	static private TreeMap<Integer, List<IFile>> curDaysToFiles = 
		new TreeMap<Integer, List<IFile>>();
	
	private int myStartingDay = Calendar.MONDAY;
	private String dateFormat = "MMM, yyyy";

	// Colors
	private final static Color mainPlainColor = Colors.WHITE;
	private final static Color mainBackGroundColor = Colors.SOFT_BLUE;
	private final static Color todayColor = Colors.SOFT_ORANGE;
	private final static Color entryColor = Colors.SOFT_GREEN;
	
	private final static Color[] lineColors = Colors.LINE_COLORS;
	private final static int[] linePercentages = Colors.LINE_PERCENTAGES;

	private CLabel newDateLabel = null;
	private CLabel day1;
	private CLabel day2;
	private CLabel day3;
	private CLabel day4;
	private CLabel day5;
	private CLabel day6;
	private CLabel day7;
	private String[] daysLabels = new String[]{"M","T","W","T","F","S","S"};
	
	private CLabel[] days = new CLabel[42];
	CalendarDaysListener daysListener;

	public SWTCalendar(Composite parent)
	{
		composite = parent;

		daysListener = new CalendarDaysListener(composite);
		composite.addListener(SWT.Dispose, daysListener);
		composite.addListener(SWT.MouseUp, daysListener);
		Font font = FontManager.getFont(FontSymbolicNames.DEFAULT);
		composite.setBackground(mainPlainColor);
		
		createUpperLine(mainBackGroundColor);
		createDays(mainPlainColor, font);
		
		setMyStartingDay(Calendar.MONDAY);

		Calendar now = updateToday();
		setDayForDisplay(now);
	}

	private Calendar updateToday()
	{
		Calendar now = Calendar.getInstance();
		today = new Date(now.getTimeInMillis());
		now.setTime(today);
		todayYear = now.get(Calendar.YEAR);
		todayMonth = now.get(Calendar.MONTH) + 1; //!
		todayDay = now.get(Calendar.DATE);
		newDate = today;
		return now;
	}
	
	private void createUpperLine(Color bgColor)
	{
		// set Upper Layout
		Composite upper = new Composite(composite, SWT. NONE);
		upper.addListener(SWT.MouseUp, daysListener);
		GridLayout upperGridLayout = new GridLayout();
		upperGridLayout.numColumns = 3;
		upperGridLayout.marginWidth = 2;
		upperGridLayout.marginHeight = 2;
		upperGridLayout.marginBottom = 0;
		upper.setLayout(upperGridLayout);
		upper.setBackground(bgColor);

		// MONTH SPINNER
	    UpDownSpinner monthSpinner = new UpDownSpinner(upper, SWT.NONE);
	    monthSpinner.addUpListener(SWT.Selection, new Listener()
	    {
			public void handleEvent(Event event){nextMonth();}
	    });
	    monthSpinner.addDownListener(SWT.Selection, new Listener()
	    {
			public void handleEvent(Event event){previousMonth();}
	    });

		// TEXT
		GridData upperMiddleGridData = new GridData(GridData.FILL_HORIZONTAL);
		upperMiddleGridData.widthHint = 70;
		newDateLabel = new CLabel(upper, SWT.CENTER);
		newDateLabel.addListener(SWT.MouseUp, daysListener);
		newDateLabel.setBackground(bgColor);
		newDateLabel.setLayoutData(upperMiddleGridData);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		newDateLabel.setText(formatter.format(new Date()));

		// YEAR SPINNER
	    UpDownSpinner yearSpinner = new UpDownSpinner(upper, SWT.NONE);
	    yearSpinner.addUpListener(SWT.Selection, new Listener()
	    {
			public void handleEvent(Event event){nextYear();}
	    });
	    yearSpinner.addDownListener(SWT.Selection, new Listener()
	    {
			public void handleEvent(Event event){previousYear();}
	    });
	}

	private void createDays(Color white, Font font)
	{
		// MONTH DESCRIPTION
		Composite down = new Composite(composite, SWT. NONE);
		down.addListener(SWT.MouseUp, daysListener);
		GridLayout downGridLayout = new GridLayout();
		downGridLayout.numColumns = 7;
		downGridLayout.horizontalSpacing = 2;
		downGridLayout.verticalSpacing = 1;
		downGridLayout.marginTop = 0;
		downGridLayout.marginWidth = 0;
		downGridLayout.marginHeight = 0;
		down.setLayout(downGridLayout);
		down.setBackground(white);
		
		int labelStyle = SWT.FLAT | SWT.CENTER;
		
		GridData downGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		downGridData.verticalAlignment = SWT.TOP;
		downGridData.heightHint = 15;
		//downGridData.widthHint = 15;
		
		day1 = new CLabel(down, labelStyle);
		day1.setFont(font);
		day1.setText(daysLabels[0]);
		day1.setBackground(mainPlainColor);
		day1.setLayoutData(downGridData);
		
		day2 = new CLabel(down, labelStyle);
		day2.setFont(font);
		day2.setText(daysLabels[1]);
		day2.setBackground(mainPlainColor);
		day2.setLayoutData(downGridData);
		
		day3 = new CLabel(down, labelStyle);
		day3.setFont(font);
		day3.setText(daysLabels[2]);
		day3.setBackground(mainPlainColor);
		day3.setLayoutData(downGridData);
		
		day4 = new CLabel(down, labelStyle);
		day4.setFont(font);
		day4.setText(daysLabels[3]);
		day4.setBackground(mainPlainColor);
		day4.setLayoutData(downGridData);
		
		day5 = new CLabel(down, labelStyle);
		day5.setFont(font);
		day5.setText(daysLabels[4]);
		day5.setBackground(mainPlainColor);
		day5.setLayoutData(downGridData);
		
		day6 = new CLabel(down, labelStyle);
		day6.setFont(font);
		day6.setText(daysLabels[5]);
		day6.setBackground(mainPlainColor);
		day6.setLayoutData(downGridData);
		
		day7 = new CLabel(down, labelStyle);
		day7.setFont(font);
		day7.setText(daysLabels[6]);
		day7.setBackground(mainPlainColor);
		day7.setLayoutData(downGridData);
		
		// add before Line
		GridData lineData = new GridData(GridData.FILL_HORIZONTAL);
		lineData.horizontalSpan = 7;
		lineData.heightHint = 2;
		CLabel beforeLine = new CLabel(down, labelStyle);
		beforeLine.setBackground(lineColors, linePercentages);
		beforeLine.setLayoutData(lineData);
		
		for (int i = 0; i < 42; i++)
		{
			GridData othersGridData = new GridData(
					GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
			othersGridData.heightHint = 15;
			othersGridData.widthHint = 15;
			othersGridData.verticalAlignment = SWT.CENTER;
			othersGridData.horizontalAlignment = SWT.CENTER;
			
			days[i] = new CLabel(down, labelStyle);
			days[i].setFont(font);
			days[i].setBackground(mainPlainColor);
			days[i].setLayoutData(othersGridData);
			days[i].addMouseListener(this);
		}
		
		// add after Line
		CLabel afterLine = new CLabel(down, labelStyle);
		afterLine.setBackground(lineColors, linePercentages);
		afterLine.setLayoutData(lineData);
	}

	/**
	 * @param year Year (e.g. 2008)
	 * @param month month (1 .. 12)
	 * @return the last day of the given month in the given year
	 */
	private int getLastDayOfMonth(int year, int month)
	{
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
		{
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			return 30;
		}
		if (month == 2)
		{
			if (isLeapYear(year))
			{
				return 29;
			}
			else
			{
				return 28;
			}
		}
		return 0;
	}

	/**
	 * @param year Year (e.g. 2008)
	 * @return true, if the year is leap; false otherwise
	 */
	public boolean isLeapYear(int year)
	{
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * Move the calendar with year/month before/after
	 * e.g. moveTo(Calendar.MONTH, -1);
	 * 
	 * @param type Calendar.MONTH, Calendar.YEAR
	 * @param value integer
	 */
	private void moveTo(int type, int value)
	{
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(newDate);
		newCalendar.add(type, value);
		newDate = new Date(newCalendar.getTimeInMillis());
		
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		newDateLabel.setText(formatter.format(newDate));
		setDayForDisplay(newCalendar);
	}

    private void setDayForDisplay(Calendar newCalendar)
	{
		newCalendar.add(Calendar.DAY_OF_MONTH, -(newCalendar.get(Calendar.DATE) - 1)); //
		
		// find starting index
		int dayOfWeek = newCalendar.get(Calendar.DAY_OF_WEEK);
		int dayOfWeekIndex = dayOfWeek - 1;
		int myDayIndex = myStartingDay - 1;
		int negposNumber = 7 - myDayIndex - myDayIndex;
		int sth = negposNumber>=0? negposNumber : 7 + negposNumber;
		int startIndex = (dayOfWeekIndex + myDayIndex + sth)%7;
		
		int year = newCalendar.get(Calendar.YEAR);
		int month = newCalendar.get(Calendar.MONTH) + 1;
		int lastDay = getLastDayOfMonth(year, month);
		int endIndex = startIndex + lastDay - 1;
		int startday = 1;

		newCalendar.set(year, month - 1, 1, 0, 0, 0);
		curFirstMonthDay = newCalendar.getTime().getTime();
		
		newCalendar.set(year, month, 
				newCalendar.getGreatestMinimum(Calendar.MONTH), 23, 59, 59);
		curLastMonthDay = newCalendar.getTime().getTime();
		
		// get all the Files for this Month
		removeFiles();
		SortedMap<Long, List<IFile>> files = 
			CalendarData.getFilesFromTo(curFirstMonthDay, curLastMonthDay);
		Set<Long> fileDates = files.keySet();
		Iterator<Long> datesIter = fileDates.iterator();
		while (datesIter.hasNext())
		{
			Long curFilesDate = (Long) datesIter.next();
			List<IFile> curDateFiles = files.get(curFilesDate);
			Date date = new Date(curFilesDate);
			newCalendar.setTime(date);
			int filesDay = newCalendar.get(Calendar.DAY_OF_MONTH);
			
			addFiles(filesDay, curDateFiles);
		}
		
		boolean todaysMonth = isTodaysMonth(year, month);
		
		for (int i = 0; i < 42; i++)
		{
			if (i >= startIndex && i <= endIndex)
			{
				// add numbers to the labels
				days[i].setText("" + startday);

				// setup Background Colors and Listeners
				if(curDaysToFiles.containsKey(startday))
				{
					String tooltip = "";
					List<IFile> curDayFiles = curDaysToFiles.get(startday);
					for (int j = 0; j < curDayFiles.size(); j++)
					{
						IFile file = curDayFiles.get(j);
						tooltip += file.getName()+"\n";
					}
					
					days[i].setBackground(entryColor);
					days[i].setToolTipText(tooltip);

					days[i].setData(curDayFiles);
				}
				else
				{
					clearCLabel(i);
				}
				if(todaysMonth && startday == todayDay)
				{
					days[i].setBackground(todayColor);
				}
				
				startday++;
			}
			else
			{
				days[i].setText(null);
				clearCLabel(i);
			}
			days[i].addListener(SWT.MouseUp, daysListener);
		}
	}

	private void clearCLabel(int i)
	{
		days[i].setBackground(mainPlainColor);
		days[i].setToolTipText("");
		days[i].setData(null);
		
		days[i].removeListener(SWT.MouseUp, daysListener);
	}

    private void removeFiles()
	{
    	curDaysToFiles.clear();
	}

	private static void addFiles(int filesDay, List<IFile> curDateFiles)
	{
		if(curDaysToFiles.containsKey(filesDay))
		{
			List<IFile> fileList = curDaysToFiles.get(filesDay);
			for (int i = 0; i < curDateFiles.size(); i++)
			{
				if(!fileList.contains(curDateFiles.get(i)))
					fileList.add(curDateFiles.get(i));
			}
		}
		else
		{
	    	curDaysToFiles.put(filesDay, curDateFiles);
		}
	}
    
    public static List<IFile> getFilesFor(int filesDay)
	{
    	return curDaysToFiles.get(filesDay);
	}

	/**
     * Checks whether the given month is today's month
     * 
     * @param year Year of the month, Calendar.get(Calendar.YEAR);
     * @param month Month, Calendar.get(Calendar.MONTH);
     * @return <code>true</code> if the given month is today's month
     */
	private boolean isTodaysMonth(int year, int month)
	{
		return year == todayYear && month == todayMonth;
	}

	public void previousYear()
	{
		moveTo(Calendar.YEAR, -1);
	}

	public void nextYear()
	{
		moveTo(Calendar.YEAR, 1);
	}

	public void nextMonth()
	{
		moveTo(Calendar.MONTH, 1);
	}

	public void previousMonth()
	{
		moveTo(Calendar.MONTH, -1);
	}

	public void mouseDown(MouseEvent e)
	{
	}
	
	public void mouseUp(MouseEvent e)
	{
	}

	public void mouseDoubleClick(MouseEvent e)
	{
		CLabel day = (CLabel) e.getSource();
		if (!day.getText().equals(""))
		{
			selectedDate = newDateLabel.getText() + "-" + day.getText();
			System.out.println("selectedDate = "+selectedDate);
			//this.shell.close();
		}
	}
	
	/**
	 * Sets the starting Day of the Calendar
	 * @param newStartingDay Calendar.MONDAY , TUESDAY, WEDNESDAY, THURSDAY, 
	 * FRIDAY, SATURDAY, SUNDAY
	 */
	public void setMyStartingDay(int newStartingDay)
	{
		if(newStartingDay >= 1 && newStartingDay <= 7)
		{
			// the shift daysLabels array
			if(newStartingDay == myStartingDay) return;
			else if (newStartingDay > myStartingDay)
			{
				int diff = newStartingDay - myStartingDay;
				circularRightShift(daysLabels, daysLabels.length-diff);
			}
			else
			{
				int diff = myStartingDay - newStartingDay;
				circularRightShift(daysLabels, diff);
			}
			
			// update the labels
			day1.setText(daysLabels[0]);
			day2.setText(daysLabels[1]);
			day3.setText(daysLabels[2]);
			day4.setText(daysLabels[3]);
			day5.setText(daysLabels[4]);
			day6.setText(daysLabels[5]);
			day7.setText(daysLabels[6]);
			
			// save the new starting day
			myStartingDay = newStartingDay;
		}
	}
	
	public void setFocus()
	{
		composite.setFocus();
	}
	
	public Composite getParent()
	{
		return composite;
	}

	/**
	 * Circularly right shifts <code>positions</code>-times the given array
	 * @param array
	 * @param positions
	 */
	private void circularRightShift(Object[] array, int positions)
	{
		int len = array.length;
		Object[] temp = new Object[len];
		
		System.out.println("----- "+(0 + 5)%len);
		
		for (int i = 0; i < len; i++)
		{
			int j = (i + positions)%len;
			temp[j] = array[i];
		}

		for (int i = 0; i < len; i++)
			array[i] = temp[i];
	}

	public void refreshData()
	{
		moveTo(Calendar.MONTH, 0); // just freshes
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Display dis = Display.getDefault();
		Shell shell = new Shell(SWT.TITLE);
		shell.setText("Calendar 0.55v");
		shell.setSize(280, 280);
		
		// set normal Layout
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.wrap = false;
		shell.setLayout(rowLayout);
		
		new SWTCalendar(shell);

		shell.pack();
		shell.open();
		while (!shell.isDisposed ())
		{
			if (!dis.readAndDispatch ())
				dis.sleep ();
		}
		dis.dispose ();
	}

}
