package ru.chelogaev.dm.booklibrary.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;
import ru.chelogaev.dm.booklibrary.repository.PersonsRepository;

import java.time.LocalDate;

@Component
public class PersonValidator implements Validator {
    private PersonsRepository personsRepository;

    @Autowired
    public PersonValidator(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        PersonEntity person = (PersonEntity) obj;
        String age = person.getBirthYear();
        if (age != null && !age.isEmpty()) {
            int year = 0;
            try {
                year = Integer.parseInt(age);
            } catch (IllegalArgumentException ex) {
                errors.rejectValue("birthYear", "", "The year of birth should be a number!");
            } finally {
                if (year < 1900) {
                    errors.rejectValue("birthYear", "", "The year of birth should not be earlier than 1900!");
                }
                LocalDate today = LocalDate.now();
                if (year > today.getYear()) {
                    errors.rejectValue("birthYear", "", "The year of birth cannot be more than the current year!");
                }
            }
        }
        if (personsRepository.findByEmailAndIdNot(person.getEmail(), person.getId()).isPresent()) {
            errors.rejectValue("email", "", "E-mail must be unique!");
        }

        if (personsRepository.findByFioAndIdNot(person.getFio(), person.getId()).isPresent()) {
            errors.rejectValue("fio", "", "Full name must be unique!");
        }
    }
}
