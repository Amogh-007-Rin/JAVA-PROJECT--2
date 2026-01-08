package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Book;
import java.time.LocalDate;

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
