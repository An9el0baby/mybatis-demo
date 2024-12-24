package angelowu.day01;

import angelowu.dao.IBookDAO;
import angelowu.dao.impl.BookInfoDAOImpl;
import angelowu.pojo.BookInfo;
import org.junit.jupiter.api.Test;
import java.util.List;

public class test2024 {
    @Test
    public void testAll() throws Exception {
        IBookDAO bookDAO = new BookInfoDAOImpl();
        List<BookInfo> list = bookDAO.getBooks();
        for (BookInfo bookInfo : list) {
            System.out.println(bookInfo);
        }
    }
}
