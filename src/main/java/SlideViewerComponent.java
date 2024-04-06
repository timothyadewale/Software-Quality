package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.*;

public class SlideViewerComponent extends JComponent implements PresentationObserver
{
    private static final long serialVersionUID = 227L;

    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_HEIGHT = 10;
    private static final int X_POSITION = 1100;
    private static final int Y_POSITION = 20;
    private static final Dimension PREFERRED_SIZE = new Dimension (Slide.WIDTH, Slide.HEIGHT);

    private Presentation presentation;
    private Slide slide;
    // Initialize labelFont directly at declaration to ensure it's always initialized
    private final Font labelFont = new Font (FONT_NAME, FONT_STYLE, FONT_HEIGHT);

    // Optional: If frame reference is not necessary, consider removing it
    private JFrame frame; // Keep this if you need a reference to the parent JFrame, make it non-final if not always used

    public SlideViewerComponent (Presentation presentation, JFrame frame)
    {
        this.presentation = presentation;
        this.frame = frame; // Assign the frame if passed, can be null
        presentation.attach (this);
        setBackground (BACKGROUND_COLOR);
    }

    // If you have cases where SlideViewerComponent is instantiated without a JFrame, consider overloading the constructor
    public SlideViewerComponent (Presentation presentation)
    {
        this (presentation, null); // Call the primary constructor with 'null' for the frame
    }

    @Override
    public Dimension getPreferredSize ()
    {
        return PREFERRED_SIZE;
    }

    // Implement update method as required by PresentationObserver
    @Override
    public void update (Presentation presentation, Slide currentSlide)
    {
        this.slide = currentSlide;
        if (currentSlide != null && frame != null)
        {
            frame.setTitle (presentation.getTitle ());
        }
        repaint ();
    }

    @Override
    protected void paintComponent (Graphics g)
    {
        super.paintComponent (g);
        drawBackground (g);
        drawSlideInfo (g);
        if (slide != null)
        {
            drawSlide (g);
        }
    }

    private void drawBackground (Graphics g)
    {
        g.setColor (BACKGROUND_COLOR);
        g.fillRect (0, 0, getWidth (), getHeight ());
    }

    private void drawSlideInfo (Graphics g)
    {
        if (presentation.getSlideNumber () >= 0)
        {
            g.setFont (labelFont);
            g.setColor (TEXT_COLOR);
            String slideInfo = "Slide " + (1 + presentation.getSlideNumber ()) + " of " + presentation.getSize ();
            g.drawString (slideInfo, X_POSITION, Y_POSITION);
        }
    }

    private void drawSlide (Graphics g)
    {
        // Assuming Slide class has a method to draw itself given a Graphics object
        Rectangle area = new Rectangle (0, Y_POSITION, getWidth (), getHeight () - Y_POSITION);
        slide.draw (g, area, this); // Ensure Slide has a draw method compatible with this signature
    }
}
