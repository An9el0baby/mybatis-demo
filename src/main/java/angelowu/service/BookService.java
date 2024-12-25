package angelowu.service;

import angelowu.dao.IBookDAO;
import angelowu.pojo.BookInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public List<BookInfo> getAllBooks(){
        return bookDAO.getBooks();
    }

    public BookInfo getBookInfoById(Integer id){
        return bookDAO.getBookById(id);
    }

    public void addBook(BookInfo book){
        bookDAO.addBook(book);
    }

    public void updateBook(BookInfo book){
        bookDAO.updateBook(book);
    }

    public void deleteBookById(Integer id){
        bookDAO.deleteBookById(id);
    }
}
