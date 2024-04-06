package main.java;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Slide
{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private String title;
    private List<SlideItem> items;

    public Slide ()
    {
        this.items = new ArrayList<> ();
    }

    public void append (SlideItem anItem)
    {
        this.items.add (anItem);
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String newTitle)
    {
        this.title = newTitle;
    }

    public void append (int level, String message)
    {
        append (new TextItem (level, message));
    }

    public SlideItem getSlideItem (int number)
    {
        return items.get (number);
    }

    public List<SlideItem> getSlideItems ()
    {
        return items;
    }

    public int getSize ()
    {
        return items.size ();
    }

    public void draw (Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale (area);
        int y = drawTitle (g, area, view, scale);
        drawItems (g, area, view, scale, y);
    }

    private int drawTitle (Graphics g, Rectangle area, ImageObserver view, float scale)
    {
        SlideItem titleItem = new TextItem (0, getTitle ());
        Style titleStyle = Style.getStyle (titleItem.getLevel ());
        titleItem.draw (area.x, area.y, scale, g, titleStyle, view);
        return area.y + titleItem.getBoundingBox (g, view, scale, titleStyle).height;
    }

    private void drawItems (Graphics g, Rectangle area, ImageObserver view, float scale, int startY)
    {
        int y = startY;
        for (SlideItem item : getSlideItems ())
        {
            Style itemStyle = Style.getStyle (item.getLevel ());
            item.draw (area.x, y, scale, g, itemStyle, view);
            y += item.getBoundingBox (g, view, scale, itemStyle).height;
        }
    }

    private float getScale (Rectangle area)
    {
        return Math.min ((float) area.width / WIDTH, (float) area.height / HEIGHT);
    }
}
