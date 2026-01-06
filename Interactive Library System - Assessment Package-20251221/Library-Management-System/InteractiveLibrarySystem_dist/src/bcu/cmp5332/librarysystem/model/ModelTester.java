package bcu.cmp5332.librarysystem.model;


public class ModelTester {
    
    public static void main(String args[]){
        
        
        Patron patron2 = new Patron(2, "Abhinava", "9380663282", "amoghdath233@gmail.com", false, null);
        Patron patron1 = new Patron(1, "Amogh", "9380663282", "amoghdath233@gmail.com", false, null);
        Loan loanOne = new Loan(patron1, null, null, null);
        Book book1 = new Book(1, "firstBook", "amoghdath", "2025", "Param", false, loanOne );


        // Short book details 
        System.out.println(book1.getDetailsShort());
        System.out.println();
        System.out.println(book1.getDetailsLong());
        System.out.println();
        System.out.println(patron1.getPatronDetailsShort());
        System.out.println();
        System.out.println(patron1.getPatronDetailsLong());
        System.out.println();
        Library library = new Library();
        System.out.println("---------------LIST OF PATRONS IN THE LIBRARY------------------");
        library.addPatron(patron1);
        library.addPatron(patron2);
        System.out.println( library.getPatrons());
    }
}
