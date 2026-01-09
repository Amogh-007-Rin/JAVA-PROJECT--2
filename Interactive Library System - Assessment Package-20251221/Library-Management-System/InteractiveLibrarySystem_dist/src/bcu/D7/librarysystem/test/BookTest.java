package bcu.D7.librarysystem.test;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookTest {

    private Book book;

    @Mock
    private Loan mockedLoan;

    @BeforeEach
    void setUp() {
        // Initialize a book with no active loan (loan = null) and not deleted
        book = new Book(101, "The Great Gatsby", "F. Scott Fitzgerald", "1925", "Scribner", false, null);
    }

    @Test
    @DisplayName("Constructor initializes fields correctly")
    void testConstructor() {
        assertEquals(101, book.getId());
        assertEquals("The Great Gatsby", book.getTitle());
        assertEquals("F. Scott Fitzgerald", book.getAuthor());
        assertEquals("1925", book.getPublicationYear());
        assertEquals("Scribner", book.getPublisher());
        assertFalse(book.getIsDeleted());
        assertNull(book.getLoan());
    }

    @Test
    @DisplayName("getDetailsShort returns correctly formatted string")
    void testGetDetailsShort() {
        String details = book.getDetailsShort();
        assertEquals("Book #101 - The Great Gatsby - F. Scott Fitzgerald", details);
    }

    @Test
    @DisplayName("getDetailsLong contains all key information")
    void testGetDetailsLong() {
        String details = book.getDetailsLong();
        
        assertTrue(details.contains("BOOK ID :- 101"));
        assertTrue(details.contains("TITLE :- The Great Gatsby"));
        assertTrue(details.contains("AUTHOR :- F. Scott Fitzgerald"));
        assertTrue(details.contains("PUBLICATION YEAR :- 1925"));
        assertTrue(details.contains("BOOK PUBLISHER :- Scribner"));
    }

    @Test
    @DisplayName("isOnLoan returns true only when loan object is set")
    void testIsOnLoan() {
        // Initially null
        assertFalse(book.isOnLoan());

        // Set loan
        book.setLoan(mockedLoan);
        assertTrue(book.isOnLoan());
    }

    @Test
    @DisplayName("getStatus returns 'Deleted' when book is marked as deleted")
    void testGetStatusDeleted() {
        book.setIsDeleted(true);
        assertEquals("Deleted", book.getStatus());
    }

    @Test
    @DisplayName("getStatus returns 'Available' when not deleted and no loan")
    void testGetStatusAvailable() {
        book.setIsDeleted(false);
        book.setLoan(null);
        assertEquals("Available", book.getStatus());
    }

    @Test
    @DisplayName("getStatus returns formatted date string when on loan")
    void testGetStatusOnLoan() {
        LocalDate dummyDate = LocalDate.of(2023, 12, 31);
        
        // Arrange
        book.setLoan(mockedLoan);
        when(mockedLoan.getDueDate()).thenReturn(dummyDate);

        // Act
        String status = book.getStatus();

        // Assert
        assertEquals("On loan until 2023-12-31", status);
    }

    @Test
    @DisplayName("getDueDate returns null if no loan exists")
    void testGetDueDateNoLoan() {
        book.setLoan(null);
        assertNull(book.getDueDate());
    }

    @Test
    @DisplayName("getDueDate delegates to Loan object")
    void testGetDueDateWithLoan() {
        LocalDate expectedDate = LocalDate.now();
        
        book.setLoan(mockedLoan);
        when(mockedLoan.getDueDate()).thenReturn(expectedDate);

        assertEquals(expectedDate, book.getDueDate());
    }

    @Test
    @DisplayName("setDueDate updates loan successfully")
    void testSetDueDateSuccess() throws LibraryException {
        LocalDate newDate = LocalDate.now().plusDays(7);
        book.setLoan(mockedLoan);

        book.setDueDate(newDate);

        // Verify that the setDueDate method was called on the mock object
        verify(mockedLoan).setDueDate(newDate);
    }

    @Test
    @DisplayName("setDueDate throws exception if book is not on loan")
    void testSetDueDateException() {
        book.setLoan(null); // Ensure no loan

        LibraryException ex = assertThrows(LibraryException.class, () -> {
            book.setDueDate(LocalDate.now());
        });

        assertEquals("Book Is Not On Loan, Its Available To Borrow", ex.getMessage());
    }

    @Test
    @DisplayName("returnToLibrary removes the loan association")
    void testReturnToLibrary() {
        book.setLoan(mockedLoan);
        assertTrue(book.isOnLoan());

        book.returnToLibrary();

        assertFalse(book.isOnLoan());
        assertNull(book.getLoan());
    }
}