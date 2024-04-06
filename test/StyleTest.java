import main.java.Style;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class StyleTest
{

    @Test
    public void testStyleRetrieval ()
    {
        Style style = Style.getStyle (0);
        assertNotNull (style);
        // Test properties
        assertEquals (Color.red, style.getColor ());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStyleRetrieval ()
    {
        Style.getStyle (-1); // Expect an IllegalArgumentException
    }
}
