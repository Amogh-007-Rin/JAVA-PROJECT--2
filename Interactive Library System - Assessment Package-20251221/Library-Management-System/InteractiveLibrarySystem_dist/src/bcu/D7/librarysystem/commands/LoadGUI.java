package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.gui.MainWindow;
import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Library;

// FULLY IMPLEMENTED LOAD-GUI CLASS TO LOAD-GUI APPLICATION
public class LoadGUI implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        new MainWindow(library);
    }
    
}
 