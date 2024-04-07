import main.java.BitmapItem;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.*;

public class BitmapItemTest
{

    @Test
    public void testLoadImageValidPath ()
    {
        BitmapItem bitmapItem = new BitmapItem (1, "path/to/valid/image.jpg");
        assertNotNull ("Image should be loaded", bitmapItem.bufferedImage);
    }

    @Test
    public void testLoadImageInvalidPath ()
    {
        BitmapItem bitmapItem = new BitmapItem (1, "invalid/path/image.jpg");
        assertNull ("Image should not be loaded", bitmapItem.bufferedImage);
    }


}
