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
        this.isDelete = isDelete;
        this.borrowedBooks = borrowedBooks;
    }
    
    // Initialised getters and setters for Patron Class
    public int getPatronId(){
        return this.id;
    }

    public void setPatronId(int id){
        this.id = id;
    }

    public String getPatronName(){
        return this.name;
    }

    public void setPatronName(String name){
        this.name = name;
    }

    public String getPatronPhone(){
        return this.phone;
    }

    public void setPatronPhone(String phone){
        this.phone = phone;
    }

    public String getPatronEmail(){
        return this.email;
    }
    
    public void setPatronEmail(String email){
        this.email = email;
    }
    
    public List<Book> getBorrowedBooks(){
        return this.borrowedBooks;
    }
    public void setBorrowedBooks(List<Book> borrowedBooks){
        this.borrowedBooks = borrowedBooks;
    }

    public boolean getIsDeleted(){
        return this.isDelete;
    }

    public void setIsDeleted(boolean isDelete){
        this.isDelete = isDelete;
    }

    //////////////////////////////////////////////

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
}
 