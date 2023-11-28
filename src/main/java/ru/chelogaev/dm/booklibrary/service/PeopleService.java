package ru.chelogaev.dm.booklibrary.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chelogaev.dm.booklibrary.models.BookEntity;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;
import ru.chelogaev.dm.booklibrary.repository.PersonsRepository;


import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private PersonsRepository personsRepository;

    @Autowired
    public PeopleService(PersonsRepository personsRepository, EntityManager entityManager) {
        this.personsRepository = personsRepository;
    }

    public List<PersonEntity> getAll() {
        return personsRepository.findAll();
    }

    @Transactional
    public void save(PersonEntity person) {
        personsRepository.save(person);
    }

    public PersonEntity getById(int id) {
        return personsRepository.findById(id).orElse(null);
    }

    public List<BookEntity> getBooksByPersonId(int id) {
        Optional<PersonEntity> person = personsRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            List<BookEntity> books = person.get().getBooks();
            checkExpiredBooks(books);
            return books;
        } else return Collections.emptyList();
    }

    private void checkExpiredBooks(List<BookEntity> books) {
        for (BookEntity book: books) {
            if (book.getTakenAt()!=null && Instant.now().toEpochMilli() - book.getTakenAt().toInstant().toEpochMilli()>864000000) {
                book.setExpired(true);
            } else {
                book.setExpired(false);
            }
        }
    }

    @Transactional
    public void update(int id, PersonEntity person) {
        person.setId(id);
        personsRepository.save(person);
    }

    @Transactional
    public void deleteById(int id) {
        personsRepository.deleteById(id);
    }

}
