package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class LoanDataManager implements DataManager {
    
    public final String RESOURCE = "Interactive Library System - Assessment Package-20251221\\Library-Management-System\\InteractiveLibrarySystem_dist\\resources\\data\\loans.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) {
                    line_idx++;
                    continue;
                }
                String[] properties = line.split(SEPARATOR, -1);
                // Expect: patronId::bookId::startDate::dueDate
                if (properties.length < 4) {
                    throw new LibraryException("Invalid loan entry on line " + line_idx);
                }
                String patronIdStr = properties[0].trim();
                String bookIdStr = properties[1].trim();
                String startDateStr = properties[2].trim();
                String dueDateStr = properties[3].trim();
                try {
                    if (patronIdStr.isEmpty()) throw new NumberFormatException("empty patron id");
                    if (bookIdStr.isEmpty()) throw new NumberFormatException("empty book id");
                    int patronId = Integer.parseInt(patronIdStr);
                    int bookId = Integer.parseInt(bookIdStr);
                    LocalDate startDate = LocalDate.parse(startDateStr);
                    LocalDate dueDate = LocalDate.parse(dueDateStr);
                    Patron patron = library.getPatronByID(patronId);
                    Book book = library.getBookByID(bookId);
                    // create loan and attach to both book and patron
                    Loan loan = new Loan(patron, book, startDate, dueDate);
                    book.setLoan(loan);
                    // add book to patron's borrowed list (use addBook which adds to internal list)
                    patron.addBook(book);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse id on line " + line_idx + "\nError: " + ex);
                } catch (java.time.format.DateTimeParseException ex) {
                    throw new LibraryException("Unable to parse date on line " + line_idx + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            // iterate over all books; write a line for each that has a loan
            for (Book book : library.getBooks()) {
                Loan loan = book.getLoan();
                if (loan == null) continue;
                Patron patron = loan.getPatron();
                out.print(patron.getPatronId() + SEPARATOR);
                out.print(book.getId() + SEPARATOR);
                out.print(loan.getStartDate().toString() + SEPARATOR);
                out.print(loan.getDueDate().toString() + SEPARATOR);
                out.println();
            }
        }
    }
    
}
 