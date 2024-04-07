import main.java.Presentation;
import main.java.Slide;
import org.junit.Before;
import org.junit.Test; // Use JUnit 4's Test annotation

import static org.junit.Assert.*;

public class PresentationTest
{

    private Presentation presentation;

    @Before
    public void setUp ()
    {
        presentation = new Presentation ();
        presentation.append (new Slide ()); // slide 1
        presentation.append (new Slide ()); // slide 2
        presentation.append (new Slide ()); // slide 3
    }

    @Test
    public void testNextSlide ()
    {
        presentation.setSlideNumber (0);
        presentation.nextSlide ();
        assertEquals ("Slide number should be 1 after moving to the next slide", 1, presentation.getSlideNumber ());
    }

    @Test
    public void testPrevSlideAtStart ()
    {
        presentation.setSlideNumber (0);
        presentation.prevSlide ();
        assertEquals ("Still at the first slide", 0, presentation.getSlideNumber ());
    }

    // Additional tests for edge cases and observer notifications
}
