package angelowu.service;

import angelowu.dao.IBookDAO;
import angelowu.pojo.BookInfo;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookService {
    private final IBookDAO bookDAO;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private static final String CACHE_KEY_PREFIX = "book:";
    private static final String CACHE_LIST_KEY = "book:list";
    private static final long CACHE_DURATION = 30;


    public BookService(IBookDAO bookDAO, RedisTemplate<String, Object> redisTemplate){
        this.bookDAO = bookDAO;
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public List<BookInfo> getAllBooks(){
        // get it from redis cache
        Object cachedBooks = redisTemplate.opsForValue().get(CACHE_LIST_KEY);
        if (cachedBooks != null) {
            try {
                return objectMapper.convertValue(cachedBooks, new TypeReference<List<BookInfo>>() {});
            } catch (Exception e){
                redisTemplate.delete(CACHE_LIST_KEY);
            }
        }
        //  get it from database
        //  sleep for 5 seconds to simulate the time it takes to get data from the database
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        List<BookInfo> books = bookDAO.getBooks();
        redisTemplate.opsForValue().set(CACHE_LIST_KEY, books, CACHE_DURATION, TimeUnit.MINUTES);
        return books;
    }

    public BookInfo getBookInfoById(Integer id){
        String cacheKey = CACHE_KEY_PREFIX + id;
        // get it from redis cache
        BookInfo cachedBook = (BookInfo) redisTemplate.opsForValue().get(cacheKey);
        if (cachedBook != null){
            return cachedBook;
        }

        //  get it from database
        //  sleep for 5 seconds to simulate the time it takes to get data from the database
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        BookInfo book = bookDAO.getBookById(id);
        if (book != null){
            redisTemplate.opsForValue().set(cacheKey, book, CACHE_DURATION, TimeUnit.MINUTES);
        }
        return book;
    }

    public void addBook(BookInfo book){
        bookDAO.addBook(book);
        redisTemplate.delete(CACHE_LIST_KEY);
    }

    public void updateBook(BookInfo book){
        bookDAO.updateBook(book);
        String cacheKey = CACHE_KEY_PREFIX + book.getId();
        redisTemplate.delete(cacheKey);
        redisTemplate.delete(CACHE_LIST_KEY);
    }

    public void deleteBookById(Integer id){
        bookDAO.deleteBookById(id);
        String cacheKey = CACHE_KEY_PREFIX + id; 
        redisTemplate.delete(cacheKey);
        redisTemplate.delete(CACHE_LIST_KEY);
    }
}
