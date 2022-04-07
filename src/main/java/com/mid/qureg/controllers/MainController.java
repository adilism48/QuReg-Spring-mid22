package com.mid.qureg.controllers;

import com.mid.qureg.interfaces.PeopleInterface;
import com.mid.qureg.models.People;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {

    private final PeopleInterface peopleInterface;

    public MainController(PeopleInterface peopleInterface) {
        this.peopleInterface = peopleInterface;
    }

    @GetMapping("/")
    public String main(Model model) {
        Iterable<People> people = peopleInterface.findAll();
        model.addAttribute("people", people);
        model.addAttribute("title", "main page");
        return "index";
    }

    @PostMapping("/")
    public String mainPostAdd(@RequestParam String name, @RequestParam String surname, @RequestParam String cardID, Model model) {
        People people = new People(cardID, surname, name);
        peopleInterface.save(people);
        return "redirect:/";
    }

    @PostMapping("/del/{id}")
    public String mainPostDelete(@PathVariable(value = "id") long id, Model model) {
        People people = peopleInterface.findById(id).orElseThrow(() -> new PeopleDoesNotExistException(id));
        peopleInterface.delete(people);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.CONFLICT) // 409
    class PeopleDoesNotExistException extends RuntimeException{
        public PeopleDoesNotExistException(long id){
            super("People " + id + " does not exist.");
        }
    }
}
