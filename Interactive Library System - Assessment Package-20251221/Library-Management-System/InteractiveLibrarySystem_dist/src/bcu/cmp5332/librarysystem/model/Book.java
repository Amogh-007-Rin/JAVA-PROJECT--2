package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

// FULLY IMPLEMENTED BOOK CLASS
public class Book {

    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private Loan loan;
    private String publisher;
    private boolean isDeleted;
    
    // CONSTRUCTOR METHOD FOR BOOK CLASS
    public Book(int id, String title, String author, String publicationYear, String publisher, boolean isDeleted, Loan loan) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.loan = loan; 
        this.isDeleted = isDeleted;
        // Created an isDelete instance variable to implement the concept of soft delete
    }

    // Initialised getter and setter for Book Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // Initialised getter and setter for Book Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // Initialised getter and setter for Book Author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    // Initialised getter and setter for Book Publication Year
    public String getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    // Initialised getter and setter for Book Publisher
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Initialised getter and setter for Current Book Loan
    public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    // Initialised getter and setter for Book Deleted Status
    public boolean getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(boolean isDelete) {
        this.isDeleted = isDelete;
    }

    ////////////////////////////////////////////////////////////////

    public String getDetailsShort() {
        return "Book #" + id + " - " + title + " - " + author;
    }
    
    // IMPLEMENTED GET-DETAILS-LONG METHOD TO GET COMPLETE INFORMATION ABOUT THE BOOK
    public String getDetailsLong() {
        // TODO: implementation here

        String completeBookDetails =
                  "DETAILED INFORMATION ABOUT THE BOOK :-,\n"
                + "BOOK ID :- " + id + "\n"
                + "TITLE :- " + title + "\n"
                + "AUTHOR :- " + author + "\n"
                + "PUBLICATION YEAR :- " + publicationYear + "\n"
                + "BOOK PUBLISHER :- " + publisher + "\n"
                + "ON-LOAN :- " + loan + "\n";
        return completeBookDetails;
    }

    public boolean isOnLoan() {
        return (loan != null);
    }
    
    // IMPLEMENTED GET-STATUS METHOD TO GET THE STATUS OF A PARTICULAR BOOK
    public String getStatus() {
        if (getIsDeleted()) {
            return "Deleted";
        }
        if (!isOnLoan()) {
            return "Available";
        } else if (isOnLoan()) {
            LocalDate dueDate = getDueDate();
            return "On loan until " + dueDate.toString();
        } else {
            return "No Information About The Selected Book";
        }
    }
    
    // IMPLEMENTED GET-DUE-DATE METHOD TO GET-DUE-DATE OF THE BOOK TO RETURN
    public LocalDate getDueDate() {
        // TODO: implementation here
        if(loan == null){
            return null;
        }
        LocalDate dueDate = loan.getDueDate();
        return dueDate;
    }
    
    // IMPLEMENTED SET-DUE-DATE METHOD TO SET-DUE-DATE FOR A BOOK
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
        if(loan == null){
            throw new LibraryException("Book Is Not On Loan, Its Available To Borrow");
        }
        loan.setDueDate(dueDate);
    }

    public void returnToLibrary() {
        loan = null;
    }
}
