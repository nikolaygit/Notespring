package test;

public class CountingDownSec
{
	public static void main(String[] args) throws java.lang.Exception
	{

		String str = "2008-12-13-09-30";// date-time with the pattern
										// "yyyy-M-d-k-m"

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-M-d-k-m");
		java.util.Date date = formatter.parse(str);
		java.util.Timer timer = new java.util.Timer();
		long target = date.getTime();// target date-time in Milliseconds
		Task0 task = new Task0(target);
		timer.schedule(task, 0L, 1000L);
	}
}

class Task0 extends java.util.TimerTask
{
	long targ;

	Task0(long target)
	{// constructor
		this.targ = target;
	}

	private int n = Integer.MAX_VALUE;

	// run method..
	public void run()
	{
		System.out.println("remaining seconds:");
		n = (int) ((this.targ - System.currentTimeMillis()) / (1000L));
		System.out.println(String.valueOf(n));
		if (n <= 0)
		{
			try
			{
				System.out.println("Time is up!");
				// ..Do something useful..
				this.cancel();
				// System.exit(0);
			}
			catch (java.lang.RuntimeException ex)
			{
				throw ex;
			}
		}// end of if
	}
}
