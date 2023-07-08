package servise;

import entity.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private static final List<Note> listNote = new ArrayList<>();

    static {
        listNote.add(new Note(1, "Title1", "Content1"));
        listNote.add(new Note(2, "Title2", "Content2"));
        listNote.add(new Note(3, "Title3", "Content3"));
    }


    public List<Note> allNote() {
        return listNote;
    }

    public Note addNewNote(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        listNote.add(note);
        return note;
    }

    public void deleteById(long id) {
        Note note = getById(id);
        if (note == null) {
            throw new IllegalArgumentException("there is no note for this id-> " + id);
        }
        listNote.remove(note);
    }

    public void update(Note note) {
        long id = note.getId();
        Note existingNote = getById(id);
        if (existingNote != null) {
            int index = listNote.indexOf(existingNote);
            listNote.set(index, note);
        } else {
            throw new IllegalArgumentException("there is no note for this id-> " + id);

        }
    }

    public Note getById(long id) {
        for (Note note : listNote) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new IllegalArgumentException("there is no note for this id-> " + id);
    }

    public long generateUniqueId() {
        Random random = new Random();
        long id;
        do {
            id = random.nextLong();
        } while (id < 0 || getById(id) != null);
        return id;
    }


}
