package Extractor;

import java.util.ArrayList;
import java.util.List;

public class Music {
    private ArrayList<Note> notes;
    private String musicName;

    public Music(String musicName) {
        this.musicName = musicName;
        this.notes = new ArrayList<>();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String getMusicName() {
        return musicName;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    @Override
    public String toString() {
        return "Music : " + musicName;
    }
}
