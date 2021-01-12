package com.example.parkingadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.LinkedList;

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
// Thêm mới món ăn
    public long ThemMon(ThucDon thucdon){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Log.d("kiemtra",thucdon.toString());
        ContentValues values = new ContentValues();

        values.put(ThucDon.COL_MAMON, thucdon.getMamon());
        values.put(ThucDon.COL_TENMON,thucdon.getTenmon());
        values.put(ThucDon.COL_MABAN, thucdon.getMaban());
        values.put(ThucDon.COL_SOLUONG, thucdon.getSoluong());
        values.put(ThucDon.COL_DONGIA,thucdon.getDongia());
        values.put(ThucDon.COL_GIATIEN, thucdon.getDongia());
        values.put(ThucDon.COL_TRANGTHAI,thucdon.getTrangthai());

        long insert = sqLiteDatabase.insert(ThucDon.TABLE_NAME,null,values);
        return insert;
    }
    //Xoá  danh sách theo bàn sau khi thanh toán
    public int xoaDSsaoThanhToan(int maBan) {
        String ma=String.valueOf(maBan);
        // DELETE FROM ThucDon WHERE MaBan = ?
        // WHERE
        String selection = ThucDon.COL_MABAN + " = ?";
        String[] selectionArgs = {ma};
        // Thực thi truy vấn
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(
                ThucDon.TABLE_NAME,  // Tên bảng
                selection,          // Mệnh đề WHERE
                selectionArgs       // Cac gia tri trong menh de WHERE
        );

        return deletedRows; // Số dòng bị xóa
    }

    //Lấy danh sách giỏ hàng theo mã bàn
    public LinkedList DSgiohangtheoma(int maBan){
        SQLiteDatabase db=this.getReadableDatabase();
        String ma=String.valueOf(maBan);
        //SELECT *
        String[] projection = {
                ThucDon.COL_ID,
                ThucDon.COL_MABAN,
                ThucDon.COL_MAMON,
                ThucDon.COL_TENMON,
                ThucDon.COL_SOLUONG,
                ThucDon.COL_DONGIA,
                ThucDon.COL_GIATIEN,
                ThucDon.COL_TRANGTHAI
        };

        // WHERE mssv = ?
        String selection = ThucDon.COL_MABAN + " = ?";
        String[] selectionArgs = { ma };

        // ORDER BY MSSV ASC
        String sortOrder = ThucDon.COL_TRANGTHAI + " ASC";

        // Thực thi truy vấn
        Cursor cursor = db.query(
                ThucDon.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        // Xử lý kết quả trả về
        LinkedList<ThucDon> thucDons = new LinkedList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ThucDon.COL_ID));
            int mamon=cursor.getInt(cursor.getColumnIndexOrThrow(ThucDon.COL_MAMON));
            String tenmon=cursor.getString(cursor.getColumnIndexOrThrow(ThucDon.COL_TENMON));
            int maban=cursor.getInt(cursor.getColumnIndexOrThrow(ThucDon.COL_MABAN));
            int soluong=cursor.getInt(cursor.getColumnIndexOrThrow(ThucDon.COL_SOLUONG));
            double dongia=Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(ThucDon.COL_DONGIA)));
            double gia =Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(ThucDon.COL_GIATIEN)));
            int trangthai=cursor.getInt(cursor.getColumnIndexOrThrow(ThucDon.COL_TRANGTHAI));


            ThucDon thucDon = new ThucDon(id,mamon,tenmon,maban,soluong,dongia,gia,trangthai);

            thucDons.add(thucDon);
        }
        cursor.close();
        return thucDons;
    }
    public long TangSoLuong(ThucDon thucDon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        int soluong = thucDon.getSoluong() + 1;
        double giatien = thucDon.getGiatien() + thucDon.getDongia();

        values.put(ThucDon.COL_SOLUONG,soluong);
        values.put(ThucDon.COL_GIATIEN,giatien);

        String select = ThucDon.COL_ID + " = ?";
        String[] selectArgs = {Integer.toString(thucDon.getId())};

        long updateon = db.update(
                ThucDon.TABLE_NAME,
                values,
                select,
                selectArgs
        );
        return updateon;
    }

    public long GiamSoLuong(ThucDon thucDon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(thucDon.getSoluong()  == 1){
            return 0;
        }else{
            int soluong = thucDon.getSoluong() - 1;
            double giatien = thucDon.getGiatien() - thucDon.getDongia();

            values.put(ThucDon.COL_SOLUONG,soluong);
            values.put(ThucDon.COL_GIATIEN,giatien);

            String select = ThucDon.COL_ID + " = ?";
            String[] selectArgs = {Integer.toString(thucDon.getId())};

            long updatedown = db.update(
                    ThucDon.TABLE_NAME,
                    values,
                    select,
                    selectArgs
            );
            return updatedown;
        }
    }

    public double TongTien(int idban){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] pos = {

                ThucDon.COL_ID,

                ThucDon.COL_MABAN,

                ThucDon.COL_GIATIEN,


        };

        String select = ThucDon.COL_MABAN + " = ?";
        String[] selectArgs = {String.valueOf(idban)};

        Cursor cursor = db.query(
                ThucDon.TABLE_NAME,
                pos,
                select,
                selectArgs,
                null,
                null,
                null
        );

        double tongtien = 0;
        while(cursor.moveToNext()){
            double GiaTien = cursor.getDouble(cursor.getColumnIndexOrThrow(ThucDon.COL_GIATIEN));
            tongtien += GiaTien;
        }

        return  tongtien;


    }

    public  int XoaMonAn(ThucDon thucdon){

        String select = ThucDon.COL_ID + " like ?";
        String[] valueselect = {String.valueOf(thucdon.getId())};
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(
                ThucDon.TABLE_NAME,
                select,
                valueselect
        );
        return  delete;
    }
    public long GoiMonAn(int maban){
       /* SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ThucDon.COL_TRANGTHAI, 0);

        String selection = ThucDon.COL_MABAN + " = ?";
        String[] selectvalue = {String.valueOf(maban)};

        int update = db.update(
                ThucDon.TABLE_NAME,
                values,
                selection,        // ma ban
                selectvalue// ma ban vưa chon
        );
        return update;*/
        long kq=0;
        LinkedList<ThucDon> td=DSgiohangtheoma(maban);
        for(int i=0;i<td.size();i++)
        {
            if(td.get(i).getTrangthai()==1)
              kq= SoSanhTrung(td.get(i),maban);
        }
        return kq;
    }
    public long SoSanhTrung(ThucDon td,int maban)
    {
        LinkedList<ThucDon> thucdon=DSgiohangtheoma(maban);
        for(int i=0;i<thucdon.size();i++) {
            if (thucdon.get(i).getTrangthai() == 0&&thucdon.get(i).getMamon() == td.getMamon()) {
                    long a= TangSoLuong(thucdon.get(i).getId(), td.getSoluong(), thucdon.get(i).getSoluong(), thucdon.get(i).getDongia());
                   XoaMonAn(td);
                   return a;
            }
        }

        return GoiMonAntheoMa(td.getId());


    }
    public long TangSoLuong(int id,int Soluong,int Soluongcu,double gia)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        int soluong = Soluongcu + Soluong;
        double giatien =  gia*soluong;

        values.put(ThucDon.COL_SOLUONG,soluong);
        values.put(ThucDon.COL_GIATIEN,giatien);

        String select = ThucDon.COL_ID + " = ?";
        String[] selectArgs = {Integer.toString(id)};

        long updateon = db.update(
                ThucDon.TABLE_NAME,
                values,
                select,
                selectArgs
        );
        return updateon;
    }
    public long GoiMonAntheoMa(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ThucDon.COL_TRANGTHAI, 0);

        String selection = ThucDon.COL_ID + " = ?";
        String[] selectvalue = {String.valueOf(id)};

        long update = db.update(
                ThucDon.TABLE_NAME,
                values,
                selection,
                selectvalue
        );
        return update;
    }
    public boolean KiemTraGoiMon(int maban)
    {
        LinkedList<ThucDon> thucDons;
        thucDons=DSgiohangtheoma(maban);
        for (int i=0;i<thucDons.size();i++)
        {
            if(thucDons.get(i).getTrangthai()==1)
                return false;
        }
        return true;
    }


}
