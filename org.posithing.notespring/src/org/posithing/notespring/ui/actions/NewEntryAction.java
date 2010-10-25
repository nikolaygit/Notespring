package org.posithing.notespring.ui.actions;

import org.posithing.notespring.wizards.AbsractNewWizard;
import org.posithing.notespring.wizards.EntryNewWizard;

public class NewEntryAction extends NewWizardAction
{
	@Override
	protected AbsractNewWizard createNewWizard()
	{
		return new EntryNewWizard();
	}
}
