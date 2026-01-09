package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Library;

// FULLY IMPLEMENTED DELETE-PATRON CLASS TO DELETE A PATRON FROM LIBRARY
public class DeletePatron implements Command {

    private final int patronId;

    public DeletePatron(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        try {
            library.removePatron(patronId);
            System.out.println("Patron #" + patronId + " deleted.");
        } catch (IllegalArgumentException ex) {
            throw new LibraryException(ex.getMessage());
        }
    }
}
