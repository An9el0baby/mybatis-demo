package angelowu.dao.impl;

import angelowu.dao.IBookDAO;
import angelowu.pojo.BookInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class BookInfoDAOImpl implements IBookDAO {

    @Override
    public List<BookInfo> getBooks() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 1. sql session factory builder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2. session factory
        SqlSessionFactory factory =  builder.build(is);
        // 3. session
        SqlSession session = factory.openSession();
        // 4. sql session select list
        return session.selectList("getBooks");
    }
}
