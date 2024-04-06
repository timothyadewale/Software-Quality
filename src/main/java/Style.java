package main.java;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Style
{
    private static final List<Style> styles = new ArrayList<> ();

    private static final String FONT_NAME = "Helvetica";
    private final int indent;
    private final Color color;
    private final Font font;
    private final int fontSize;
    private final int leading;

    static
    {
        styles.add (new Style (0, Color.red, 48, 20));
        styles.add (new Style (20, Color.blue, 40, 10));
        styles.add (new Style (50, Color.black, 36, 10));
        styles.add (new Style (70, Color.black, 30, 10));
        styles.add (new Style (90, Color.black, 24, 10));
    }

    public static Style getStyle (int level)
    {
        if (level < 0 || level >= styles.size ())
        {
            throw new IllegalArgumentException ("Invalid level: " + level);
        }
        return styles.get (level);
    }

    private Style (int indent, Color color, int fontSize, int leading)
    {
        this.indent = indent;
        this.color = color;
        this.fontSize = fontSize;
        this.leading = leading;
        this.font = new Font (FONT_NAME, Font.BOLD, fontSize);
    }

    @Override
    public String toString ()
    {
        return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
    }

    public Font getFont (float scale)
    {
        return font.deriveFont ((float) fontSize * scale);
    }

    // Getter methods for the Style properties
    public int getIndent ()
    {
        return indent;
    }

    public Color getColor ()
    {
        return color;
    }

    public Font getFont ()
    {
        return font;
    }

    public int getFontSize ()
    {
        return fontSize;
    }

    public int getLeading ()
    {
        return leading;
    }
}
