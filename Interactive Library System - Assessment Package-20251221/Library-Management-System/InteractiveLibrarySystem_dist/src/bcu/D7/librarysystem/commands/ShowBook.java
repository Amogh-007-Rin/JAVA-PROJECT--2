package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Library;

// FULLY IMPLEMENTED SHOW-BOOK CLASS TO SHOW A BOOK WITH ITS DETAILS FROM THE LIBRARY BOOK COLLECTION
public class ShowBook implements Command{
    
    private final int bookId;

    public ShowBook(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookId);
        System.out.println(book.getDetailsLong());
    }
}
