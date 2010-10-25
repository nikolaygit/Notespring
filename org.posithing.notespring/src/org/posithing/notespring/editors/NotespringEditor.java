package org.posithing.notespring.editors;

import java.util.Date;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;
import org.posithing.notespring.NotespringConstants;
import org.posithing.notespring.NotespringPlugIn;


public class NotespringEditor extends TextEditor
{
	public NotespringEditor()
	{
		super();
	}

	@Override
	public void createPartControl(Composite parent)
	{
		super.createPartControl(parent);

		// set Word Wrap
		Preferences prefs = NotespringPlugIn.getDefault().getPluginPreferences();
		boolean isWrapped = prefs.getBoolean(NotespringConstants.P_WRAP);
		ISourceViewer sourceViewer = getSourceViewer();
		if (isWrapped)
		{
			// TODO WRAP FIX, but all line-based TextEditor features get into
			// trouble
			if (sourceViewer != null)
			{
				sourceViewer.getTextWidget().setWordWrap(true);
			}
		}

		// TODO - Current Line Color is ALWAYS WHITE, this may be changed in the
		// future
		getPreferenceStore()
				.setValue(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_CURRENT_LINE, "255,255,255");
	}


	@Override
	protected void initializeEditor()
	{
		super.initializeEditor();
		
		// TODO add spell checking
		//SourceViewerConfiguration config = new TextSourceViewerConfiguration(EditorsUI.getPreferenceStore());
		SourceViewerConfiguration config = new TextSourceViewerConfiguration(getPreferenceStore());
		setSourceViewerConfiguration(config);

	}
}
