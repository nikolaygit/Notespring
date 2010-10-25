package org.posithing.notespring.views;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

public class CalendarData
{
	static private TreeMap<Long, List<IFile>> map = 
		new TreeMap<Long, List<IFile>>();
	
	public static void add(Long lastModified, IFile file)
	{
		if(map.containsKey(lastModified))
		{
			List<IFile> fileList = map.get(lastModified);
			if(!fileList.contains(file))
				fileList.add(file);
		}
		else
		{
			List<IFile> fileList = new ArrayList<IFile>();
			fileList.add(file);
			map.put(lastModified, fileList);
		}
	}

	public static Map<Long, List<IFile>> getAllFiles()
	{
		return map;
	}

	public static List<IFile> getFileList(Long date)
	{
		return map.get(date);
	}

	/**
	 * Removes all files on this date from the CalendarData
	 * 
	 * @param date java.util.Date
	 */
	public static void remove(Long date)
	{
		map.remove(date);
	}

	/**
	 * Removes the file using its lastModified date from the CalendarData
	 * 
	 * @param file IFile
	 */
	public static void remove(IFile file)
	{
		long lastModified = file.getLocation().toFile().lastModified();
		Date fileDate = new Date(lastModified);
		List<IFile> fileList = map.get(fileDate);
		fileList.remove(file);
	}
	
	public static void synchronizeData()
	{
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		try
		{
			IResource[] members = root.members();
			for (int i = 0; i < members.length; i++)
			{
				IResource project = members[i];
				
				if (project.isAccessible())
				{
					project.accept(new IResourceVisitor()
					{
						public boolean visit(IResource child)
						{
							if(child.getType() == IResource.FILE)
							{
								if(!child.getFileExtension().endsWith("project"))
								{
									IFile file = (IFile) child;
									long lastModified = child.getLocation().toFile().lastModified();
									add(lastModified, file);
								}
							}
							return true;
						}
					});
				}
			}
			
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param from the number of milliseconds since January 1, 1970, 00:00:00 GMT
	 * @param to the number of milliseconds since January 1, 1970, 00:00:00 GMT
	 * @return all the files in the given range.
	 */
	public static SortedMap<Long, List<IFile>> getFilesFromTo(long from, long to)
	{
		return map.subMap(from, to);
	}

	/**
	 * @param before the number of milliseconds since January 1, 1970, 00:00:00 GMT
	 * @return all the files before this date.
	 */
	public static SortedMap<Long, List<IFile>> getFilesBefore(long before)
	{
		return map.headMap(before);
	}

	/**
	 * @param after the number of milliseconds since January 1, 1970, 00:00:00 GMT 
	 * @return all the files after this date.
	 */
	public static SortedMap<Long, List<IFile>> getFilesAfter(long after)
	{
		return map.tailMap(after);
	}
	
	public static void main(IFile[] args)
	{
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		Long longToday = new Long(today.getTime());
		
		cal.add(Calendar.MONTH, -3);
		cal.add(Calendar.DATE, -3);
		Date anotherDay = cal.getTime();
		Long longAnotherDay = new Long(anotherDay.getTime());

		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, -5);
		cal.add(Calendar.DATE, +11);
		Date thirdDay = cal.getTime();
		Long longThirdDay = new Long(thirdDay.getTime());
		
//		add(longToday, "file-1");
//		add(longToday, "file-2");
//		
//		add(longAnotherDay, "file-3");
//		add(longAnotherDay, "file-4");
//		add(longAnotherDay, "file-5");
//		
//		add(longThirdDay, "file-6");
//		add(longThirdDay, "file-7");

		System.out.println(map.subMap(longThirdDay, longThirdDay+1));
	}
}

