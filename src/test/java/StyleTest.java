import main.java.Style;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.*;

public class StyleTest
{

    @Test
    public void testStyleRetrieval ()
    {
        Style style = Style.getStyle (0);
        assertNotNull (style, "The style should not be null.");
        // Test properties
        assertEquals (Color.red, style.getColor (), "The color should be red.");
    }

    @Test
    public void testInvalidStyleRetrieval ()
    {
        // Use assertThrows to expect IllegalArgumentException
        assertThrows (IllegalArgumentException.class, () ->
        {
            Style.getStyle (-1); // Expect an IllegalArgumentException
        });
    }
}
