package org.posithing.notespring.wizards;

import org.eclipse.jface.viewers.ISelection;
import org.posithing.notespring.NotespringConstants;


/**
 * The Dreams New Wizard
 */
public class DreamsNewWizard extends AbsractNewWizard
{
	@Override
	protected String createInitialContents()
	{
		return getPluginPreferences().getString(NotespringConstants.P_INITIAL_TEXT_DREAM);
	}

	@Override
	protected AbstractNewWizardPage createAbstractNewWizardPage(ISelection selection)
	{
		return new DreamsNewWizardPage(selection);
	}
}
