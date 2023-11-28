package ru.chelogaev.dm.booklibrary.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
    /*private BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (bookDAO.getByNameAndAuthor(book.getName(), book.getAuthor(), book.getId()).isPresent()) {
            errors.rejectValue("name", "", "There is already a book with this title and author");
        }
        int year;
        try {
            year = Integer.parseInt(book.getYear());
            if (year<1500) {
                errors.rejectValue("year", "", "The year value should not be less than 1500");
            }
            LocalDate today = LocalDate.now();
            if (year > today.getYear()) {
                errors.rejectValue("birthYear", "", "The year value cannot be more than the current year!");
            }
        } catch (IllegalArgumentException ex) {
            errors.rejectValue("year","","The value of the year must be numerical");
        }
    }*/
}
