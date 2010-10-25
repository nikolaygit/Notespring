package org.posithing.notespring;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.ide.IIDEActionConstants;
import org.eclipse.ui.internal.provisional.application.IActionBarConfigurer2;

/**
 * Eclipse counterpart:
 * {@link org.eclipse.ui.internal.ide.WorkbenchActionBuilder}
 */
@SuppressWarnings("restriction")
public class NotespringActionBuilder extends ActionBarAdvisor
{
	private IWorkbenchAction exitAction;

	private IWorkbenchAction prefAction;

	private IContributionItem newWizardMenu;

	private IWorkbenchAction saveAction;

	private IWorkbenchAction saveAllAction;

	private IWorkbenchAction closeAction;

	private IWorkbenchAction closeAllAction;

	private IWorkbenchAction saveAsAction;

	private IWorkbenchAction revertAction;

	private IWorkbenchAction printAction;

	private IWorkbenchAction propsAction;

	private IContributionItem showViewAction;

	private IWorkbenchAction importAction;

	private IWorkbenchAction exportAction;

	private IWorkbenchAction undoAction;

	private IWorkbenchAction redoAction;

	private IWorkbenchAction cutAction;

	private IWorkbenchAction copyAction;

	private IWorkbenchAction pasteAction;

	private IWorkbenchAction deleteAction;

	private IWorkbenchAction selectAllAction;

	private IWorkbenchAction findAction;

	private IWorkbenchAction aboutAction;

	private IWorkbenchAction nextAction;

	private IWorkbenchAction prevAction;

	private IWorkbenchAction backwardHistoryAction;

	private IWorkbenchAction forwardHistoryAction;

	private IWorkbenchAction switchWorkspaceAction;

	private IWorkbenchAction customizePerspAction;

	private IWorkbenchAction resetPerspAction;

	private IWorkbenchAction newWizardToolBarAction;

	private IWorkbenchAction addTaskAction;

	private IWorkbenchAction addBookmarkAction;

	private IWorkbenchAction toggleToolBarAction;

	private IWorkbenchAction refreshAction;

	private IWorkbenchAction renameAction;

	private IWorkbenchAction moveAction;

	private IWorkbenchAction goIntoAction;

	private IContributionItem showInAction;

	private IWorkbenchAction upAction;

	private IWorkbenchAction backAction;

	private IWorkbenchAction forwardAction;

	private IWorkbenchAction showPartPaneMenuAction;

	private IWorkbenchAction showViewMenuAction;

	private IWorkbenchAction showQuickAccessAction;

	private IWorkbenchAction maxAction;

	private IWorkbenchAction minAction;

	private IWorkbenchAction nextEditorAction;

	private IWorkbenchAction prevEditorAction;

	private IWorkbenchAction nextPartAction;

	private IWorkbenchAction prevPartAction;

	private IWorkbenchAction quickSwitchEditorsAction;

	private IWorkbenchAction activateEditorAction;

	private IWorkbenchAction showEditorAction;

	private IWorkbenchAction showOpenEditorsAction;

	private IContributionItem pinEditorContributionItem;

	public NotespringActionBuilder(final IActionBarConfigurer configurer)
	{
		super(configurer);
	}
	
	@Override
	protected void makeActions(final IWorkbenchWindow window)
	{
		newWizardMenu = ContributionItemFactory.NEW_WIZARD_SHORTLIST.create(window);
		newWizardToolBarAction = ActionFactory.NEW_WIZARD_DROP_DOWN.create(window);
		register(newWizardToolBarAction);
		closeAction = ActionFactory.CLOSE.create(window);
		register(closeAction);
		closeAllAction = ActionFactory.CLOSE_ALL.create(window);
		register(closeAllAction);
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
		saveAsAction = ActionFactory.SAVE_AS.create(window);
		register(saveAsAction);
		saveAllAction = ActionFactory.SAVE_ALL.create(window);
		register(saveAllAction);
		revertAction = ActionFactory.REVERT.create(window);
		register(revertAction);
		printAction = ActionFactory.PRINT.create(window);
		register(printAction);
		importAction = ActionFactory.IMPORT.create(window);
		switchWorkspaceAction = IDEActionFactory.OPEN_WORKSPACE.create(window);
		register(switchWorkspaceAction);
		register(importAction);
		exportAction = ActionFactory.EXPORT.create(window);
		register(exportAction);
		propsAction = ActionFactory.PROPERTIES.create(window);
		register(propsAction);
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);

