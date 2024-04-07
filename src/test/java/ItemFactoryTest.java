import main.java.BitmapItem;
import main.java.ItemFactory;
import main.java.SlideItem;
import main.java.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemFactoryTest
{

    @Test
    public void testCreateTextItem ()
    {
        SlideItem textItem = ItemFactory.createSlideItem ("text", 1, "Test text");
        assertTrue (textItem instanceof TextItem, "The item should be an instance of TextItem");
        assertEquals ("Test text", ((TextItem) textItem).getText (), "The text should match the expected value");
    }

    @Test
    public void testCreateBitmapItem ()
    {
        SlideItem bitmapItem = ItemFactory.createSlideItem ("image", 1, "path/to/image.jpg");
        assertTrue (bitmapItem instanceof BitmapItem, "The item should be an instance of BitmapItem");
        assertEquals ("path/to/image.jpg", ((BitmapItem) bitmapItem).getName (), "The bitmap name should match the expected value");
    }

    @Test
    public void testCreateUnknownItem ()
    {
        assertThrows (IllegalArgumentException.class, () ->
        {
            ItemFactory.createSlideItem ("unknown", 1, "content");
        }, "Creating an item with unknown type should throw IllegalArgumentException");
    }
}
