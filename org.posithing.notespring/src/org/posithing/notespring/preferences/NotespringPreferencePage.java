package org.posithing.notespring.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.posithing.notespring.NotespringPlugIn;


public class NotespringPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	public NotespringPreferencePage()
	{
		super(FieldEditorPreferencePage.GRID);

		// Set the preference store for the preference page.
		setPreferenceStore(NotespringPlugIn.getDefault().getPreferenceStore());
	}
	
	@Override
	protected void createFieldEditors()
	{
//		StringFieldEditor sfe = new StringFieldEditor(
//				NotespringConstants.INITIAL_TEXT_ENTRY,
//				"&Entry initial text",
//				getFieldEditorParent());
//		addField(sfe);
	}

	public void init(IWorkbench workbench)
	{
		// TODO Auto-generated method stub
	}
}
