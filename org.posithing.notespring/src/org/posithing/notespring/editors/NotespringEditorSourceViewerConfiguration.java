package org.posithing.notespring.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class NotespringEditorSourceViewerConfiguration extends SourceViewerConfiguration
{
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer)
	{
		ContentAssistant ca = new ContentAssistant();
        IContentAssistProcessor cap = new NotespringEditorCompletionProcessor();
        ca.setContentAssistProcessor(cap, IDocument.DEFAULT_CONTENT_TYPE);
        ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));
		return ca;
	}
}
