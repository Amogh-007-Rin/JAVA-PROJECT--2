package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

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
