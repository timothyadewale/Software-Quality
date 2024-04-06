import main.java.Accessor;
import main.java.DemoPresentation;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccessorTest
{

    @Test
    public void testGetAccessorValidType ()
    {
        // getting a demopresentation
        Accessor accessor = Accessor.getAccessor ("demo");
        assertTrue (accessor instanceof DemoPresentation);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAccessorInvalidType ()
    {
        // expecting an assertion
        Accessor.getAccessor ("invalidType");
    }
}
