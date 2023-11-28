package ru.chelogaev.dm.booklibrary.dao;

import org.springframework.stereotype.Component;

@Component
public class BookDAO {
    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * from book", new BeanPropertyRowMapper<>(Book.class));
    }


    public Optional<Book> getByNameAndAuthor(String name, String author, int id) {
        return jdbcTemplate.query("SELECT * from book where name = ? and author = ? and id != ?", new Object[]{name, author, id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("update book set name = ?, author = ?, year = ? where id = ?", book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public Book getById(int id) {
        return jdbcTemplate.query("SELECT * from book where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT into book (name, author, year) VALUES (?,?,?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void assignToPerson(int id, int personId) {
        jdbcTemplate.update("update book set person_id = ? where id =?", personId, id);
    }

    public Optional<Person> getOwner(int id_book) {
      return  jdbcTemplate.query("select * from person join book on person.id=book.person_id and book.id=?", new Object[]{id_book}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public void releaseFromPerson(int id_book) {
        jdbcTemplate.update("update book set person_id = NULL where id = ?", id_book);
    }*/
}