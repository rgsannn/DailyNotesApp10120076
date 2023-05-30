package com.rgsan.dailynotesapp.model;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class Note {
    private String id_catatan;
    private String judul;
    private String kategori;
    private String catatan;
    private String tanggal;

    public Note() {
    }

    public Note(String id_catatan, String judul, String kategori, String catatan, String tanggal) {
        this.id_catatan = id_catatan;
        this.judul = judul;
        this.kategori = kategori;
        this.catatan = catatan;
        this.tanggal = tanggal;
    }

    public String getIdCatatan() {
        return id_catatan;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getTanggal() {
        return tanggal;
    }
}



