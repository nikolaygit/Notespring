package org.posithing.notespring.editors;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class NotespringEditorCompletionProcessor implements IContentAssistProcessor
{
	private final IContextInformation[] NO_CONTEXTS = new IContextInformation[0];

	private final char[] PROPOSAL_ACTIVATION_CHARS = new char[] { 's', 'f', 'p', 'n', 'm', };

	private ICompletionProposal[] NO_COMPLETIONS = new ICompletionProposal[0];

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset)
	{
		try
		{
			IDocument document = viewer.getDocument();
			ArrayList result = new ArrayList();
			String prefix = lastWord(document, offset);
			String indent = lastIndent(document, offset);
			// TODO Content Assist Proposals
			//EscriptModel model = EscriptModel.getModel(document, null);
			//model.getContentProposals(prefix, indent, offset, result);
			return (ICompletionProposal[]) result.toArray(new ICompletionProposal[result.size()]);
		}
		catch (Exception e)
		{
			// ... log the exception ...
			System.out.println("Completion: NO_COMPLETIONS");
			return NO_COMPLETIONS;
		}
	}

	private String lastWord(IDocument doc, int offset)
	{
		try
		{
			for (int n = offset - 1; n >= 0; n--)
			{
				char c = doc.getChar(n);
				if (!Character.isJavaIdentifierPart(c))
					return doc.get(n + 1, offset - n - 1);
			}
		}
		catch (BadLocationException e)
		{
			// ... log the exception ...
			System.out.println("Completion: BadLocationException");
		}
		return "";
	}

	private String lastIndent(IDocument doc, int offset)
	{
		try
		{
			int start = offset - 1;
			while (start >= 0 && doc.getChar(start) != '\n')
				start--;
			int end = start;
			while (end < offset && Character.isSpaceChar(doc.getChar(end)))
				end++;
			return doc.get(start + 1, end - start - 1);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset)
	{
		return NO_CONTEXTS;
	}

	public char[] getCompletionProposalAutoActivationCharacters()
	{
		return PROPOSAL_ACTIVATION_CHARS;
	}

	public char[] getContextInformationAutoActivationCharacters()
	{
		return null;
	}

	public IContextInformationValidator getContextInformationValidator()
	{
		return null;
	}

	public String getErrorMessage()
	{
		return "Content Assist is still under development ...";
	}

}
