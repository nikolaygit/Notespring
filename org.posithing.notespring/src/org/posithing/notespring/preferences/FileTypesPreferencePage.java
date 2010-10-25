package org.posithing.notespring.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.posithing.notespring.NotespringConstants;
import org.posithing.notespring.NotespringPlugIn;


public class FileTypesPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	public FileTypesPreferencePage()
	{
		super(FieldEditorPreferencePage.GRID);

		// Set the preference store for the preference page.
		setPreferenceStore(NotespringPlugIn.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors()
	{
		TextFieldEditor textFEditor = new TextFieldEditor(
				NotespringConstants.P_INITIAL_TEXT_ENTRY,
				"&Entry initial text",
				TextFieldEditor.UNLIMITED,
				TextFieldEditor.VALIDATE_ON_FOCUS_LOST,
				getFieldEditorParent(),
				TextFieldEditor.STYLE_MULTILINE);
		addField(textFEditor);
		
		textFEditor = new TextFieldEditor(
				NotespringConstants.P_INITIAL_TEXT_DREAM,
				"&Dream initial text",
				TextFieldEditor.UNLIMITED,
				TextFieldEditor.VALIDATE_ON_FOCUS_LOST,
				getFieldEditorParent(),
				TextFieldEditor.STYLE_MULTILINE);
		addField(textFEditor);
	}

	public void init(IWorkbench workbench)
	{
	}
}
