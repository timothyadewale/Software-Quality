package main.java;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SlideViewerFrame extends JFrame
{
    private Presentation presentation;

    // added width and height
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;

    public SlideViewerFrame (Presentation presentation, String version)
    {
        super (version);
        this.presentation = presentation;
        initializeFrame ();
    }

    private void initializeFrame ()
    {
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent (presentation);
        configureWindowListener ();
        add (slideViewerComponent);
        setFrameProperties ();
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

    private void setFrameProperties ()
    {
        setSize (FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo (null);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setVisible (true);
    }
}
