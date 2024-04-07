package main.java;

import java.io.IOException;

public abstract class Accessor
{
    public static final String DEMO_NAME = "Demonstration presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    // This method could be adapted to return different types of Accessors based on some condition.
    // For example, you could read from a configuration file or system property to determine
    // whether to load a demo presentation or some other type.
    public static Accessor getAccessor (String type)
    {
        // Example condition, this could be expanded to check for different types
        if ("demo".equalsIgnoreCase (type))
        {
            return new main.java.DemoPresentation ();
        }
        else
        {
            // Placeholder for other potential Accessors
            throw new UnsupportedOperationException ("Accessor type " + type + " not supported.");
        }
    }

    public Accessor ()
    {
    }

    abstract public void loadFile (main.java.Presentation presentation, String fn) throws IOException;

    abstract public void saveFile (main.java.Presentation presentation, String fn) throws IOException;

    // Example method demonstrating the use of the factory method.

    public static void useAccessorExample ()
    {
        Accessor accessor = Accessor.getAccessor ("demo");
        main.java.Presentation presentation = new main.java.Presentation ();
        try
        {
            accessor.loadFile (presentation, "path/to/demo/presentation.xml");
            // Further processing...
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
    }
}
