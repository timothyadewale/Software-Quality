import main.java.BitmapItem;
import main.java.CompositeItem;
import main.java.TextItem;
import org.junit.jupiter.api.Test;

public class CompositeItemTest
{

    @Test
    public void testAddAndDrawSlideItems ()
    {
        CompositeItem compositeItem = new CompositeItem (0);
        compositeItem.addSlideItem (new TextItem (1, "Test text"));
        compositeItem.addSlideItem (new BitmapItem (1, "path/to/valid/image.jpg"));

    }
}
