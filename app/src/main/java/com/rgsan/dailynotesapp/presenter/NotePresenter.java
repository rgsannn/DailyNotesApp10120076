package com.rgsan.dailynotesapp.presenter;

import android.content.Context;

import com.rgsan.dailynotesapp.model.Note;
import com.rgsan.dailynotesapp.model.NoteDatabaseHelper;

import java.util.List;
import java.util.Random;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class NotePresenter {
    private View view;
    private Context mContext;
    private NoteDatabaseHelper databaseHelper;

    public NotePresenter(View view, Context context) {
        this.view = view;
        mContext = context;
        databaseHelper = new NoteDatabaseHelper(mContext);
    }

    public void loadNotes() {
        List<Note> notes = databaseHelper.getAllNotes();
        view.showNotes(notes);
    }

    public void addNote(Note note) {
        databaseHelper.addNote(note);
        loadNotes();
    }

    public void getNoteById(String idCatatan) {
        Note note = databaseHelper.getNoteById(idCatatan);
        if (note != null) {
            view.showNoteDetail(note);
        } else {
            view.showNoteNotFound();
        }
    }

    public void updateNoteById(String idCatatan, String judul, String kategori, String catatan) {
        Note note = new Note(idCatatan, judul, kategori, catatan, null);
        databaseHelper.updateNote(note);
    }

    public void loadNotesByDate(String tanggal) {
        List<Note> notes = databaseHelper.getNotesByDate(tanggal);
        view.showNotes(notes);
    }

    public void deleteNoteById(String id_catatan) {
        databaseHelper.deleteNote(id_catatan);
    }

    public interface View {
        void showNotes(List<Note> notes);

        void showNoteDetail(Note note);

        void showNoteNotFound();
    }

    public String generateIdCatatan() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(12);
        Random random = new Random();

        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}

