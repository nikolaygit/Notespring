package test.richtexteditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class DisplayMozillaVersion {
    public static void main(String[] args) {
        Device.DEBUG = true;
        Display display = new Display();
        Shell shell = new Shell(display);
        System.out.println(">>>Snippet creating SWT.MOZILLA-style Browser");
        try {
            new Browser(shell, SWT.MOZILLA);
            System.out.println(">>>succeeded");
        } catch (Error e) {
            System.out.println(">>>This failed with the following error:");
            e.printStackTrace();
            System.out.println("\n\nSnippet creating SWT.NONE-style Browser");
            try {
                new Browser(shell, SWT.NONE);
                System.out.println(">>>succeeded");
            } catch (Error e2) {
                System.out.println(">>>This failed too, with the following error:");
                e2.printStackTrace();
            }
        }
        display.dispose();
    }
}
