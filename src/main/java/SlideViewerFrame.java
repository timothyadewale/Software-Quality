package main.java;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;

    private static final String DEFAULT_TITLE = "Jabberpoint 1.6 - OU";
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;

    public SlideViewerFrame (Presentation presentation)
    {
        super (DEFAULT_TITLE);
        initializeFrame (presentation);
    }

    private void initializeFrame (Presentation presentation)
    {
        SlideViewerComponent slideViewerComponent = createSlideViewerComponent (presentation);
        configureWindowListener ();
        addComponentsToFrame (slideViewerComponent, presentation);
        setFrameProperties ();
    }

    private SlideViewerComponent createSlideViewerComponent (Presentation presentation)
    {
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent (presentation, this);
        presentation.setShowView (slideViewerComponent);
        return slideViewerComponent;
    }

    private void configureWindowListener ()
    {
        addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing (WindowEvent e)
            {
                System.exit (0);
            }
        });
    }

    private void addComponentsToFrame (SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        getContentPane ().add (slideViewerComponent);
        addKeyListener (new KeyController (presentation));
        setMenuBar (new MenuController (this, presentation));
    }

    private void setFrameProperties ()
    {
        setSize (new Dimension (FRAME_WIDTH, FRAME_HEIGHT));
        setVisible (true);
    }
}
