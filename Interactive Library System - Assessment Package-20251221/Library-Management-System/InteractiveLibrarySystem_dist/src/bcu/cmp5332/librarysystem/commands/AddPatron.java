package bcu.cmp5332.librarysystem.commands;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;
import java.util.Scanner;

public class AddPatron implements Command {

    private final String name;
    private final String phone;

    public AddPatron(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // TODO: implementation here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Patron ID: ");
        String id = sc.nextLine();
        System.out.println("Enter Patron Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Patron Phone Number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Enter Patron Email: ");
        String email = sc.nextLine();
        System.out.println();
        Patron patronNew = new Patron(id, name, phoneNumber, email, false, null);
        Library library2 = new Library();
        library2.addPatron(patronNew);

    }
}
 