package ru.chelogaev.dm.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chelogaev.dm.booklibrary.models.BookEntity;

import java.util.List;

public interface BooksRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByNameContaining(String startWord);
}
