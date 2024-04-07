package main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLAccessor extends Accessor
{

    private static final String FONT_SIZE = "fontSize";
    private static final String ITEM_KIND_TEXT = "text";
    private static final String ITEM_KIND_IMAGE = "image";
    private static final String SHOWTITLE = "showtitle";
    private static final String SLIDE = "slide";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String KIND = "kind";
    private static final String SLIDETITLE = "title";

    public void loadFile (Presentation presentation, String filename)
    {
        try
        {
            Document document = parseXMLDocument (filename);
            Element docElement = document.getDocumentElement ();
            presentation.setTitle (getElementTextContent (docElement, SHOWTITLE));
            NodeList slides = docElement.getElementsByTagName (SLIDE);
            loadSlides (presentation, slides);
        } catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    private Document parseXMLDocument (String filename) throws Exception
    {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
        return builder.parse (new File (filename));
    }

    private void loadSlides (Presentation presentation, NodeList slides)
    {
        for (int i = 0; i < slides.getLength (); i++)
        {
            Element slideElement = (Element) slides.item (i);
            Slide slide = new Slide ();
            slide.setTitle (getElementTextContent (slideElement, SLIDETITLE));
            presentation.append (slide);
            loadSlideItems (slide, slideElement.getElementsByTagName (ITEM));
        }
    }

    private void loadSlideItems (Slide slide, NodeList items)
    {
        for (int i = 0; i < items.getLength (); i++)
        {
            Element itemElement = (Element) items.item (i);
            slide.append (createSlideItemFromElement (itemElement));
        }
    }

    private SlideItem createSlideItemFromElement (Element item)
    {
        NamedNodeMap attributes = item.getAttributes ();
        int level = Integer.parseInt (attributes.getNamedItem (LEVEL).getTextContent ());
        String kind = attributes.getNamedItem (KIND).getTextContent ();
        String content = item.getTextContent ();

        switch (kind)
        {
            case ITEM_KIND_TEXT:
                return new TextItem (level, content);
            case ITEM_KIND_IMAGE:
                return new BitmapItem (level, content);
            default:
                throw new IllegalArgumentException ("Unknown item kind: " + kind);
        }
    }

    public void saveFile (Presentation presentation, String filename) throws IOException
    {
        try (PrintWriter out = new PrintWriter (new FileWriter (filename)))
        {
            out.println ("<?xml version=\"1.0\"?>");
            out.println ("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
            out.println ("<presentation>");
            out.printf ("<showtitle>%s</showtitle>%n", presentation.getTitle ());
            for (Slide slide : presentation.getSlides ())
            {
                saveSlide (out, slide);
            }
            out.println ("</presentation>");
        }
    }

    private void saveSlide (PrintWriter out, Slide slide)
    {
        out.println ("<slide>");
        out.printf ("<title>%s</title>%n", slide.getTitle ());
        for (SlideItem item : slide.getSlideItems ())
        {
            out.printf ("<item kind=\"%s\" level=\"%d\">%s</item>%n",
                    item instanceof TextItem ? ITEM_KIND_TEXT : ITEM_KIND_IMAGE,
                    item.getLevel (),
                    item instanceof TextItem ? ((TextItem) item).getText () : ((BitmapItem) item).getName ());
        }
        out.println ("</slide>");
    }

    private String getElementTextContent (Element element, String tagName)
    {
        return element.getElementsByTagName (tagName).item (0).getTextContent ();
    }
}
