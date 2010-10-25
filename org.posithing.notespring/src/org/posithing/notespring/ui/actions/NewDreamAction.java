package org.posithing.notespring.ui.actions;

import org.posithing.notespring.wizards.AbsractNewWizard;
import org.posithing.notespring.wizards.DreamsNewWizard;

public class NewDreamAction extends NewWizardAction
{
	@Override
	protected AbsractNewWizard createNewWizard()
	{
		return new DreamsNewWizard();
	}
}
