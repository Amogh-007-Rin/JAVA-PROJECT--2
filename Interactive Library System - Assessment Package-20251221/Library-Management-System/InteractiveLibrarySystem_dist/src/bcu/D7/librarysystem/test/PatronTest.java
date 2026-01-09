package bcu.D7.librarysystem.test;

import bcu.D7.librarysystem.main.LibraryException;
import bcu.D7.librarysystem.model.Book;
import bcu.D7.librarysystem.model.Loan;
import bcu.D7.librarysystem.model.Patron;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatronTest {

    private Patron patron;

    @Mock
    private Book mockedBook;

    @BeforeEach
    void setUp() {
        // Initialize a fresh Patron before every test
        // passing an empty ArrayList for the borrowedBooks constructor argument
        patron = new Patron(1, "John Doe", "0123456789", "john@example.com", false, new ArrayList<>());
    }

    @Test
    @DisplayName("Constructor should initialize fields correctly")
    void testConstructor() {
        assertEquals(1, patron.getPatronId());
        assertEquals("John Doe", patron.getPatronName());
        assertEquals("0123456789", patron.getPatronPhone());
        assertEquals("john@example.com", patron.getPatronEmail());
        assertFalse(patron.getIsDeleted());
    }

    @Test
    @DisplayName("getPatronDetailsShort returns formatted string")
    void testGetPatronDetailsShort() {
        String details = patron.getPatronDetailsShort();
        assertTrue(details.contains("PATRON ID :- 1"));
        assertTrue(details.contains("NAME :- John Doe"));
        assertTrue(details.contains("EMAIL :- john@example.com"));
    }

    @Test
    @DisplayName("borrowBook successfully borrows an available book")
    void testBorrowBookSuccess() throws LibraryException {
        // Arrange
        LocalDate start = LocalDate.now();
        LocalDate due = start.plusDays(14);
        
        // Mock book behavior: It is not deleted and not currently on loan
        when(mockedBook.getIsDeleted()).thenReturn(false);
        when(mockedBook.isOnLoan()).thenReturn(false);

        // Act
        patron.borrowBook(mockedBook, start, due);

        // Assert
        // Verify that the book was updated with a new Loan object
        verify(mockedBook).setLoan(any(Loan.class));
        
        // IMPORTANT: Because of the logic bug (see notes below), we verify 
        // the book was added by checking if we can now renew it.
        // If borrowBook failed to add to the internal list, renewBook would throw exception.
        assertDoesNotThrow(() -> patron.renewBook(mockedBook, due.plusDays(7)));
    }

    @Test
    @DisplayName("borrowBook throws exception if book is deleted")
    void testBorrowBookDeleted() {
        when(mockedBook.getIsDeleted()).thenReturn(true);

        LibraryException ex = assertThrows(LibraryException.class, () -> {
            patron.borrowBook(mockedBook, LocalDate.now(), LocalDate.now().plusDays(7));
        });

        assertEquals("Book is deleted and cannot be borrowed", ex.getMessage());
        verify(mockedBook, never()).setLoan(any());
    }

    @Test
    @DisplayName("borrowBook throws exception if book is already on loan")
    void testBorrowBookAlreadyOnLoan() {
        when(mockedBook.getIsDeleted()).thenReturn(false);
        when(mockedBook.isOnLoan()).thenReturn(true);

        LibraryException ex = assertThrows(LibraryException.class, () -> {
            patron.borrowBook(mockedBook, LocalDate.now(), LocalDate.now().plusDays(7));
        });

        assertEquals("Book Is Already On Loan, Not Available To Borrow", ex.getMessage());
        verify(mockedBook, never()).setLoan(any());
    }

    @Test
    @DisplayName("renewBook successfully updates due date")
    void testRenewBookSuccess() throws LibraryException {
        // Arrange
        // We must add the book to the patron first so the list check passes
        patron.addBook(mockedBook);
        
        // The book must be on loan to be renewed
        when(mockedBook.isOnLoan()).thenReturn(true);
        LocalDate newDate = LocalDate.now().plusDays(20);

        // Act
        patron.renewBook(mockedBook, newDate);

        // Assert
        verify(mockedBook).setDueDate(newDate);
    }

    @Test
    @DisplayName("renewBook throws exception if patron does not have the book")
    void testRenewBookNotOwned() {
        // We do NOT add the book to the patron
        
        assertThrows(LibraryException.class, () -> {
            patron.renewBook(mockedBook, LocalDate.now());
        });
    }

    @Test
    @DisplayName("returnBook successfully returns book")
    void testReturnBookSuccess() throws LibraryException {
        // Arrange
        patron.addBook(mockedBook);

        // Act
        patron.returnBook(mockedBook);

        // Assert
        verify(mockedBook).returnToLibrary();
        
        // Confirm it is removed by trying to return it again (should fail)
        assertThrows(LibraryException.class, () -> patron.returnBook(mockedBook));
    }

    @Test
    @DisplayName("returnBook throws exception if patron does not have the book")
    void testReturnBookNotOwned() {
        assertThrows(LibraryException.class, () -> {
            patron.returnBook(mockedBook);
        });
        
        verify(mockedBook, never()).returnToLibrary();
    }
}