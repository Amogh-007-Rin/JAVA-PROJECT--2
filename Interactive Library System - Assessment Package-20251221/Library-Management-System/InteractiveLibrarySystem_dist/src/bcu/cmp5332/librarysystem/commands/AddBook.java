package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;
    private boolean isDeleted;

    public AddBook(String title, String author, String publicationYear, String publisher, boolean isDeleted) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher; // Added new feature publisher into the book object.
        this.isDeleted = false;  // Added new feature isDeleted feature to track the soft delete process. 
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int maxId = 0;
    	if (library.getBooks().size() > 0) {
    		int lastIndex = library.getBooks().size() - 1;
            maxId = library.getBooks().get(lastIndex).getId();
    	}
        Book book = new Book(++maxId, title, author, publicationYear, publisher, isDeleted, null);
        library.addBook(book);
        System.out.println("Book Added With Details :-");
        System.out.println("Book #" + book.getTitle() + " added to library with Id:- " + book.getId());
    }
}
 