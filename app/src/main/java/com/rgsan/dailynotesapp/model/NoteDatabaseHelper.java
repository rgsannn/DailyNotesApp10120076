package com.rgsan.dailynotesapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class NoteDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "note_database";
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID_CATATAN = "id_catatan";
    private static final String COLUMN_JUDUL = "judul";
    private static final String COLUMN_KATEGORI = "kategori";
    private static final String COLUMN_CATATAN = "catatan";
    private static final String COLUMN_TANGGAL = "tanggal";

    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID_CATATAN + " TEXT PRIMARY KEY, " +
                COLUMN_JUDUL + " TEXT, " +
                COLUMN_KATEGORI + " TEXT, " +
                COLUMN_CATATAN + " TEXT, " +
                COLUMN_TANGGAL + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void addNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CATATAN, note.getIdCatatan());
        values.put(COLUMN_JUDUL, note.getJudul());
        values.put(COLUMN_KATEGORI, note.getKategori());
        values.put(COLUMN_CATATAN, note.getCatatan());
        values.put(COLUMN_TANGGAL, note.getTanggal().toString());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String id_catatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID_CATATAN));
                String judul = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL));
                String kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI));
                String catatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATATAN));
                String tanggal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL));
                Note note = new Note(id_catatan, judul, kategori, catatan, tanggal);
                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_CATATAN, note.getIdCatatan());
        values.put(COLUMN_JUDUL, note.getJudul());
        values.put(COLUMN_KATEGORI, note.getKategori());
        values.put(COLUMN_CATATAN, note.getCatatan());
        db.update(TABLE_NAME, values, COLUMN_ID_CATATAN + "=?", new String[]{note.getIdCatatan()});
        db.close();
    }

    public void deleteNote(String id_catatan) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID_CATATAN + "=?", new String[]{id_catatan});
        db.close();
    }

    public Note getNoteById(String idCatatan) {
        Note note = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID_CATATAN,
                COLUMN_JUDUL,
                COLUMN_KATEGORI,
                COLUMN_CATATAN,
                COLUMN_TANGGAL
        };

        String selection = COLUMN_ID_CATATAN + " = ?";
        String[] selectionArgs = { idCatatan };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            String judul = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL));
            String kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI));
            String catatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATATAN));
            String tanggal = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL));

            note = new Note(idCatatan, judul, kategori, catatan, tanggal);
        }

        cursor.close();
        db.close();

        return note;
    }


    public List<Note> getNotesByDate(String tanggal) {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID_CATATAN,
                COLUMN_JUDUL,
                COLUMN_KATEGORI,
                COLUMN_CATATAN,
                COLUMN_TANGGAL
        };

        String selection = COLUMN_TANGGAL + " LIKE ?";
        String[] selectionArgs = { tanggal + "%" };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id_catatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID_CATATAN));
                String judul = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JUDUL));
                String kategori = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KATEGORI));
                String catatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATATAN));
                String tanggalCatatan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL));

                Note note = new Note(id_catatan, judul, kategori, catatan, tanggalCatatan);
                notes.add(note);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return notes;
    }

}

