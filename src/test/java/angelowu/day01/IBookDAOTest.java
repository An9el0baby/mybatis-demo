package angelowu.day01;

import angelowu.dao.IBookDAO;
import angelowu.mybatisdemo.MybatisDemoApplication;
import angelowu.pojo.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MybatisDemoApplication.class)
public class IBookDAOTest {

    @Autowired
    private IBookDAO bookDAO;

    @Test
    public void testFindAllBooks() {
        List<BookInfo> list = bookDAO.getBooks();
        assertNotNull(list); // Ensure the list is not null
        assertFalse(list.isEmpty()); // Ensure there's at least one book
        for (BookInfo bookInfo : list) {
            System.out.println(bookInfo);
        }
    }

    @Test
    public void testFindBookById() {
        int testId = 1; // Assume this ID exists in your database
        BookInfo book = bookDAO.getBookById(testId);
        assertNotNull(book); // Ensure the book is found
        assertEquals(testId, book.getId()); // Ensure the ID matches
        System.out.println(book);
    }

    @Test
    public void testAddBook() {
        BookInfo newBook = new BookInfo(null, "Test Book", "Test Author", "Test Description", 10.99);
        int rows = bookDAO.addBook(newBook);
        assertEquals(1, rows); // Ensure one row is affected
        System.out.println("Inserted Book: " + newBook);

        // Verify the book was added
        BookInfo retrievedBook = bookDAO.getBookById(newBook.getId());
        assertNotNull(retrievedBook);
        assertEquals("Test Book", retrievedBook.getName());
    }

    @Test
    public void testUpdateBook() {
        // Assume there's a book with ID 1 that we want to update
        BookInfo bookToUpdate = bookDAO.getBookById(1);
        assertNotNull(bookToUpdate); // Ensure the book exists

        bookToUpdate.setName("Updated Name");
        bookToUpdate.setPrice(15.99);
        int rows = bookDAO.updateBook(bookToUpdate);
        assertEquals(1, rows); // Ensure one row is affected

        // Verify the book was updated
        BookInfo updatedBook = bookDAO.getBookById(1);
        assertEquals("Updated Name", updatedBook.getName());
        assertEquals(15.99, updatedBook.getPrice());
        System.out.println("Updated Book: " + updatedBook);
    }

    @Test
    public void testDeleteBookById() {
        // Add a book to ensure there's one to delete
        BookInfo bookToDelete = new BookInfo(null, "To Be Deleted", "Author", "Description", 9.99);
        bookDAO.addBook(bookToDelete);

        // Delete the book
        int rows = bookDAO.deleteBookById(bookToDelete.getId());
        assertEquals(1, rows); // Ensure one row is affected

        // Verify the book was deleted
        BookInfo deletedBook = bookDAO.getBookById(bookToDelete.getId());
        assertNull(deletedBook); // The book should no longer exist
        System.out.println("Deleted Book ID: " + bookToDelete.getId());
    }
}