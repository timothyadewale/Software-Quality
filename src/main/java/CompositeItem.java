package main.java;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class CompositeItem extends SlideItem
{
    private List<SlideItem> slideItems = new ArrayList<> ();

    public CompositeItem (int level)
    {
        super (level);
    }

    public void addSlideItem (SlideItem slideItem)
    {
        slideItems.add (slideItem);
    }

    @Override
    public void draw (int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
    {
        for (SlideItem item : slideItems)
        {
            item.draw (x, y, scale, g, style, observer);
        }
    }

    @Override
    public Rectangle getBoundingBox (Graphics g, ImageObserver observer, float scale, Style style)
    {
        // Implementation based on composite items' bounding box calculation
        return null; // Simplified for example
    }
}
