package bcu.D7.librarysystem.model;
import java.util.*;

import bcu.D7.librarysystem.main.LibraryException;

// FULLY IMPLEMENTED LIBRARY CLASS
public class Library {
    
    private final int loanPeriod = 7;
    private final Map<Integer, Patron> patrons = new TreeMap<>();
    private final Map<Integer, Book> books = new TreeMap<>();

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public List<Book> getBooks() {
        List<Book> out = new ArrayList<>(books.values());
        return Collections.unmodifiableList(out);
    }

    public Book getBookByID(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("There is no such book with that ID.");
        }
        return books.get(id);
    }

    // Added a get all the patron method
    public List<Patron> getPatrons(){
        List<Patron> out = new ArrayList<>(patrons.values());
        return Collections.unmodifiableList(out);
    }

    public Patron getPatronByID(int id) throws LibraryException {
        // TODO: implementation here
        if(!patrons.containsKey(id)){
            throw new LibraryException("There is no such patron present with that ID");
        }
        return patrons.get(id);
    }
    
    // IMPLEMENTED ADD-BOOK METHOD TO ADD A BOOK INTO LIBRARY
    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }
    
    // IMPLEMENTED ADD-PATRON METHOD TO ADD A PATRON INTO THE LIBRARY
    public void addPatron(Patron patron) {
        // TODO: implementation here
        if (patrons.containsKey(patron.getPatronId())){
            throw new IllegalArgumentException("Duplicate patron ID");
        }
        patrons.put(patron.getPatronId(), patron);
    }
    
    //IMPLEMENTED A NEW FEATURE TO REMOVE A PATRON FORM THE LIBRARY
    public void removePatron(int patronId){
        if(!patrons.containsKey(patronId)){
            throw new IllegalArgumentException("Patron Id Miss Match or PatronId Does Not Exist");
        }
        patrons.remove(patronId);
    }
    
}
 