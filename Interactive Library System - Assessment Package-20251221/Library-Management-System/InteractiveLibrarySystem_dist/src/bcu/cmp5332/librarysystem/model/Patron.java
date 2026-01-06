package bcu.cmp5332.librarysystem.model;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private final List<Book> books = new ArrayList<>();
    private String email;
    private boolean isDelete;
    private List<Book> borrowedBooks = new ArrayList<>();

    
    // TODO: implement constructor here
    public Patron(int id, String name, String phone, String email,boolean isDelete, ArrayList<Book> borrowedBooks){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.isDelete = false; // used as a soft delete flag
        this.borrowedBooks = borrowedBooks;
    }
    
    // Initialised getter and setter for Patron ID
    public int getPatronId(){
        return this.id;
    }
    public void setPatronId(int id){
        this.id = id;
    }
    
    // Initialised getter and setter for Patron Name
    public String getPatronName(){
        return this.name;
    }
    public void setPatronName(String name){
        this.name = name;
    }

    // Initialised getter and setter for Patron Phone
    public String getPatronPhone(){
        return this.phone;
    }
    public void setPatronPhone(String phone){
        this.phone = phone;
    }

    // Initialised getter and setter for Patron Email
    public String getPatronEmail(){
        return this.email;
    }    
    public void setPatronEmail(String email){
        this.email = email;
    }
    
    // Initialised getter and setter for books borrowed by a patron
    public List<Book> getBorrowedBooks(){
        return this.borrowedBooks;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks){
        this.borrowedBooks = borrowedBooks;
    }

    // Initialised getter and setter for isDeleted which will be used as a soft delete flag
    public boolean getIsDeleted(){
        return this.isDelete;
    }
    public void setIsDeleted(boolean deleted){
        this.isDelete = deleted;
    }

    //////////////////////////////////////////////  
    
    // Method to get short information about the patron
    public String getPatronDetailsShort(){
        
        String shortPatronDetails =
                  "SHORT INFORMATION ABOUT THE PATRON :-,\n"
                + "PATRON ID :- " + id + "\n"
                + "NAME :- " + name + "\n"
                + "EMAIL :- " + email;
                                
        return shortPatronDetails;
    }
    
    // Method to get detailed information about the Patron
    public String getPatronDetailsLong(){
        
        String completePatronDetails =
                  "DETAILED INFORMATION ABOUT THE PATRON :-,\n"
                + "PATRON ID :- " + id + "\n"
                + "NAME :- " + name + "\n"
                + "EMAIL :- " + email + "\n"
                + "PHONE NUMBER :- " + phone + "\n"
                + "CURRENT ACTIVE LIBRARY MEMBER :- " + this.isDelete + "\n"
                + "BORROWED BOOKS :- " + borrowedBooks + "\n";
                
        return completePatronDetails;
    }
   

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void returnBook(Book book) throws LibraryException {
        // TODO: implementation here
    }
    
    public void addBook(Book book) {
        // TODO: implementation here
    }

    @Override
    public String toString(){
        return "\nPatron [ID: " + id + ", Name: " + name + "Email : " + email + "]\n";
    }

}


 