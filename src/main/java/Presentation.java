package main.java;

import java.util.ArrayList;
import java.util.List;

public class Presentation
{
    private String showTitle; // Title of the presentation
    private ArrayList<Slide> showList = new ArrayList<> (); // An ArrayList with Slides
    private int currentSlideNumber = -1; // The slide number of the current Slide

    private List<PresentationObserver> observers = new ArrayList<> ();

    // Attach an observer to the Presentation
    public void attach (PresentationObserver observer)
    {
        observers.add (observer);
        // Optionally, you might want to immediately update the observer with the current state
        if (currentSlideNumber >= 0)
        {
            observer.update (this, getCurrentSlide ());
        }
    }

    // Notify all observers of a change
    private void notifyObservers ()
    {
        Slide currentSlide = getCurrentSlide ();
        for (PresentationObserver observer : observers)
        {
            observer.update (this, currentSlide);
        }
    }

    // Sets the current slide number and notifies observers
    public void setSlideNumber (int number)
    {
        if (number >= 0 && number < showList.size ())
        {
            this.currentSlideNumber = number;
            notifyObservers ();
        }
        else
        {
            System.out.println ("Invalid slide number: " + number);
        }
    }

    public void prevSlide ()
    {
        if (currentSlideNumber > 0)
        {
            setSlideNumber (currentSlideNumber - 1);
        }
    }

    public void nextSlide ()
    {
        if (currentSlideNumber < (showList.size () - 1))
        {
            setSlideNumber (currentSlideNumber + 1);
        }
    }

    public void clear ()
    {
        showList.clear ();
        currentSlideNumber = -1;
        notifyObservers ();
    }

    public void append (Slide slide)
    {
        showList.add (slide);
        notifyObservers ();
    }

    public Slide getSlide (int number)
    {
        if (number >= 0 && number < showList.size ())
        {
            return showList.get (number);
        }
        return null;
    }

    public Slide getCurrentSlide ()
    {
        return getSlide (currentSlideNumber);
    }

    public void exit (int status)
    {
        System.exit (status);
    }

    public int getSize ()
    {
        return showList.size ();
    }

    public String getTitle ()
    {
        return showTitle;
    }

    public void setTitle (String title)
    {
        this.showTitle = title;
        notifyObservers ();
    }

    public int getSlideNumber ()
    {
        return currentSlideNumber;
    }

    public List<Slide> getSlides ()
    {
        return showList;
    }
}
