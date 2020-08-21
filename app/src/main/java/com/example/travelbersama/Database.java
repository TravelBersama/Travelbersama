package com.example.travelbersama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Register.db";
    public static final String TABLE_NAME = "RegisterUser";
    public static final String COL_1 ="Id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_NAME = "name";
    public static final String TABLE_BOOK = "tb_book";
    public static final String COL_ID_BOOK = "id_book";
    public static final String COL_ASAL = "asal";
    public static final String COL_TUJUAN = "tujuan";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_DEWASA = "dewasa";
    public static final String COL_ANAK = "anak";
    public static final String TABLE_HARGA = "tb_harga";
    public static final String COL_HARGA_DEWASA = "harga_dewasa";
    public static final String COL_HARGA_ANAK = "harga_anak";
    public static final String COL_HARGA_TOTAL = "harga_total";
    private SQLiteDatabase sqLiteDatabase;


    public Database(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqLiteDatabase.execSQL("CREATE TABLE RegisterUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_NAME + " (" + COL_2 + " TEXT PRIMARY KEY, " + COL_3 +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_BOOK + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ASAL + " TEXT, " + COL_TUJUAN + " TEXT" + ", " + COL_TANGGAL + " TEXT, " + COL_DEWASA + " TEXT, "
                + COL_ANAK + " TEXT)");
        db.execSQL("create table " + TABLE_HARGA + " (" + COL_2 + " TEXT, " + COL_ID_BOOK + " INTEGER, " +
                COL_HARGA_DEWASA + " TEXT, " + COL_HARGA_ANAK + " TEXT, " + COL_HARGA_TOTAL +
                " TEXT, FOREIGN KEY(" + COL_2 + ") REFERENCES " + TABLE_NAME
                + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");
        db.execSQL("insert into " + TABLE_NAME + " values ('anang@gmail.com','anang','AnangGM');");
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",user);
        contentValues.put("Password",password);
        long res = db.insert("RegisterUser",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;

    }
}
