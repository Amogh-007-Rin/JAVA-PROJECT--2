package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatronDataManager implements DataManager {

    private final String RESOURCE = "Interactive Library System - Assessment Package-20251221\\Library-Management-System\\InteractiveLibrarySystem_dist\\resources\\data\\patrons.txt";
    
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
                // expect at least id, name, phone, email
                if (properties.length < 4) {
                    throw new LibraryException("Invalid patron entry on line " + line_idx);
                }
                String idStr = properties[0].trim();
                String name = properties[1].trim();
                String phone = properties[2].trim();
                String email = properties[3].trim();
                String isDeletedStr = "";
                String borrowedStr = "";
                if (properties.length > 4) {
                    isDeletedStr = properties[4].trim();
                }
                if (properties.length > 5) {
                    borrowedStr = properties[5].trim();
                }
                boolean isDeleted = false;
                if (!isDeletedStr.isEmpty()) {
                    isDeleted = Boolean.parseBoolean(isDeletedStr);
                }
                ArrayList<Book> borrowedBooks = new ArrayList<>();
                if (!borrowedStr.isEmpty() && !borrowedStr.equalsIgnoreCase("null") && !borrowedStr.equalsIgnoreCase("book")) {
                    String[] ids = borrowedStr.split(",");
                    for (String bid : ids) {
                        String b = bid.trim();
                        if (b.isEmpty()) continue;
                        try {
                            int bidInt = Integer.parseInt(b);
                            try {
                                Book book = library.getBookByID(bidInt);
                                borrowedBooks.add(book);
                            } catch (LibraryException le) {
                                // book missing; ignore for now
                                System.out.println("Warning: borrowed book id " + bidInt + " on patron line " + line_idx + " not found in books.");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Warning: invalid borrowed book id '" + b + "' on line " + line_idx);
                        }
                    }
                }
                try {
                    if (idStr.isEmpty()) {
                        throw new NumberFormatException("empty id");
                    }
                    int id = Integer.parseInt(idStr);
                    Patron patron = new Patron(id, name, phone, email, isDeleted, borrowedBooks);
                    patron.setIsDeleted(isDeleted);
                    patron.setBorrowedBooks(borrowedBooks);
                    library.addPatron(patron);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse patron id '" + idStr + "' on line " + line_idx
                            + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Patron patron : library.getPatrons()) {
                out.print(patron.getPatronId() + SEPARATOR);
                out.print((patron.getPatronName() == null ? "" : patron.getPatronName()) + SEPARATOR);
                out.print((patron.getPatronPhone() == null ? "" : patron.getPatronPhone()) + SEPARATOR);
                out.print((patron.getPatronEmail() == null ? "" : patron.getPatronEmail()) + SEPARATOR);
                out.print(patron.getIsDeleted() + SEPARATOR);
                // write borrowed book ids as comma separated list
                List<Book> borrowed = patron.getBorrowedBooks();
                if (borrowed == null || borrowed.isEmpty()) {
                    out.print("" + SEPARATOR);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < borrowed.size(); i++) {
                        if (i > 0) sb.append(",");
                        sb.append(borrowed.get(i).getId());
                    }
                    out.print(sb.toString() + SEPARATOR);
                }
                out.println();
            }
        }
    }
}
 