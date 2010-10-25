package org.posithing.notespring.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.posithing.notespring.wizards.AbsractNewWizard;


abstract public class NewWizardAction implements IWorkbenchWindowActionDelegate
{
	private IWorkbenchWindow window;
	private ISelection selection;

	public void init(IWorkbenchWindow window)
	{
		this.window = window;
	}

	public void selectionChanged(IAction action, ISelection selection)
	{
		this.selection = selection;
	}

	public void run(IAction action)
	{
		AbsractNewWizard wizard = createNewWizard();
		wizard.init(window.getWorkbench(), (IStructuredSelection) selection);
		
		// Instantiates the wizard container with the wizard and opens it
		Shell shell = new Shell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

	abstract protected AbsractNewWizard createNewWizard();

	public void dispose()
	{
	}
}
