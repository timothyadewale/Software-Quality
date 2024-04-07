package main.java;

import javax.swing.JOptionPane;
import java.io.IOException;

public class JabberPoint
{

    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";
    protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    public static void main (String[] argv)
    {
        try
        {
            runApplication (argv);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog (null, IOERR + ex, JABERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void runApplication (String[] argv) throws IOException
    {
        Style.createStyles ();
        Presentation presentation = new Presentation ();
        initializeUI (presentation);
        loadPresentation (argv, presentation);
    }

    private static void initializeUI (Presentation presentation)
    {
        //  SlideViewerFrame  now  matches the parameters.
        SlideViewerFrame slideViewerFrame = new SlideViewerFrame (presentation, JABVERSION);
        presentation.attach ((PresentationObserver) slideViewerFrame);
    }

    private static void loadPresentation (String[] argv, Presentation presentation) throws IOException
    {
        Accessor accessor = determineAccessor (argv);
        String filePath = argv.length > 0 ? argv[0] : "";
        accessor.loadFile (presentation, filePath);
        presentation.setSlideNumber (0);
    }

    private static Accessor determineAccessor (String[] argv)
    {
        // Simplifies the selection logic for the accessor.
        return argv.length == 0 ? Accessor.getAccessor ("demo") : new XMLAccessor ();
    }
}
