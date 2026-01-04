package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {

    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private Loan loan;
    private String publisher;
    private boolean isDeleted;

    public Book(int id, String title, String author, String publicationYear, String publisher, boolean isDeleted, Loan loan) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.loan = loan;
        this.isDeleted = false;
        // Created an isDelete instance variable to impliment the concept of soft delete
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

    public String getDetailsLong() {
        // TODO: implementation here

        String completeBookDetails =
                  "DETAILED INFORMATION ABOUT THE BOOK :-,\n"
                + "BOOK ID :- " + id + "\n"
                + "TITLE :- " + title + "\n"
                + "AUTHOR :-" + author + "\n"
                + "PUBLICATION YEAR :-" + publicationYear + "\n"
                + "BOOK PUBLISHER :-" + publisher + "\n"
                + "AVAILABLE :-" + loan + "\n";
        return completeBookDetails;
    }

    public boolean isOnLoan() {
        return (loan != null);
    }

    public String getStatus() {
        // TODO: implementation here
        return null;
    }

    public LocalDate getDueDate() {
        // TODO: implementation here
        return null;
    }

    public void setDueDate(LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void returnToLibrary() {
        loan = null;
    }
}
