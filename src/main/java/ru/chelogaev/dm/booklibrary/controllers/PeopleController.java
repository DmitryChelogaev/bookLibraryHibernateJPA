package ru.chelogaev.dm.booklibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.chelogaev.dm.booklibrary.util.PersonValidator;
import ru.chelogaev.dm.booklibrary.models.PersonEntity;
import ru.chelogaev.dm.booklibrary.service.PeopleService;
import ru.chelogaev.dm.booklibrary.util.PersonValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonValidator personValidator;
    private PeopleService peopleService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.getAll());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.getById(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") PersonEntity person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid PersonEntity person,
                         BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            personValidator.validate(person, bindingResult);//Age verification, unique full name, email

        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.getById(id));
        return "people/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid PersonEntity person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (!bindingResult.hasErrors())
            personValidator.validate(person, bindingResult);//Age verification, unique full name, email

        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }
}
