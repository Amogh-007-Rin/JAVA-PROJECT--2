package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import java.time.LocalDate;

// FULLY IMPLEMENTED DELETE-BOOK CLASS TO DELETE A BOOK FROM LIBRARY
public class DeleteBook implements Command {

    private final int bookId;

    public DeleteBook(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        try {
            Book book = library.getBookByID(bookId);
            book.setIsDeleted(true);
            System.out.println("Book #" + bookId + " marked as deleted.");
        } catch (IllegalArgumentException | LibraryException ex) {
            throw new LibraryException(ex.getMessage());
        }
    }
}