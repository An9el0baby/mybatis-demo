package angelowu.dao.impl;

import angelowu.dao.IBookDAO;
import angelowu.pojo.BookInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BookInfoDAOImpl implements IBookDAO {

    private final SqlSessionFactory factory;

    public BookInfoDAOImpl() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        this.factory = builder.build(is);
    }

    @Override
    public List<BookInfo> getBooks() {
        try (SqlSession session = factory.openSession()){
            // 4. sql session select list
            return session.selectList("getBooks");
        }
    }

    @Override
    public BookInfo getBookById(Integer id){
        try (SqlSession session = factory.openSession()){
            return session.selectOne("getBookById", id);
        }
    }

    @Override
    public int addBook(BookInfo book) {
        try (SqlSession session = factory.openSession()){
            int rows = session.insert("addBook", book);
            session.commit();
            return rows;
        }
    }

    @Override
    public int updateBook(BookInfo book) {
        try (SqlSession session = factory.openSession()){
            int rows =  session.update("updateBook", book);
            session.commit();
            return rows;
        }
    }

    @Override
    public int deleteBookById(Integer id) {
        try (SqlSession session = factory.openSession()){
             int rows = session.delete("deleteBookById", id);
             session.commit();
             return rows;
        }
    }
}
