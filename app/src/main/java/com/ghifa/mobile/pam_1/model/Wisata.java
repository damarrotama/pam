package com.ghifa.mobile.pam_1.model;

/**
 * Created by Mobile on 12/15/2017.
 */

public class Wisata {
    String id_wisata        = null;
    String judul            = null;
    String isi              = null;
    String likes            = null;
    String gambar           = null;
    String parent           = null;

    public Wisata(String id_wisata, String judul, String isi, String likes, String gambar, String parent) {
        this.id_wisata = id_wisata;
        this.judul = judul;
        this.isi = isi;
        this.likes = likes;
        this.gambar = gambar;
        this.parent = parent;
    }

    public String getId_wisata() {
        return id_wisata;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    public String getLikes() {
        return likes;
    }

    public String getGambar() {
        return gambar;
    }

    public String getParent() {
        return parent;
    }
}
