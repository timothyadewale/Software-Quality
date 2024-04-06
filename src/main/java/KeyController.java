package main.java;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.Map;

// Interface for handling key actions
interface KeyActionHandler
{
    void handleAction ();
}

// KeyController now uses handlers for actions, adhering to OCP and SRP
public class KeyController extends KeyAdapter
{
    private final Presentation presentation;
    private final Map<Integer, KeyActionHandler> actionHandlers;

    public KeyController (Presentation p)
    {
        this.presentation = p;
        this.actionHandlers = new HashMap<> ();
        initializeHandlers ();
    }

    private void initializeHandlers ()
    {
        actionHandlers.put (KeyEvent.VK_PAGE_DOWN, presentation::nextSlide);
        actionHandlers.put (KeyEvent.VK_DOWN, presentation::nextSlide);
        actionHandlers.put (KeyEvent.VK_ENTER, presentation::nextSlide);
        actionHandlers.put ((int) '+', presentation::nextSlide);

        actionHandlers.put (KeyEvent.VK_PAGE_UP, presentation::prevSlide);
        actionHandlers.put (KeyEvent.VK_UP, presentation::prevSlide);
        actionHandlers.put ((int) '-', presentation::prevSlide);

        actionHandlers.put ((int) 'q', () -> System.exit (0));
        actionHandlers.put ((int) 'Q', () -> System.exit (0));
    }

    @Override
    public void keyPressed (KeyEvent keyEvent)
    {
        if (actionHandlers.containsKey (keyEvent.getKeyCode ()))
        {
            actionHandlers.get (keyEvent.getKeyCode ()).handleAction ();
        }
    }
}
