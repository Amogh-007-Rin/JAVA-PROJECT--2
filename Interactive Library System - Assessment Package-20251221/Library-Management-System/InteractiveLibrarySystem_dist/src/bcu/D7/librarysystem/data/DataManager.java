package bcu.D7.librarysystem.data;

import java.io.IOException;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Library;

public interface DataManager {
    
    public static final String SEPARATOR = "::";
    
    public void loadData(Library library) throws IOException, LibraryException;
    public void storeData(Library library) throws IOException;
}
 