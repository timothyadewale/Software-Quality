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
            return new DemoPresentation ();
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

    abstract public void loadFile (Presentation p, String fn) throws IOException;

    abstract public void saveFile (Presentation p, String fn) throws IOException;

    // Example method demonstrating the use of the factory method.
    // This could be part of a client code or another part of your application.
    public static void useAccessorExample ()
    {
        Accessor accessor = Accessor.getAccessor ("demo");
        Presentation presentation = new Presentation ();
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