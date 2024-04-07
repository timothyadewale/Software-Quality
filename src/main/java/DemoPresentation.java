package main.java;

/**
 * A built-in demo-presentation
 */
public class DemoPresentation extends Accessor
{

    @Override
    public void loadFile (Presentation presentation, String unusedFilename)
    {
        presentation.setTitle ("Demo Presentation");

        // Creating first slide
        Slide slide1 = createFirstSlide ();
        presentation.append (slide1);

        // Creating second slide
        Slide slide2 = createSecondSlide ();
        presentation.append (slide2);

        // Creating third slide
        Slide slide3 = createThirdSlide ();
        presentation.append (slide3);
    }

    @Override
    public void saveFile (Presentation presentation, String unusedFilename)
    {
        // Since saving a demo presentation is not supported, we throw an exception.
        throw new UnsupportedOperationException ("Saving a demo presentation is not supported.");
    }

    private Slide createFirstSlide ()
    {
        Slide slide = new Slide ();
        slide.setTitle ("JabberPoint");
        slide.append (1, "The Java Presentation Tool");
        slide.append (2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append (2, "Copyright (c) 2000-now: Gert Florijn and Sylvia Stuurman");
        slide.append (4, "Starting JabberPoint without a filename shows this presentation");
        slide.append (1, "Navigate:");
        slide.append (3, "Next slide: PgDn or Enter");
        slide.append (3, "Previous slide: PgUp or up-arrow");
        slide.append (3, "Quit: q or Q");
        return slide;
    }

    private Slide createSecondSlide ()
    {
        Slide slide = new Slide ();
        slide.setTitle ("Demonstration of levels and styles");
        slide.append (1, "Level 1");
        slide.append (2, "Level 2");
        slide.append (1, "Again level 1");
        slide.append (1, "Level 1 has style number 1");
        slide.append (2, "Level 2 has style number 2");
        slide.append (3, "This is how level 3 looks like");
        slide.append (4, "And this is level 4");
        return slide;
    }

    private Slide createThirdSlide ()
    {
        Slide slide = new Slide ();
        slide.setTitle ("The third slide");
        slide.append (1, "To open a new presentation,");
        slide.append (2, "use File->Open from the menu.");
        slide.append (1, "");
        slide.append (1, "This is the end of the presentation.");
        slide.append (new BitmapItem (1, "JabberPoint.jpg"));
        return slide;
    }
}
