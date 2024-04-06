package main.java;

public class ItemFactory
{

    public static SlideItem createSlideItem (String type, int level, String content)
    {
        switch (type.toLowerCase ())
        {
            case "text":
                return new TextItem (level, content);
            case "image":
                return new BitmapItem (level, content);
            default:
                throw new IllegalArgumentException ("Unknown type: " + type);
        }
    }
}
