package ru.chelogaev.dm.booklibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chelogaev.dm.booklibrary.models.BookEntity;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;
import ru.chelogaev.dm.booklibrary.service.BooksService;
import ru.chelogaev.dm.booklibrary.service.PeopleService;
import ru.chelogaev.dm.booklibrary.util.BookValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookValidator bookValidator;
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BookValidator bookValidator, BooksService booksService, PeopleService peopleService) {
        this.bookValidator = bookValidator;
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String books(Model model, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "books_per_page", required = false) Integer books_per_page, @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if (page != null && books_per_page != null) {
            model.addAttribute("books", booksService.getAllWithPagination(page, books_per_page, sortByYear));
        } else {
            model.addAttribute("books", booksService.getAll(sortByYear));
        }
        return "books/all";
    }

    @GetMapping("/{id}")
    public String book(@PathVariable("id") int id, Model model, @ModelAttribute("person") PersonEntity person) { //3 parameter for select Person in ComboBox for assign
        model.addAttribute("book", booksService.getById(id));
        PersonEntity owner = booksService.getOwner(id);
        if (Optional.ofNullable(owner).isPresent()) {
            model.addAttribute("owner", owner);
        } else {
            model.addAttribute("people", peopleService.getAll());
        }
        return "books/book";
    }


    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.getById(id));
        return "books/edit";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") BookEntity book) {
        return "books/new";
    }

    @GetMapping("/search")
    public String getBooksForSearch(@ModelAttribute("book") BookEntity book) {
        //model.addAttribute("books", bookService.getAll(false));
        return "books/search";
    }

    @PostMapping("/search")
    public void searchBooks(Model model, @RequestParam("startWord") String startWord) {
        model.addAttribute("books", booksService.findByNameContaining(startWord));
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid BookEntity book, BindingResult result) {
        if (!result.hasErrors()) {
            bookValidator.validate(book, result); //Checking unique pairs of name+author, year
        }
        if (result.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid BookEntity book, BindingResult result, @PathVariable("id") int id) {
        if (!result.hasErrors()) {
            bookValidator.validate(book, result); //Checking unique pairs of name+author, year
        }
        if (result.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@ModelAttribute("person") PersonEntity person, @PathVariable("id") int id_book) {
        booksService.assignToPerson(id_book, person);
        return "redirect:/books/" + id_book;
    }

    @PatchMapping("/{id}/release")
    String releaseFromPerson(@PathVariable("id") int id_book) {
        booksService.releaseFromPerson(id_book);
        return "redirect:/books/" + id_book;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }


}
