package com.coreanotito.estagio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table Despesas (id INTEGER PRIMARY KEY AUTOINCREMENT, mes TEXT, receita TEXT, despesa TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {

        DB.execSQL("drop Table if exists Despesas");
    }

    public Boolean insertdespesa(String mes, Integer receita, Integer despesa)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mes", mes);
        contentValues.put("receita", receita);
        contentValues.put("despesa", despesa);
        long result=DB.insert("Despesas", null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatedespesa(String mes, String receita, String despesa)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mes", mes);
        contentValues.put("receita", receita);
        contentValues.put("despesa", despesa);
        Cursor cursor = DB.rawQuery("select * from Despesas where mes = ?", new String[] {mes});
        if (cursor.getCount()>0){

        long result=DB.update("Despesas", contentValues, "mes=?", new String[] {mes});
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }else {
            return false;
        }
    }



    public Boolean deletedespesa(String mes)
    {
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from Despesas where mes = ?", new String[] {mes});
        if (cursor.getCount()>0){

            long result=DB.delete("Despesas","mes=?", new String[] {mes});
            if (result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }
    }

    public Cursor getdespesa ()
    {
        SQLiteDatabase DB= this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from Despesas", null);
       return cursor;
    }
}
