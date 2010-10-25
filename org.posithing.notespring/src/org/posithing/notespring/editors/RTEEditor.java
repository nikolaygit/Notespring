package org.posithing.notespring.editors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;

public class RTEEditor extends EditorPart implements ISaveablePart2
{
	private boolean dirty = false;

	private RichTextEditor editor;

	public static final String ID = "org.posithing.notespring.editors.RTEEditor";

	public RTEEditor()
	{
	}

	@Override
	public void createPartControl(Composite parent)
	{
		editor = new NotespringRichTextEditor(parent, SWT.FILL, getEditorSite());
		editor.setEditable(true);
		editor.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e)
			{
				setDirty(true);
			}
		});
		
		// READ THE FILE
		try
		{
			IFile iFile = ((FileEditorInput) getEditorInput()).getFile();
			InputStream stream = iFile.getContents();
			
			String contents = giveContents(stream, iFile.getCharset());
			editor.setText(contents);
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		setPartName(getEditorInput().getName());
	}

	private static String giveContents(InputStream stream, String encoding)
									throws IOException
	{
//		ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
//		byte[] b = new byte[4096];
//		int len = 0;
//		while ((len = stream.read(b)) > 0)
//		{
//			contentStream.write(b, 0, len);
//		}
		
		ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
		byte[] b = new byte[4096];
		int len = 0;
		while ((len = stream.read(b)) > 0)
		{
			contentStream.write(b, 0, len);
		}
		return contentStream.toString(encoding);
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{
		setDirty(false);
		
		FileEditorInput fileInput = (FileEditorInput) getEditorInput();
		IFile file = fileInput.getFile();
		try
		{
			String charset = file.getCharset();
			InputStream stream = new ByteArrayInputStream(editor.getText().getBytes(charset));
			file.setContents(stream, true, true, monitor);
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs()
	{
		doSave(null);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty()
	{
		return dirty;
	}

	private void setDirty(boolean value)
	{
		dirty = value;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return false;
	}

	@Override
	public void setFocus()
	{
	}

	public int promptToSaveOnClose()
	{
		MessageDialog closeConfirmation = new MessageDialog(
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
			"Save resource", 
			null,
			"'" + ((FileEditorInput) getEditorInput()).getFile().getName() + 
			"'" + " has been modified. Save changes?",
			MessageDialog.QUESTION,
			new String[] { "Yes", "No", "Cancel" },
			0);
		
		closeConfirmation.open();

		if (closeConfirmation.getReturnCode() == 0)
			return YES;
		else if (closeConfirmation.getReturnCode() == 1)
			return NO;
		else if (closeConfirmation.getReturnCode() == 2)
			return CANCEL;
		else return CANCEL;
	}
}
