package bcu.D7.librarysystem.model;

import java.time.LocalDate;

// FULLY IMPLEMENTED LOAN CLASS
public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;
    
    // CONSTRUCTOR FOR LOAN CLASS
    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        // TODO: implementation here
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    
    // TODO: implementation of Getter and Setter methods

    // Initialised getter and setter for Patron
    public Patron getPatron(){
        return this.patron;
    }    
    public void setPatron(Patron patron){
        this.patron = patron;
    }

    // Initialised getter and setter for Book
    public Book getBook(){
        return this.book;
    }
    public void setBook(Book book){
        this.book = book;
    }

    // Initialised getter and setter for StartDate
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    // Initialised getter and setter for DueDate
    public LocalDate getDueDate(){
        return this.dueDate;
    }    
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
}
 