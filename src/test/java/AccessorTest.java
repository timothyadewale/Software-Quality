import main.java.Accessor;
import main.java.DemoPresentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccessorTest
{

    @Test
    public void testGetAccessorValidType ()
    {
        // Getting a demopresentation
        Accessor accessor = Accessor.getAccessor ("demo");
        assertTrue (accessor instanceof DemoPresentation, "The accessor should be an instance of DemoPresentation");
    }

    @Test
    public void testGetAccessorInvalidType ()
    {
        // Expecting an exception
        assertThrows (UnsupportedOperationException.class, () ->
        {
            Accessor.getAccessor ("invalidType");
        }, "Accessing with an invalid type should throw UnsupportedOperationException");
    }
}