		undoAction = ActionFactory.UNDO.create(window);
		register(undoAction);
		redoAction = ActionFactory.REDO.create(window);
		register(redoAction);
		cutAction = ActionFactory.CUT.create(window);
		register(cutAction);
		copyAction = ActionFactory.COPY.create(window);
		register(copyAction);
		pasteAction = ActionFactory.PASTE.create(window);
		register(pasteAction);
		deleteAction = ActionFactory.DELETE.create(window);
		register(deleteAction);
		selectAllAction = ActionFactory.SELECT_ALL.create(window);
		register(selectAllAction);
		addBookmarkAction = IDEActionFactory.BOOKMARK.create(window);
		register(addBookmarkAction);
		addTaskAction = IDEActionFactory.ADD_TASK.create(window);
		register(addTaskAction);

		refreshAction = ActionFactory.REFRESH.create(window);
		register(refreshAction);
		renameAction = ActionFactory.RENAME.create(window);
		register(renameAction);
		moveAction = ActionFactory.MOVE.create(window);
		register(moveAction);

		pinEditorContributionItem = ContributionItemFactory.PIN_EDITOR.create(window);

		findAction = ActionFactory.FIND.create(window);
		register(findAction);

		toggleToolBarAction = ActionFactory.TOGGLE_COOLBAR.create(window);
		register(toggleToolBarAction);
		showEditorAction = ActionFactory.SHOW_EDITOR.create(window);
		register(showEditorAction);

		goIntoAction = ActionFactory.GO_INTO.create(window);
		register(goIntoAction);
		backAction = ActionFactory.BACK.create(window);
		register(backAction);
		forwardAction = ActionFactory.FORWARD.create(window);
		register(forwardAction);
		upAction = ActionFactory.UP.create(window);
		register(upAction);
		showInAction = ContributionItemFactory.VIEWS_SHOW_IN.create(window);
		nextAction = ActionFactory.NEXT.create(window);
		register(nextAction);
		prevAction = ActionFactory.PREVIOUS.create(window);
		register(prevAction);
		backwardHistoryAction = ActionFactory.BACKWARD_HISTORY.create(window);
		register(backwardHistoryAction);
		forwardHistoryAction = ActionFactory.FORWARD_HISTORY.create(window);
		register(forwardHistoryAction);

