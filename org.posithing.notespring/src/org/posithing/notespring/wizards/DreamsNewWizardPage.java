package org.posithing.notespring.wizards;

import org.eclipse.jface.viewers.ISelection;

/**
 * The Dreams New Wizard Page
 */
public class DreamsNewWizardPage extends AbstractNewWizardPage
{
	private static final String TYPE_NAME = "Dream";
	private static final String TITLE = "Write a new dream";
	private static final String DESCRIPTION = "Creates a new dream file.";
	private static final String INITIAL_FILE_NAME = "Dream-Name";
	private static final String FILE_EXTENSION = "drm";
	
	public DreamsNewWizardPage(ISelection selection)
	{
		super(selection);
	}

	@Override
	protected String getTypeName()
	{
		return TYPE_NAME;
	}

	@Override
	protected String createTitle()
	{
		return TITLE;
	}

	@Override
	protected String createDescription()
	{
		return DESCRIPTION;
	}

	@Override
	protected String setInitialFileName()
	{
		return INITIAL_FILE_NAME;
	}

	@Override
	protected String getFileExtension()
	{
		return FILE_EXTENSION;
	}
}
