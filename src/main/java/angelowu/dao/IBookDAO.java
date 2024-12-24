package angelowu.dao;

import angelowu.pojo.BookInfo;
import java.util.List;

public interface IBookDAO {
    List<BookInfo> getBooks() ;
    BookInfo getBookById(Integer id) ;
    int addBook(BookInfo book);
    int updateBook(BookInfo book) ;
    int deleteBookById(Integer id) ;
}
