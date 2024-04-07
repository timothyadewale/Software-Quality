import main.java.BitmapItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitmapItemTest
{

    @Test
    public void testLoadImageValidPath ()
    {
        BitmapItem bitmapItem = new BitmapItem (1, "./JabberPoint.gif");
        assertNotNull (bitmapItem.bufferedImage, "Image should be loaded");
    }

    @Test
    public void testLoadImageInvalidPath ()
    {
        BitmapItem bitmapItem = new BitmapItem (1, "./fake/JabberPoint.gif");
        assertNull (bitmapItem.bufferedImage, "Image should not be loaded");
    }
}
