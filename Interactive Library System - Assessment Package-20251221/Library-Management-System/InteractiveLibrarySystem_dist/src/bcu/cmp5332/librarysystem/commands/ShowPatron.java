package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Book;
import java.time.LocalDate;
import java.util.List;

// FULLY IMPLEMENTED SHOW-PATRON CLASS TO SHOW A PATRON WITH THEIR DETAILS FROM THE LIBRARY
public class ShowPatron implements Command {

    private final int patronId;

    public ShowPatron(int patronId) {
        this.patronId = patronId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronId);
        System.out.println("Patron #" + patron.getPatronId() + " - " + patron.getPatronName());
        System.out.println("Phone: " + patron.getPatronPhone());
        System.out.println("Email: " + patron.getPatronEmail());
        System.out.println();
        List<Book> books = patron.getBorrowedBooks();
        if (books.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("Borrowed books:");
            for (Book book : books) {
                System.out.println("  " + book.getDetailsShort());
            }
            System.out.println("Patron Has Borrowed " + books.size() + " book(s) borrowed");
        }
    }
}
