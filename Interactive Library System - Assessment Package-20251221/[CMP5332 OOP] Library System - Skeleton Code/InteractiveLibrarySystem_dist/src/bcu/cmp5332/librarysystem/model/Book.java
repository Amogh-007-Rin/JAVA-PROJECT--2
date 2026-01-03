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
    private Loan currentLoan;

    public Book(int id, String title, String author, String publicationYear, String publisher, boolean isDeleted, Loan currentLoan) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isDeleted = isDeleted;
        this.currentLoan = currentLoan;
    }
    
    // Getters and Setters for Book class
    public int getId() {
        return id;
    } 
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getpublisher(){
        return publisher;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
	
    public boolean getIsDeleted(){
        return this.isDeleted;
    }
    public void setIsDeleted(boolean isDelete){
        this.isDeleted = isDelete;
    }
    
    public Loan getCurrentLoan(){
        return this.currentLoan;
    }
    public void setCurrentLoan(Loan currentLoan){
        this.currentLoan = currentLoan;
    }

    ////////////////////////////////////////////////////////////////

    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
        // TODO: implementation here
        return null;
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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
}
