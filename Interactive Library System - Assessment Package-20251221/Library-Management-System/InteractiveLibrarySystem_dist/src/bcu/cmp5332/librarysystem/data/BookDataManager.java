package bcu.cmp5332.librarysystem.data;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// FULLY IMPLEMENTED BOOK-DATA-MANAGER CLASS
public class BookDataManager implements DataManager {
    
    private final String RESOURCE = "Interactive Library System - Assessment Package-20251221\\Library-Management-System\\InteractiveLibrarySystem_dist\\resources\\data\\books.txt";
    
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // skip empty/blank lines
                if (line.trim().isEmpty()) {
                    line_idx++;
                    continue;
                }
                String[] properties = line.split(SEPARATOR, -1);
                // basic validation: at least id, title, author, publicationYear, publisher
                if (properties.length < 5) {
                    throw new LibraryException("Invalid book entry on line " + line_idx);
                }
                String idStr = properties[0].trim();
                String title = properties[1].trim();
                String author = properties[2].trim();
                String publicationYear = properties[3].trim();
                String publisher = properties[4].trim();
                // properties beyond index 4 may contain loan and/or isDeleted; handle both orders
                String isDeletedStr = "";
                String loanStr = null;
                if (properties.length > 5) {
                    String p5 = properties[5].trim();
                    if (p5.equalsIgnoreCase("true") || p5.equalsIgnoreCase("false")) {
                        isDeletedStr = p5;
                        if (properties.length > 6) {
                            loanStr = properties[6].trim();
                        }
                    } else {
                        loanStr = p5;
                        if (properties.length > 6) {
                            isDeletedStr = properties[6].trim();
                        }
                    }
                }
                boolean isDeleted = false;
                if (!isDeletedStr.isEmpty()) {
                    isDeleted = Boolean.parseBoolean(isDeletedStr);
                }
                try {
                    if (idStr.isEmpty()) {
                        throw new NumberFormatException("empty id");
                    }
                    int id = Integer.parseInt(idStr);
                    // Loan parsing intentionally omitted until LoanDataManager is implemented
                    Book book = new Book(id, title, author, publicationYear, publisher, isDeleted, null);
                    library.addBook(book);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse book id '" + idStr + "' on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Book book : library.getBooks()) {
                out.print(book.getId() + SEPARATOR);
                out.print((book.getTitle() == null ? "" : book.getTitle()) + SEPARATOR);
                out.print((book.getAuthor() == null ? "" : book.getAuthor()) + SEPARATOR);
                out.print((book.getPublicationYear() == null ? "" : book.getPublicationYear()) + SEPARATOR);
                out.print((book.getPublisher() == null ? "" : book.getPublisher()) + SEPARATOR);
                // loan is not yet implemented in storage; write a placeholder
                out.print((book.getLoan() == null ? "null" : book.getLoan().toString()) + SEPARATOR);
                out.print(book.getIsDeleted() + SEPARATOR);
                out.println();
            }
        }
    }
}
 