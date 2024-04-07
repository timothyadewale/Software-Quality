import main.java.DemoPresentation;
import main.java.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoPresentationTest
{

    @Test
    public void testLoadFile ()
    {
        DemoPresentation demoPresentation = new DemoPresentation ();
        Presentation presentation = new Presentation ();
        demoPresentation.loadFile (presentation, "");

        // check number of slides
        assertEquals (3, presentation.getSize (), "Expected number of slides in demo presentation");
    }

    @Test
    public void testSaveFileUnsupportedOperation ()
    {
        DemoPresentation demoPresentation = new DemoPresentation ();
        Presentation presentation = new Presentation ();

        // Use assertThrows to check that an exception is thrown
        assertThrows (UnsupportedOperationException.class, () -> demoPresentation.saveFile (presentation, "anyPath"));
    }
}
