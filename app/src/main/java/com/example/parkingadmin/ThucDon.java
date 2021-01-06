package com.example.parkingadmin;

public class ThucDon {
    public  static  final  String  TABLE_NAME = "THUC_DON";
    public  static  final  String COL_ID = "ID";
    public  static  final  String COL_MAMON = "MA_MON";
    public  static  final  String COL_MABAN = "MA_BAN";
    public  static  final  String COL_SOLUONG = "SO_LUONG";
    public  static  final  String COL_DONGIA = "DON_GIA";
    public  static  final  String COL_GIATIEN = "GIA_TIEN";
    public  static  final  String COL_TRANGTHAI = "TRANG_THAI";

    public  static  final  String CREATE_TABLE = "Create Table "+ TABLE_NAME + " ( " +
            COL_ID + " Integer primary key autoincrement," +
            COL_MAMON + "Integer, " +
            COL_SOLUONG + " Integer " +
            COL_GIATIEN + " Real " +
            COL_DONGIA + " Read" +
            COL_TRANGTHAI + "Integer )";

    public  static  final  String XOA_BANG = "Drop Table if Exist " + TABLE_NAME;


    private int id;
    private  int mamon;
    private int maban;
    private  int soluong;
    private  double giatien;
    private double dongia;

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    private  int trangthai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public ThucDon(int id, int mamon, int maban, int soluong, double gia,double tong, int trangthai){
     super();
     this.setId(id);
     this.mamon = mamon;
     this.maban = maban;
     this.soluong = soluong;
     this.dongia=gia;
     this.giatien = tong;
     this.trangthai = trangthai;
    }
    public ThucDon(int mamon, int maban, int soluong, double gia){
     super();
     this.setId(0);
     this.mamon = mamon;
     this.maban = maban;
     this.soluong = soluong;
     this.dongia = gia;
     this.giatien=gia;
     this.trangthai = 1;
    }
}
