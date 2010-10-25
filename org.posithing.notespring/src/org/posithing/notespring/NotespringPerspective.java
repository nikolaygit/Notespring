package org.posithing.notespring;

import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.posithing.notespring.views.CalendarView;
import org.posithing.notespring.views.ClockView;
import org.posithing.notespring.views.TimerView;


/**
 * The Notespring Perspective
 */
public class NotespringPerspective implements IPerspectiveFactory
{
	public static final String ID = "org.posithing.notespring.perspective";
	
	public void createInitialLayout(final IPageLayout layout)
	{
		defineActions(layout);
		defineLayout(layout);
	}

	public void defineActions(IPageLayout layout)
	{
		// Add "new wizards"
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$
		
		// Add "show views"
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
	}

	public void defineLayout(IPageLayout layout)
	{
		final String editorArea = layout.getEditorArea();
		
		// LEFT
		IFolderLayout lefttop = layout.createFolder("lefttop", IPageLayout.LEFT, (float) 0.22, editorArea);
		//folder.addPlaceholder(ProjectExplorer.VIEW_ID);
		lefttop.addView(CalendarView.VIEW_ID);
		
		IFolderLayout leftmiddle = layout.createFolder("leftMiddle", IPageLayout.BOTTOM, (float) 0.20, "lefttop");
		leftmiddle.addView(ProjectExplorer.VIEW_ID);
		
		//layout.addActionSet("org.posithing.notespring.ui.actionSet.create");
		
		// BOTTOM
		IPlaceholderFolderLayout bottom = 
			layout.createPlaceholderFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea);
		bottom.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		bottom.addPlaceholder(IPageLayout.ID_TASK_LIST);
		bottom.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		bottom.addPlaceholder(NewSearchUI.SEARCH_VIEW_ID);
		
		// RIGHT
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.80f, editorArea);
		right.addView(ClockView.VIEW_ID);
		right.addView(TimerView.VIEW_ID);
	}
}
