package myapp.controller;

import lombok.RequiredArgsConstructor;
import myapp.entity.Note;
import myapp.servise.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")

public class NoteController {
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView allNote() {
        ModelAndView result = new ModelAndView("list");
        result.addObject("notes", noteService.allNote());
        return result;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView result = new ModelAndView("create");
        result.addObject("note", new Note());
        return result;
    }

    @PostMapping("/create")
    public ModelAndView addNote(@ModelAttribute("note") Note note) {
        noteService.addNewNote(note);
        ModelAndView result = new ModelAndView("redirect:/note/list");
        result.addObject("notes", noteService.allNote());
        return result;
    }
    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("id") long id){
        noteService.deleteById(id);
        ModelAndView result = new ModelAndView("redirect:/note/list");
        result.addObject("notes",noteService.allNote());

        return result;

    }
    @GetMapping("/edit")
    public ModelAndView editPage(@RequestParam("id") long id) {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }


    @PostMapping("/edit")
    public ModelAndView editNote(@ModelAttribute("note") Note updateNote) {
        noteService.update(updateNote);
        ModelAndView result = new ModelAndView("redirect:/note/list");
        result.addObject("notes", noteService.allNote());
        return result;
    }

}
