package bcu.D7.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Library;

// FULLY IMPLEMENTED LIST-BOOKS CLASS TO LIST ALL THE BOOK PRESENT IN THE LIBRARY
public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        for (Book book : books) {
            System.out.println(book.getDetailsShort());
        }
        System.out.println("Total book(s): " + books.size());
    }
}
 