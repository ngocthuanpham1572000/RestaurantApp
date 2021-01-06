package com.example.parkingadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ThucDonDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "NHAHANG.db";
    public ThucDonDBHelper(@Nullable Context context) {
        super(context, DB_NAME , null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ThucDon.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ThucDon.XOA_BANG);
        onCreate(db);
    }

    public long ThemMon(ThucDon thucdon){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ThucDon.COL_MAMON, thucdon.getMamon());
        values.put(ThucDon.COL_MABAN, thucdon.getMaban());
        values.put(ThucDon.COL_SOLUONG, thucdon.getSoluong());
        values.put(ThucDon.COL_GIATIEN, thucdon.getGiatien());

        long insert = sqLiteDatabase.insert(ThucDon.TABLE_NAME,null,values);
        return insert;
    }


}
