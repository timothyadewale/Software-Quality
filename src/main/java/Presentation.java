package main.java;

import java.util.ArrayList;
import java.util.List;

public class Presentation
{
    private String showTitle; // Title of the presentation
    private ArrayList<Slide> showList; // An ArrayList with Slides
    private int currentSlideNumber; // The slide number of the current Slide

    private List<SlideViewerComponent> observers = new ArrayList<> ();

    public Presentation ()
    {
        this.showList = new ArrayList<> ();
        clear ();
    }

    // Method to attach an observer
    public void attach (SlideViewerComponent observer)
    {
        observers.add (observer);
    }

    // Method to notify all observers of change
    private void notifyObservers ()
    {
        for (SlideViewerComponent observer : observers)
        {
            observer.update (this, getCurrentSlide ());
        }
    }

    // Override the setSlideNumber to notify observers, with validation
    public void setSlideNumber (int number)
    {
        if (number >= 0 && number < showList.size ())
        {
            this.currentSlideNumber = number;
            notifyObservers ();
        }
        else
        {
            // Handle the invalid number case, could log or throw an exception
            System.out.println ("Invalid slide number: " + number);
        }
    }

    // Go to the previous slide unless you're at the beginning of the presentation
    public void prevSlide ()
    {
        if (currentSlideNumber > 0)
        {
            setSlideNumber (currentSlideNumber - 1);
        }
    }

    // Go to the next slide unless you're at the end of the presentation.
    public void nextSlide ()
    {
        if (currentSlideNumber < (showList.size () - 1))
        {
            setSlideNumber (currentSlideNumber + 1);
        }
    }

    // Delete the presentation to be ready for the next one.
    public void clear ()
    {
        showList = new ArrayList<> ();
        setSlideNumber (-1);
    }

    // Add a slide to the presentation
    public void append (Slide slide)
    {
        showList.add (slide);
        // Optionally notify observers about the change in the presentation
    }

    // Get a slide with a certain slidenumber
    public Slide getSlide (int number)
    {
        if (number < 0 || number >= getSize ())
        {
            return null;
        }
        return showList.get (number);
    }

    // Give the current slide
    public Slide getCurrentSlide ()
    {
        return getSlide (currentSlideNumber);
    }

    public void exit (int n)
    {
        System.exit (n);
    }

    public int getSize ()
    {
        return showList.size ();
    }

    public String getTitle ()
    {
        return showTitle;
    }

    public void setTitle (String nt)
    {
        showTitle = nt;
    }
}
