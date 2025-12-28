package com.example.dev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {

    private static final String VERITABANI_ADI = "AngolaDB";
    private static final int SURUM = 1;

    public VeritabaniYardimcisi(Context context) {
        super(context, VERITABANI_ADI, null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE kullanicilar (id INTEGER PRIMARY KEY AUTOINCREMENT, kadi TEXT, sifre TEXT)");
        db.execSQL("CREATE TABLE sehirler (id INTEGER PRIMARY KEY AUTOINCREMENT, sehir_adi TEXT, nufus TEXT, aciklama TEXT)");
        db.execSQL("INSERT INTO kullanicilar (kadi, sifre) VALUES ('admin@email.com', '1234')");
        db.execSQL("INSERT INTO kullanicilar (kadi, sifre) VALUES ('selim@test.com', '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kullanicilar");
        db.execSQL("DROP TABLE IF EXISTS sehirler");
        onCreate(db);
    }

    public boolean girisKontrol(String email, String sifre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM kullanicilar WHERE kadi=? AND sifre=?", new String[]{email, sifre});
        boolean sonuc = cursor.getCount() > 0;
        cursor.close();
        return sonuc;
    }

    public boolean kullaniciEkle(String email, String sifre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("kadi", email);
        v.put("sifre", sifre);
        return db.insert("kullanicilar", null, v) != -1;
    }

    public boolean kullaniciSil(String email, String sifre) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("kullanicilar", "kadi=? AND sifre=?", new String[]{email, sifre}) > 0;
    }

    public boolean kullaniciGuncelle(String eskiEmail, String yeniEmail, String yeniSifre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("kadi", yeniEmail);
        v.put("sifre", yeniSifre);
        return db.update("kullanicilar", v, "kadi=?", new String[]{eskiEmail}) > 0;
    }

    public boolean sehirEkle(String sehirAdi, String nufus, String aciklama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("sehir_adi", sehirAdi);
        v.put("nufus", nufus);
        v.put("aciklama", aciklama);
        return db.insert("sehirler", null, v) != -1;
    }

    public Cursor tumSehirleriGetir() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM sehirler", null);
    }

    public Integer sehirSil(String sehirAdi) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("sehirler", "sehir_adi = ?", new String[]{sehirAdi});
    }

    public boolean sehirGuncelle(String sehirAdi, String yeniNufus, String yeniAciklama) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("nufus", yeniNufus);
        v.put("aciklama", yeniAciklama);
        return db.update("sehirler", v, "sehir_adi = ?", new String[]{sehirAdi}) > 0;
    }
}