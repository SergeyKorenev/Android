package com.aioki.myapplication.DB;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aioki.myapplication.Site.SiteModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String TABLE_SITES = "sites";

    private static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_URL = "url";

    private static final String DB_NAME = "RSS_db";
    private static final int DB_VERSION = 2;


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = String.format("CREATE TABLE %S (%S INTEGER PRIMARY KEY, %S TEXT, %S TEXT)", TABLE_SITES, KEY_ID, KEY_NAME, KEY_URL);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SITES);
        onCreate(db);
    }

    public void addSite(@NotNull SiteModel site){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URL, site.getUrl());
        values.put(KEY_NAME, site.getName());

        db.insert(TABLE_SITES, null, values);
        db.close();
    }

    public SiteModel getSite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        SiteModel s = null;

        try (Cursor cursor = db.query(TABLE_SITES, new String[]{KEY_ID,
                        KEY_NAME, KEY_URL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null)) {

            assert cursor != null;
            cursor.moveToFirst();
            s = new SiteModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0));
        } catch (CursorIndexOutOfBoundsException e) {
            s = new SiteModel("Error", "Error");
        }
        return s;
    }

    public List<SiteModel> getAllSites() {
        List<SiteModel> sites = new ArrayList<>();

        String select = "SELECT * FROM " + TABLE_SITES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            do {
                SiteModel s = new SiteModel(c.getString(1), c.getString(2), c.getInt(0));
                sites.add(s);
            } while (c.moveToNext());
        }
        c.close();
        return sites;
    }

    public int updateSite(@NonNull SiteModel site) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, site.getName());
        values.put(KEY_URL, site.getUrl());

        return db.update(TABLE_SITES, values, KEY_ID + " =?", new String[]{String.valueOf(site.getId())});
    }

    public void deleteSite(SiteModel site) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SITES, KEY_ID + " =?", new String[]{String.valueOf(site.getId())});
        db.close();
    }

    public void deleteSite(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SITES, KEY_ID + " =?", new String[]{String.valueOf(i)});
        db.close();
    }


    public int getSitesCount() {
        String count = "SELECT * FROM " + TABLE_SITES;
        Cursor c = this.getReadableDatabase().rawQuery(count, null);
        final int x = c.getCount();
        c.close();
        return x;
    }

}

