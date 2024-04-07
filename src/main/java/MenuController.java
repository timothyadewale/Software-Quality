package main.java;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.IOException;

public class MenuController extends MenuBar
{

    private Frame parent;
    private Presentation presentation;

    // menu items
    private static final String FILE = "File";
    private static final String OPEN = "Open";
    private static final String NEW = "New";
    private static final String SAVE = "Save";
    private static final String EXIT = "Exit";
    private static final String VIEW = "View";
    private static final String NEXT = "Next";
    private static final String PREV = "Previous";
    private static final String GOTO = "Go to Slide";
    private static final String HELP = "Help";
    private static final String ABOUT = "About JabberPoint";

    // paths to save the presentation file
    private static final String TESTFILE = "path/to/test/presentation.xml";
    private static final String SAVEFILE = "path/to/save/presentation.xml";
    // output it
    private static final String IOEX = "Input/Output Exception: ";
    private static final String LOADERR = "Load Error";
    private static final String SAVEERR = "Save Error";
    private static final String PAGENR = "Page Number:"; // page number


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
        setHelpMenu (helpMenu);
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

    // creation of menu item
    private MenuItem mkMenuItem (String name, ActionListener action)
    {
        MenuItem menuItem = new MenuItem (name, new MenuShortcut (name.charAt (0)));
        menuItem.addActionListener (action);
        return menuItem;
    }
}
