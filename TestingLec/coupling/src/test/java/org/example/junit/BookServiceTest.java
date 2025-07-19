package org.example.junit;

import org.example.exercise.Book;
import org.example.exercise.BookRepository;
import org.example.exercise.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void borrowAvailableBookShouldMarkAsBorrowedAndSave() {
        // Given
        Book book = new Book("1", "Clean Code", "Robert C. Martin");
        when(bookRepository.findById("1")).thenReturn(book);

        // When
        Book result = bookService.borrowBook("1");

        // Then
        assertFalse(result.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    public void borrowNonExistingBookShouldThrowException() {
        // Given
        when(bookRepository.findById("2")).thenReturn(null);

        // When & Then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> bookService.borrowBook("2"));
        assertEquals("Book not found", ex.getMessage());
    }

    @Test
    public void borrowAlreadyBorrowedBookShouldThrowException() {
        // Given
        Book book = new Book("3", "Refactoring", "Martin Fowler");
        book.setAvailable(false);
        when(bookRepository.findById("3")).thenReturn(book);

        // When & Then
        Exception ex = assertThrows(IllegalStateException.class, () -> bookService.borrowBook("3"));
        assertEquals("Book is already borrowed", ex.getMessage());
    }

    @Test
    public void returnBorrowedBookShouldMarkAsAvailableAndSave() {
        // Given
        Book book = new Book("4", "Design Patterns", "GoF");
        book.setAvailable(false);
        when(bookRepository.findById("4")).thenReturn(book);

        // When
        Book result = bookService.returnBook("4");

        // Then
        assertTrue(result.isAvailable());
        verify(bookRepository).save(book);
    }

    @Test
    public void returnNonExistingBookShouldThrowException() {
        // Given
        when(bookRepository.findById("5")).thenReturn(null);

        // When & Then
        Exception ex = assertThrows(IllegalArgumentException.class, () -> bookService.returnBook("5"));
        assertEquals("Book not found", ex.getMessage());
    }

    @Test
    public void returnAlreadyAvailableBookShouldThrowException() {
        // Given
        Book book = new Book("6", "Effective Java", "Joshua Bloch");
        book.setAvailable(true);
        when(bookRepository.findById("6")).thenReturn(book);

        // When & Then
        Exception ex = assertThrows(IllegalStateException.class, () -> bookService.returnBook("6"));
        assertEquals("Book was not borrowed", ex.getMessage());
    }

    @Test
    public void findBooksByAuthorShouldReturnFilteredAvailableBooks() {
        // Given
        List<Book> books = Arrays.asList(
                new Book("7", "Book A", "Author1"),
                new Book("8", "Book B", "Author2"),
                new Book("9", "Book C", "Author1")
        );
        when(bookRepository.findAllAvailableBooks()).thenReturn(books);

        // When
        List<Book> result = bookService.findAvailableBooksByAuthor("Author1");

        // Then
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(book -> book.getAuthor().equals("Author1")));
    }

    @Test
    public void findBooksByUnknownAuthorShouldReturnEmptyList() {
        // Given
        when(bookRepository.findAllAvailableBooks()).thenReturn(Collections.emptyList());

        // When
        List<Book> result = bookService.findAvailableBooksByAuthor("Unknown");

        // Then
        assertTrue(result.isEmpty());
    }
}
