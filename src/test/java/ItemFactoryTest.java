import main.java.BitmapItem;
import main.java.ItemFactory;
import main.java.SlideItem;
import main.java.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ItemFactoryTest
{

    @Test
    public void testCreateTextItem ()
    {
        SlideItem textItem = ItemFactory.createSlideItem ("text", 1, "Test text");
        assertTrue (textItem instanceof TextItem);
        assertEquals ("Test text", ((TextItem) textItem).getText ());
    }

    @Test
    public void testCreateBitmapItem ()
    {
        SlideItem bitmapItem = ItemFactory.createSlideItem ("image", 1, "path/to/image.jpg");
        assertTrue (bitmapItem instanceof BitmapItem);
        assertEquals ("path/to/image.jpg", ((BitmapItem) bitmapItem).getName ());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUnknownItem ()
    {
        ItemFactory.createSlideItem ("unknown", 1, "content");
    }
}
