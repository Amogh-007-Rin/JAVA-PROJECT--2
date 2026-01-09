package bcu.D7.librarysystem.commands;

import java.time.LocalDate;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Library;
import bcu.D7.librarysystem.model.Patron;

// FULLY IMPLEMENTED BORROW-BOOK CLASS TO BORROW A BOOK FROM LIBRARY
public class BorrowBook implements Command {
    
    private final int patronId;
    private final int bookId;

    public BorrowBook(int patronId, int bookId){
        this.patronId = patronId;
        this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException{
        Patron patron = library.getPatronByID(patronId);
        Book book = library.getBookByID(bookId);
        LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
        patron.borrowBook(book, currentDate, dueDate);
        System.out.println("Book #" + book.getId() + " loaned to Patron #" + patron.getPatronId() + " until " + dueDate);
    }
    

}
