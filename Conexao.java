package com.example.compartilhamentodearquivo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {
    private  static int version=1;
    private  static String name="Login_Registro_BaseDados.db";


    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(username TEXT PRIMARY KEY, password TEXT);";
        db.execSQL(str);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        onCreate(db);
    }
    public long CriarUtilizador(String username, String password) {//2
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = db.insert("Utilizador",null,contentValues);
        return result;


    }
    public String ValidarLogin(String username, String password){//3
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Utilizador WHERE username=? AND password=?", new String[]{username,password});
        if (cursor.getCount()>0) {
            return "Ok";

        }
        return "Erro";
    }


}
