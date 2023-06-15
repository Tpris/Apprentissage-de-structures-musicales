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

    public void addDurationToLastNote(int duration){
        notes.get(notes.size()-1).addTime(duration);
    }

    public void addNote(Note note){
//        if(notes.size()>0) {
//            Note last = notes.get(notes.size() - 1);
//            if (last.equals(note)) last.addTime(note.getDuration());
//            else notes.add(note);
//        }
//        else
            notes.add(note);
    }

    @Override
    public String toString() {
        return "Music : " + musicName + "\nNotes : \n" + notes;
    }
}
