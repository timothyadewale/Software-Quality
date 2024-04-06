import main.java.Presentation;
import main.java.XMLAccessor;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class XMLAccessorTest
{

    @Test
    public void testLoadFileValidXML () throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor ();
        Presentation presentation = new Presentation ();

        xmlAccessor.loadFile (presentation, "path/to/validPresentation.xml");

        // Check if the presentation is filled
        assertFalse ("Presentation should not be empty", presentation.getSize () == 0);

    }

    @Test(expected = IOException.class)
    public void testLoadFileInvalidXML () throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor ();
        Presentation presentation = new Presentation ();
        // loads an xml file
        xmlAccessor.loadFile (presentation, "path/to/invalid.xml");
    }

    @Test
    public void testSaveFile () throws Exception
    {
        XMLAccessor xmlAccessor = new XMLAccessor ();
        Presentation presentation = new Presentation ();

        String savePath = "path/to/savePresentation.xml";
        xmlAccessor.saveFile (presentation, savePath);


    }
}
