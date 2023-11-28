package ru.chelogaev.dm.booklibrary.dao;

import org.springframework.stereotype.Component;


@Component
public class PersonDAO {

    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getByEmail(String email, int id) {
        return jdbcTemplate.query("select * from Person where email like ? and email is not null and id!=?", new Object[]{email, id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (fio, birth_year, email) VALUES(?, ?, ?)", person.getFio(), person.getBirthYear(),
                person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio=?, birth_year=?, email=? WHERE id=?", updatedPerson.getFio(),
                updatedPerson.getBirthYear(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Person getById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * from book where person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Person> getByFio(String fio, int id) {
        return jdbcTemplate.query("select * from Person where fio = ? and id!=?", new Object[]{fio, id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }*/
}
