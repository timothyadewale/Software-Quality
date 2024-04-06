package main.java;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import javax.swing.JOptionPane;

public class MenuController extends MenuBar
{

    private Frame parent;
    private Presentation presentation;

    public MenuController (Frame frame, Presentation pres)
    {
        this.parent = frame;
        this.presentation = pres;
        add (constructFileMenu ());
        add (constructViewMenu ());
        add (constructHelpMenu ());
    }

    private Menu constructFileMenu ()
    {
        Menu fileMenu = new Menu (FILE);
        fileMenu.add (mkMenuItem (OPEN, e -> openPresentation ()));
        fileMenu.add (mkMenuItem (NEW, e -> newPresentation ()));
        fileMenu.add (mkMenuItem (SAVE, e -> savePresentation ()));
        fileMenu.addSeparator ();
        fileMenu.add (mkMenuItem (EXIT, e -> presentation.exit (0)));
        return fileMenu;
    }

    private Menu constructViewMenu ()
    {
        Menu viewMenu = new Menu (VIEW);
        viewMenu.add (mkMenuItem (NEXT, e -> presentation.nextSlide ()));
        viewMenu.add (mkMenuItem (PREV, e -> presentation.prevSlide ()));
        viewMenu.add (mkMenuItem (GOTO, e -> goToSlide ()));
        return viewMenu;
    }

    private Menu constructHelpMenu ()
    {
        Menu helpMenu = new Menu (HELP);
        helpMenu.add (mkMenuItem (ABOUT, e -> AboutBox.show (parent)));
        setHelpMenu (helpMenu); // For portability (Motif, etc.)
        return helpMenu;
    }

    private void openPresentation ()
    {
        presentation.clear ();
        Accessor xmlAccessor = new XMLAccessor ();
        try
        {
            xmlAccessor.loadFile (presentation, TESTFILE);
            presentation.setSlideNumber (0);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog (parent, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint ();
    }

    private void newPresentation ()
    {
        presentation.clear ();
        parent.repaint ();
    }

    private void savePresentation ()
    {
        Accessor xmlAccessor = new XMLAccessor ();
        try
        {
            xmlAccessor.saveFile (presentation, SAVEFILE);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog (parent, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void goToSlide ()
    {
        String pageNumberStr = JOptionPane.showInputDialog ((Object) PAGENR);
        int pageNumber = Integer.parseInt (pageNumberStr);
        presentation.setSlideNumber (pageNumber - 1);
    }

    // Centralizes the creation of MenuItem with action listener using lambda expressions
    private MenuItem mkMenuItem (String name, ActionListener action)
    {
        MenuItem menuItem = new MenuItem (name, new MenuShortcut (name.charAt (0)));
        menuItem.addActionListener (action);
        return menuItem;
    }
}
