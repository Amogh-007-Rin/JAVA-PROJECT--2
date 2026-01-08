package bcu.cmp5332.librarysystem.commands;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;
import java.util.Scanner;

public class AddPatron implements Command {

    private final String name;
    private final String phone;
    private final String email;
    private boolean isDeleted;

    public AddPatron(String name, String phone, String email, boolean isDeleted) {
        this.name = name;
        this.phone = phone;
        this.email = email; // added new property email into patron object.
        this.isDeleted = false; // added new property isDeleted to track the soft delete process.
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // TODO: implementation here
        int patId = 0;
        if(library.getPatrons().size() > 0){
            int lastIndex = library.getPatrons().size() - 1;
            patId = library.getPatrons().get(lastIndex).getPatronId();
        }
        Patron patron = new Patron(patId, name, phone, email, false, null);
        library.addPatron(patron);
        System.out.println("Patron added sucessfully");
        System.out.println("Added patron details");
        System.out.println(patron.getPatronDetailsShort());
    }
}
 