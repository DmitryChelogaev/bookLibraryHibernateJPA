package ru.chelogaev.dm.booklibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;

import java.util.Optional;

@Repository
public interface PersonsRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<Object> findByFioAndIdNot(String fio, int id);

    Optional<Object> findByEmailAndIdNot(String email, int id);

}
