package bcu.D7.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Library;
import bcu.D7.librarysystem.model.Patron;

// FULLY IMPLEMENTED LIST-PATRONS CLASS TO LIST ALL THE PATRONS PRESENT IN THE LIBRARY
public class ListPatrons implements Command {
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException{
        List<Patron> patrons = library.getPatrons();
        System.out.println("----------ALL PATRON DETAILS---------");
        for (Patron patron : patrons){
            System.out.println(patron.getPatronDetailsShort());            
        }
        System.out.println();
        System.out.println("Total Number Of Registered Patrons :- " + patrons.size());
    }
}
