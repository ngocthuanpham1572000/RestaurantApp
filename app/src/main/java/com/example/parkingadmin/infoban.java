package com.example.parkingadmin;

public class infoban {
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private String tenban;
    private int songuoi;
    private int soghe;
    private int TrangThai;
    private String ViTri;

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public infoban(int id,String vitri, String ten, int songuoi, int soghe , int trangthai)
    {
        this.Id=id;
        this.ViTri=vitri;
        this.setTenban(ten);
        this.setSonguoi(songuoi);
        this.setSoghe(soghe);
        this.TrangThai=trangthai;

    }



    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public int getSonguoi() {
        return songuoi;
    }

    public void setSonguoi(int songuoi) {
        this.songuoi = songuoi;
    }

    public int getSoghe() {
        return soghe;
    }

    public void setSoghe(int soghe) {
        this.soghe = soghe;
    }
}
