package myapp.servise;

import myapp.entity.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private  static final Map<Long, Note> noteMap = new HashMap<>();
    static {
        noteMap.put(1L, new Note(1,"title_1","content_1"));
        noteMap.put(2L, new Note(2,"title_1","content_1"));
        noteMap.put(3L, new Note(3,"title_1","content_1"));
    }


    public List<Note> allNote() {
        return new ArrayList<>(noteMap.values());
    }

    public Note addNewNote(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("there is no note for this id-> " + id);
        }
        noteMap.remove(id);
    }

    public void update(Note note) {
        long id = note.getId();
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("there is no note for this id-> " + id);
        }
        noteMap.put(id, note);

    }

    public Note getById(long id) {
        if (!noteMap.containsKey(id)) {
            throw new IllegalArgumentException("there is no note for this id-> " + id);
        }
        return noteMap.get(id);

    }

    public long generateUniqueId() {
        Random random = new Random();
        long id;
        do {
            id = random.nextLong();
        } while (id < 0 || noteMap.containsKey(id));
        return id;
    }


}
