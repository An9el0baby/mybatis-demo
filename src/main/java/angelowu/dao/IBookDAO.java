package angelowu.dao;

import angelowu.pojo.BookInfo;
import java.util.List;

public interface IBookDAO {
    public List<BookInfo> getBooks() throws Exception;
}
