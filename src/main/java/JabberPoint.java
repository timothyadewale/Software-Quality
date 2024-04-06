package main.java;

import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 */
public class JabberPoint
{
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    /**
     * The Main Program
     */
    public static void main (String[] argv)
    {
        Style.createStyles ();
        Presentation presentation = new Presentation ();
        SlideViewerFrame slideViewerFrame = new SlideViewerFrame (JABVERSION, presentation);
        presentation.attach (slideViewerFrame);

        try
        {
            Accessor accessor;
            if (argv.length == 0)
            { // A demo presentation
                accessor = Accessor.getAccessor ("demo");
            }
            else
            {
                accessor = new XMLAccessor (); // Assuming XMLAccessor is the default for loading files
            }
            accessor.loadFile (presentation, (argv.length > 0) ? argv[0] : "");
            presentation.setSlideNumber (0);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog (null,
                    IOERR + ex, JABERR,
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
