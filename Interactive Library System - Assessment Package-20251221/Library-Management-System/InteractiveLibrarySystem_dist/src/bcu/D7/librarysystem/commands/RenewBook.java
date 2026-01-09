package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Library;
import bcu.D7.librarysystem.model.Patron;

// FULLY IMPLEMENTED RENEW-BOOK CLASS TO RENEW A BOOK BORROWED FROM THE LIBRARY
public class RenewBook implements Command {

    private final int patronId;
    private final int bookId;

    public RenewBook(int patronId, int bookId) {
        this.patronId = patronId;
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronId);
        Book book = library.getBookByID(bookId);
        LocalDate ReNewDue = currentDate.plusDays(library.getLoanPeriod());
        patron.renewBook(book, ReNewDue);
        System.out.println("Book #" + book.getId() + " renewed until " + ReNewDue);
    }
}
