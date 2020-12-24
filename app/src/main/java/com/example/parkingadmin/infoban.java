package com.example.parkingadmin;

public class infoban {
    private String tenban;
    private int songuoi;
    private int soghe;

    public infoban(String ten, int songuoi, int soghe)
    {
        this.setTenban(ten);
        this.setSonguoi(songuoi);
        this.setSoghe(soghe);

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
