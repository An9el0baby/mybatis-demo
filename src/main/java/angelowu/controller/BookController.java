package angelowu.controller;

import angelowu.pojo.BookInfo;
import angelowu.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // GET /books
    @GetMapping
    public ResponseEntity<List<BookInfo>> getAllBooks(){
        List<BookInfo> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // GET /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BookInfo> getBookById(@PathVariable Integer id){
        BookInfo book = bookService.getBookInfoById(id);
        if (book == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    // POST /books
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookInfo book){
        bookService.addBook(book);
        return ResponseEntity.ok("Book added successfully!");
    }

    // PUT /books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id, @RequestBody BookInfo book){
        book.setId(id);
        bookService.updateBook(book);
        return ResponseEntity.ok("Book updated successfullly!");
    }

    // DELETE /books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book deleted successfully!");
    }

}
