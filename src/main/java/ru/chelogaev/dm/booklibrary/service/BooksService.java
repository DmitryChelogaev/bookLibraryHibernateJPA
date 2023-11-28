package ru.chelogaev.dm.booklibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chelogaev.dm.booklibrary.models.BookEntity;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;
import ru.chelogaev.dm.booklibrary.repository.BooksRepository;
import ru.chelogaev.dm.booklibrary.repository.PersonsRepository;


import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PersonsRepository personsRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PersonsRepository personsRepository) {
        this.booksRepository = booksRepository;
        this.personsRepository = personsRepository;
    }

    public List<BookEntity> getAll(boolean sortByYear) {
        return sortByYear == true ? booksRepository.findAll(Sort.by("year")) : booksRepository.findAll();
    }

    public Object getAllWithPagination(Integer page, Integer books_per_page, boolean sortByYear) {
        if (page<1) {
            throw new IllegalArgumentException("page number must be greater than 0");
        }
        return sortByYear == true ? booksRepository.findAll(PageRequest.of(page-1, books_per_page, Sort.by("year"))).getContent() :
                booksRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }

    public BookEntity getById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public PersonEntity getOwner(int id) {
        return booksRepository.findById(id).map(BookEntity::getOwner).orElse(null);
    }

    @Transactional
    public void save(BookEntity book) {
        booksRepository.save(book);
    }


    @Transactional
    public void update(int id, BookEntity updatedBook) {
        BookEntity book = booksRepository.findById(id).get();
        updatedBook.setOwner(book.getOwner()); // чтобы не терялась связь при обновлении
        updatedBook.setId(id);
        updatedBook.setTakenAt(book.getTakenAt());
        save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assignToPerson(int id_book, PersonEntity person) {
        Date currDate = new Date();
        booksRepository.findById(id_book).ifPresent(
                x -> {
                    x.setOwner(person);
                    x.setTakenAt(currDate);
                }
        );
    }

    @Transactional
    public void releaseFromPerson(int id_book) {
        booksRepository.findById(id_book).ifPresent(
                x -> {
                    x.setOwner(null);
                    x.setTakenAt(null);
                }
        );
    }

    public List<BookEntity> findByNameContaining(String startWord) {
        return booksRepository.findByNameContaining(startWord);
    }

}
