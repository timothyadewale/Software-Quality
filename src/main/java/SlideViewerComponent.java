package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

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
    // creating the font
    private final Font labelFont = new Font (FONT_NAME, FONT_STYLE, FONT_HEIGHT);

    private JFrame frame;

    public SlideViewerComponent (Presentation presentation, JFrame frame)
    {
        this.presentation = presentation;
        this.frame = frame;
        presentation.attach (this);
        setBackground (BACKGROUND_COLOR);
    }


    public SlideViewerComponent (Presentation presentation)
    {
        this (presentation, null);
    }

    @Override
    public Dimension getPreferredSize ()
    {
        return PREFERRED_SIZE;
    }

    //method for the PresentationObserver
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
        // drawslide
        Rectangle area = new Rectangle (0, Y_POSITION, getWidth (), getHeight () - Y_POSITION);
        slide.draw (g, area, this);
    }
}
