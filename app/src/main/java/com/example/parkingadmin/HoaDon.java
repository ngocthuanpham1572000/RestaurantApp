package com.example.parkingadmin;

public class HoaDon {
    public HoaDon(int maban, int manv, double tongtien, String thoigianlap) {
        this.id=0;
        this.maban = maban;
        this.manv = manv;
        this.tongtien = tongtien;
        this.thoigianlap = thoigianlap;
        this.TrangThai=1;
    }

    public HoaDon(int id, int maban, int manv, double tongtien, String thoigianlap, int trangThai) {
        this.id = id;
        this.maban = maban;
        this.manv = manv;
        this.tongtien = tongtien;
        this.thoigianlap = thoigianlap;
        TrangThai = trangThai;
    }

    private int id;
    private int maban;
    private int manv;
    private double tongtien;
    private String thoigianlap;
    private int TrangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

    public String getThoigianlap() {
        return thoigianlap;
    }

    public void setThoigianlap(String thoigianlap) {
        this.thoigianlap = thoigianlap;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}
