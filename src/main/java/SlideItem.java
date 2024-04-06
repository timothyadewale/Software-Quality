package main.java;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Represents an abstract slide item that can be drawn on a slide.
 * Subclasses must provide implementation for drawing the item and determining its bounding box.
 */
public abstract class SlideItem
{
    private final int level;

    /**
     * Constructs a new SlideItem with the specified level.
     *
     * @param level The level (indentation/order) of the slide item.
     */
    public SlideItem (int level)
    {
        this.level = level;
    }

    /**
     * Draws the slide item on the given graphics context.
     *
     * @param x        The x coordinate where the item should be drawn.
     * @param y        The y coordinate where the item should be drawn.
     * @param scale    The scale factor for drawing.
     * @param g        The Graphics context to draw on.
     * @param style    The style to use for drawing.
     * @param observer An image observer to be notified of any image updates.
     */
    public abstract void draw (int x, int y, float scale, Graphics g, Style style, ImageObserver observer);

    /**
     * Gets the bounding box of the slide item.
     *
     * @param g        The Graphics context.
     * @param observer An image observer to be notified of any image updates.
     * @param scale    The scale factor for the bounding box.
     * @param style    The style of the slide item.
     * @return A Rectangle representing the bounding box of the slide item.
     */
    public abstract Rectangle getBoundingBox (Graphics g, ImageObserver observer, float scale, Style style);

    /**
     * Gets the level of the slide item.
     *
     * @return The level of the slide item.
     */
    public int getLevel ()
    {
        return level;
    }
}
