package main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class TextItem extends SlideItem
{
    private final String text;
    private static final String DEFAULT_TEXT = "No Text Given";

    public TextItem (int level, String text)
    {
        super (level);
        this.text = text != null ? text : DEFAULT_TEXT;
    }

    public TextItem ()
    {
        this (0, DEFAULT_TEXT);
    }

    public String getText ()
    {
        return text;
    }

    private AttributedString createAttributedString (Style style, float scale)
    {
        AttributedString attributedString = new AttributedString (text);
        attributedString.addAttribute (TextAttribute.FONT, style.getFont (scale), 0, text.length ());
        return attributedString;
    }

    public Rectangle getBoundingBox (Graphics g, ImageObserver observer, float scale, Style style)
    {
        List<TextLayout> layouts = calculateTextLayouts (g, style, scale);
        int width = 0, height = (int) (style.getLeading () * scale);
        for (TextLayout layout : layouts)
        {
            Rectangle2D bounds = layout.getBounds ();
            width = Math.max (width, (int) bounds.getWidth ());
            height += layout.getAscent () + layout.getDescent () + layout.getLeading ();
        }
        return new Rectangle ((int) (style.getIndent () * scale), 0, width, height);
    }

    public void draw (int x, int y, float scale, Graphics g, Style style, ImageObserver observer)
    {
        List<TextLayout> layouts = calculateTextLayouts (g, style, scale);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor (style.getColor ());
        float penY = y + (style.getLeading () * scale);
        for (TextLayout layout : layouts)
        {
            penY += layout.getAscent ();
            layout.draw (g2d, x + (style.getIndent () * scale), penY);
            penY += layout.getDescent () + layout.getLeading ();
        }
    }

    private List<TextLayout> calculateTextLayouts (Graphics g, Style style, float scale)
    {
        List<TextLayout> layouts = new ArrayList<> ();
        AttributedString attributedString = createAttributedString (style, scale);
        FontRenderContext frc = ((Graphics2D) g).getFontRenderContext ();
        LineBreakMeasurer measurer = new LineBreakMeasurer (attributedString.getIterator (), frc);
        float wrappingWidth = (Slide.WIDTH - style.getIndent ()) * scale;
        while (measurer.getPosition () < text.length ())
        {
            layouts.add (measurer.nextLayout (wrappingWidth));
        }
        return layouts;
    }

    @Override
    public String toString ()
    {
        return String.format ("TextItem[level=%d, text=%s]", getLevel (), text);
    }
}
