package org.example.lab4jv.controller;

import org.example.lab4jv.repo.AuthorRepository;
import org.example.lab4jv.data.Author;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthorController {

    private AuthorRepository authorRepository;

    @GetMapping("/returnT")
    public String homeT(){
        return"index";
    }

    @GetMapping("/author")
    public String showAuthor(Model model){
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("author", authors);
        return "author";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(@RequestParam String author, @RequestParam String nameb, @RequestParam int year){
        Author authors = new Author();
        authors.setAuthor(author);
        authors.setNameb(nameb);
        authors.setYear(year);
        authorRepository.save(authors);
        return "redirect:/author";
    }

    @GetMapping("/delete_author")
    public String deleteAuthor(@RequestParam int id) {
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @GetMapping("/author_edit")
    public String authorEdit(@RequestParam int id, Model model){
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isEmpty()){
            return "redirect:/author";
        }
        model.addAttribute("author",optionalAuthor.get());
        return "author_edit";
    }

    @PostMapping("/update")
    public String authorUpdate(@RequestParam int id,@RequestParam String author, @RequestParam String nameb, @RequestParam int year) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        optionalAuthor.ifPresent(t -> {
            t.setAuthor(author);
            t.setNameb(nameb);
            t.setYear(year);
            authorRepository.save(t);
        });
        return "redirect:/author";
    }

    @GetMapping("/library_author")
    public String showAuthorsByLibrary(@RequestParam String author, Model model) {
        List<Author> authors = authorRepository.findByAuthor(author);

        model.addAttribute("authors", authors);
        model.addAttribute("authorName", author);

        return "author_by_library";
    }

}
