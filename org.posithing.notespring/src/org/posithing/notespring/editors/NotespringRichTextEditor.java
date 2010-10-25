package org.posithing.notespring.editors;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.IRichTextToolBar;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.actions.AddImageAction;
import org.eclipse.epf.richtext.actions.AddLineAction;
import org.eclipse.epf.richtext.actions.AddLinkAction;
import org.eclipse.epf.richtext.actions.AddOrderedListAction;
import org.eclipse.epf.richtext.actions.AddTableAction;
import org.eclipse.epf.richtext.actions.AddUnorderedListAction;
import org.eclipse.epf.richtext.actions.BoldAction;
import org.eclipse.epf.richtext.actions.ClearContentAction;
import org.eclipse.epf.richtext.actions.CopyAction;
import org.eclipse.epf.richtext.actions.CutAction;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.epf.richtext.actions.FontNameAction;
import org.eclipse.epf.richtext.actions.FontSizeAction;
import org.eclipse.epf.richtext.actions.FontStyleAction;
import org.eclipse.epf.richtext.actions.IndentAction;
import org.eclipse.epf.richtext.actions.ItalicAction;
import org.eclipse.epf.richtext.actions.OutdentAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.RichTextComboAction;
import org.eclipse.epf.richtext.actions.SubscriptAction;
import org.eclipse.epf.richtext.actions.SuperscriptAction;
import org.eclipse.epf.richtext.actions.TidyAction;
import org.eclipse.epf.richtext.actions.UnderlineAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;

public class NotespringRichTextEditor extends RichTextEditor
{

	public NotespringRichTextEditor(Composite parent, int style, IEditorSite editorSite, String basePath)
	{
		super(parent, style, editorSite, basePath);
	}

	public NotespringRichTextEditor(Composite parent, int fill, IEditorSite editorSite)
	{
		super(parent, fill, editorSite);
	}

	/**
	 * Fills the Rich Text editor tool bar with action items.
	 * 
	 * @param toolBar
	 *            The Rich text editor tool bar.
	 */
	public void fillToolBar(IRichTextToolBar toolBar)
	{
		toolBar.addAction(new FontStyleAction(this));
		toolBar.addAction(new FontNameAction(this));
		toolBar.addAction(new FontSizeAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new CutAction(this));
		toolBar.addAction(new CopyAction(this));
		toolBar.addAction(new PasteAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new ClearContentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new BoldAction(this));
		toolBar.addAction(new ItalicAction(this));
		toolBar.addAction(new UnderlineAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new SubscriptAction(this));
		toolBar.addAction(new SuperscriptAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new AddOrderedListAction(this));
		toolBar.addAction(new AddUnorderedListAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new OutdentAction(this));
		toolBar.addAction(new IndentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new AddLineAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new FindReplaceAction(this)
		{
			@Override
			public void execute(IRichText control)
			{
				control.getFindReplaceAction().execute(richText);
			}
		});
		toolBar.addAction(new TidyAction(this,false,false,false));
		toolBar.addSeparator();
        toolBar.addAction(new AddImageAction(richText));
		toolBar.addAction(new AddLinkAction(this));
		toolBar.addAction(new AddTableAction(this));
	}

}
