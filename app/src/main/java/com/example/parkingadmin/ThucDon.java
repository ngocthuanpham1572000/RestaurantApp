package com.example.parkingadmin;

public class ThucDon {
    public  static  final  String  TABLE_NAME = "THUC_DON";
    public  static  final  String COL_ID = "ID";
    public  static  final  String COL_MAMON = "MA_MON";
    public static final String COL_TENMON="TEN_MON";
    public  static  final  String COL_MABAN = "MA_BAN";
    public  static  final  String COL_SOLUONG = "SO_LUONG";
    public  static  final  String COL_DONGIA = "DON_GIA";
    public  static  final  String COL_GIATIEN = "GIA_TIEN";
    public  static  final  String COL_TRANGTHAI = "TRANG_THAI";

    public  static  final  String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " ( " +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_MAMON + " INTEGER, " +
            COL_TENMON + " TEXT, "+
            COL_MABAN + " INTEGER, " +
            COL_SOLUONG + " INTEGER, " +
            COL_DONGIA + " REAL, " +
            COL_GIATIEN + " REAL, " +
            COL_TRANGTHAI + " INTEGER )";

    public  static  final  String XOA_BANG = "DROP TABLE IF EXIST " + TABLE_NAME;


    private int id;
    private  int mamon;

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    private String tenmon;
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

    public ThucDon(int id, int mamon,String tenmon, int maban, int soluong, double gia,double tong, int trangthai){
     super();
     this.setId(id);
     this.mamon = mamon;
     this.tenmon=tenmon;
     this.maban = maban;
     this.soluong = soluong;
     this.dongia=gia;
     this.giatien = tong;
     this.trangthai = trangthai;
    }
    public ThucDon(int mamon,String tenmon, int maban, int soluong, double gia){
     super();
     this.setId(0);
     this.mamon = mamon;
     this.tenmon=tenmon;
     this.maban = maban;
     this.soluong = soluong;
     this.dongia = gia;
     this.giatien=gia;
     this.trangthai = 1;
    }
}
