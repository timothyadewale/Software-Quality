import main.java.Presentation;
import main.java.Slide;
import org.junit.Before;
import org.junit.jupiter.api.Test;

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
        assertEquals (1, presentation.getSlideNumber ());
    }

    @Test
    public void testPrevSlideAtStart ()
    {
        presentation.setSlideNumber (0);
        presentation.prevSlide ();
        assertEquals (0, presentation.getSlideNumber ()); // Still at the first slide
    }

    // Additional tests for edge cases and observer notifications
}
