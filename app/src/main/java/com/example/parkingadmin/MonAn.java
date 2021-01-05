package com.example.parkingadmin;

public class MonAn {


    public MonAn(int id, String Ten,String gia,String Hinh)
    {
        Id=id;
        TenMon=Ten;
        Gia=gia;
        HinhAnh=Hinh;

    }
    public MonAn(MonAn monAn)
    {
        this.Id=monAn.Id;
        this.TenMon=monAn.TenMon;
        this.Gia=monAn.Gia;
        this.HinhAnh=monAn.HinhAnh;
    }
    public String TenMon;
    public String Gia;

  public String HinhAnh;
  public int Id;






}
