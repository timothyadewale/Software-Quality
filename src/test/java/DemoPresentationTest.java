import main.java.DemoPresentation;
import main.java.Presentation;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class DemoPresentationTest
{

    @Test
    public void testLoadFile ()
    {
        DemoPresentation demoPresentation = new DemoPresentation ();
        Presentation presentation = new Presentation ();
        demoPresentation.loadFile (presentation, "");

        // check number of slides
        assertEquals ("Expected number of slides in demo presentation", 3, presentation.getSize ());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSaveFileUnsupportedOperation ()
    {
        DemoPresentation demoPresentation = new DemoPresentation ();
        Presentation presentation = new Presentation ();
        demoPresentation.saveFile (presentation, "anyPath");
    }
}