		showViewAction = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
		customizePerspAction = ActionFactory.EDIT_ACTION_SETS.create(window);
		register(customizePerspAction);
		resetPerspAction = ActionFactory.RESET_PERSPECTIVE.create(window);
		register(resetPerspAction);
		showPartPaneMenuAction = ActionFactory.SHOW_PART_PANE_MENU.create(window);
		register(showPartPaneMenuAction);
		showViewMenuAction = ActionFactory.SHOW_VIEW_MENU.create(window);
		register(showViewMenuAction);
		showQuickAccessAction = ActionFactory.SHOW_QUICK_ACCESS.create(window);
		register(showQuickAccessAction);
		maxAction = ActionFactory.MAXIMIZE.create(window);
		register(maxAction);
		minAction = ActionFactory.MINIMIZE.create(window);
		register(minAction);
		activateEditorAction = ActionFactory.ACTIVATE_EDITOR.create(window);
		register(activateEditorAction);
		nextEditorAction = ActionFactory.NEXT_EDITOR.create(window);
		register(nextEditorAction);
		prevEditorAction = ActionFactory.PREVIOUS_EDITOR.create(window);
		register(prevEditorAction);
		quickSwitchEditorsAction = ActionFactory.SHOW_WORKBOOK_EDITORS.create(window);
		register(quickSwitchEditorsAction);
		showOpenEditorsAction = ActionFactory.SHOW_OPEN_EDITORS.create(window);
		register(showOpenEditorsAction);
		nextPartAction = ActionFactory.NEXT_PART.create(window);
		register(nextPartAction);
		prevPartAction = ActionFactory.PREVIOUS_PART.create(window);
		register(prevPartAction);
		prefAction = ActionFactory.PREFERENCES.create(window);
		register(prefAction);

		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
	}

	@Override
	protected void fillCoolBar(final ICoolBarManager coolBar)
	{
		final IActionBarConfigurer2 actionBarConfigurer = 
			(IActionBarConfigurer2) getActionBarConfigurer();
		{
			// TODO create context menu
		}

		coolBar.add(new GroupMarker(IIDEActionConstants.GROUP_FILE));
		{// File Group
			final IToolBarManager fileToolBar = actionBarConfigurer.createToolBarManager();
			fileToolBar.add(new Separator(IWorkbenchActionConstants.NEW_GROUP));
			fileToolBar.add(newWizardToolBarAction);
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.SAVE_GROUP));
			fileToolBar.add(saveAction);
			fileToolBar.add(saveAllAction);
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
			fileToolBar.add(new Separator());
			fileToolBar.add(printAction);
			fileToolBar.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));
			fileToolBar.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

			// Add to the cool bar manager
			coolBar.add(actionBarConfigurer.createToolBarContributionItem(fileToolBar,
					IWorkbenchActionConstants.TOOLBAR_FILE));
		}

		{// Edit group
			final IToolBarManager editToolBar = actionBarConfigurer.createToolBarManager();
			editToolBar.add(undoAction);
			editToolBar.add(redoAction);
			editToolBar.add(new Separator());
			editToolBar.add(cutAction);
			editToolBar.add(copyAction);
			editToolBar.add(pasteAction);
			editToolBar.add(deleteAction);
			coolBar.add(actionBarConfigurer
						.createToolBarContributionItem(editToolBar, "org.eclipse.ui.workbench.edit"));
		}

		coolBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		coolBar.add(new GroupMarker(IIDEActionConstants.GROUP_NAV));
		{ // Navigate group
			final IToolBarManager navToolBar = actionBarConfigurer.createToolBarManager();
			navToolBar.add(new Separator(IWorkbenchActionConstants.HISTORY_GROUP));
			navToolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_APP));
			navToolBar.add(backwardHistoryAction);
			navToolBar.add(forwardHistoryAction);
			navToolBar.add(new Separator(IWorkbenchActionConstants.PIN_GROUP));
			navToolBar.add(pinEditorContributionItem);

			// Add to the cool bar manager
			coolBar.add(actionBarConfigurer.createToolBarContributionItem(navToolBar,
					IWorkbenchActionConstants.TOOLBAR_NAVIGATE));
		}

		coolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_EDITOR));

		coolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_HELP));

		{ // Help group
			final IToolBarManager helpToolBar = actionBarConfigurer.createToolBarManager();
			helpToolBar.add(new Separator(IWorkbenchActionConstants.GROUP_HELP));
			// helpToolBar.add(searchComboItem);
			// Add the group for applications to contribute
			helpToolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_APP));
			// Add to the cool bar manager
			coolBar.add(actionBarConfigurer.createToolBarContributionItem(helpToolBar,
					IWorkbenchActionConstants.TOOLBAR_HELP));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void fillMenuBar(final IMenuManager menuBar)
	{
		final MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		final MenuManager editMenu = new MenuManager("&Edit", IWorkbenchActionConstants.M_EDIT);
		final MenuManager viewMenu = new MenuManager("&View", IWorkbenchActionConstants.M_VIEW);
		final MenuManager navMenu = new MenuManager("&Navigate", IWorkbenchActionConstants.M_NAVIGATE);
		final MenuManager winMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
		final MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(navMenu);
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(winMenu);
		menuBar.add(helpMenu);

		// ========== File Menu:
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));
		final MenuManager newMenu = new MenuManager("&New", ActionFactory.NEW.getId());
		fileMenu.add(newMenu);
		newMenu.add(newWizardMenu);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
		fileMenu.add(new Separator());
		fileMenu.add(closeAction);
		fileMenu.add(closeAllAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.CLOSE_EXT));
		fileMenu.add(new Separator());
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(saveAllAction);
		fileMenu.add(revertAction);
		fileMenu.add(new Separator());
		fileMenu.add(moveAction);
		fileMenu.add(renameAction);
		fileMenu.add(refreshAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));
		fileMenu.add(new Separator());
		fileMenu.add(printAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));
		fileMenu.add(new Separator());
		fileMenu.add(switchWorkspaceAction);
		fileMenu.add(new Separator());
		fileMenu.add(importAction);
		fileMenu.add(exportAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.IMPORT_EXT));
		fileMenu.add(new Separator());
		fileMenu.add(propsAction);
		fileMenu.add(new Separator());
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		fileMenu.add(new Separator());
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MRU));
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
		fileMenu.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));

		{ // Edit Menu: done
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));
			editMenu.add(undoAction);
			editMenu.add(redoAction);
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
			editMenu.add(new Separator());
			editMenu.add(cutAction);
			editMenu.add(copyAction);
			editMenu.add(pasteAction);
			editMenu.add(deleteAction);
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));
			editMenu.add(new Separator());
			editMenu.add(selectAllAction);
			editMenu.add(new Separator());
			editMenu.add(findAction);
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));
			editMenu.add(new Separator());
			editMenu.add(addBookmarkAction);
			editMenu.add(addTaskAction);
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));
			editMenu.add(new Separator());
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			editMenu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
		}

		{ // View Menu: done for now, needs new code for usual stuff such as
			// toggling the status bar.
			viewMenu.add(toggleToolBarAction);
			viewMenu.add(showEditorAction);
			viewMenu.add(new GroupMarker(IWorkbenchActionConstants.VIEW_EXT));
		}

		{ // Navigate Menu: done for now, but some addition locations should be
			// rechecked
			navMenu.add(new GroupMarker(IWorkbenchActionConstants.NAV_START));
			navMenu.add(goIntoAction);
			{
				final MenuManager goToMenu = new MenuManager("&Go To", IWorkbenchActionConstants.GO_TO);
				navMenu.add(goToMenu);
				goToMenu.add(backAction);
				goToMenu.add(forwardAction);
				goToMenu.add(upAction);
			}
			navMenu.add(new Separator());
			navMenu.add(new GroupMarker(IWorkbenchActionConstants.OPEN_EXT));
			navMenu.add(new Separator());
			{
				final MenuManager showInMenu = new MenuManager("Sho&w in");
				navMenu.add(showInMenu);
				showInMenu.add(showInAction);
			}
			navMenu.add(new GroupMarker(IWorkbenchActionConstants.SHOW_EXT));
			navMenu.add(new Separator());
			navMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			navMenu.add(new Separator());
			navMenu.add(nextAction);
			navMenu.add(prevAction);
			navMenu.add(new Separator());
			navMenu.add(backwardHistoryAction);
			navMenu.add(forwardHistoryAction);
			navMenu.add(new GroupMarker(IWorkbenchActionConstants.NAV_END));
		}

		{ // Window Menu: done for now, may need to change if perspective
			// support is ever added
			{
				final MenuManager showViewMenu = new MenuManager("Show &View");
				winMenu.add(showViewMenu);
				showViewMenu.add(showViewAction);
			}
			winMenu.add(new Separator());
			winMenu.add(customizePerspAction);
			winMenu.add(resetPerspAction);
			winMenu.add(new Separator());
			winMenu.add(new GroupMarker(IWorkbenchActionConstants.WINDOW_EXT));
			winMenu.add(new Separator());
			{
				final MenuManager menu = new MenuManager("Navigation");
				winMenu.add(menu);
				menu.add(showPartPaneMenuAction);
				menu.add(showViewMenuAction);
				menu.add(showQuickAccessAction);
				menu.add(new Separator());
				menu.add(maxAction);
				menu.add(minAction);
				menu.add(new Separator());
				menu.add(activateEditorAction);
				menu.add(nextEditorAction);
				menu.add(prevEditorAction);
				menu.add(showOpenEditorsAction);
				menu.add(quickSwitchEditorsAction);
				menu.add(new Separator());
				menu.add(nextPartAction);
				menu.add(prevPartAction);
			}
			winMenu.add(new Separator());
			winMenu.add(prefAction);
		}

		{ // Help Menu: done for now, needs work in the future if help plug-ins
			// are added
			helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
			helpMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			helpMenu.add(new Separator());
			helpMenu.add(aboutAction);
			helpMenu.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		}
	}

}
