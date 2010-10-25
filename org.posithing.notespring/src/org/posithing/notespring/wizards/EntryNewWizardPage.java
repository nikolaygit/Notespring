package org.posithing.notespring.wizards;

import org.eclipse.jface.viewers.ISelection;

/**
 * The Entry New Wizard Page
 */
public class EntryNewWizardPage extends AbstractNewWizardPage
{
	private static final String TYPE_NAME = "Entry";
	private static final String TITLE = "Write an entry";
	private static final String DESCRIPTION = "Creates a new entry file.";
	private static final String INITIAL_FILE_NAME = "Entry-Name";
	private static final String FILE_EXTENSION = "txt";
	
	public EntryNewWizardPage(ISelection selection)
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
