package com.aytekincomez.aktueluygulamasi.Model;

public class Aktuel {
    private int id;
    private String isim;
    private String marka_logo;
    private String aktuel_resim;
    private String tarih;

    public Aktuel() {
    }

    public Aktuel(int id, String isim, String marka_logo,String aktuel_resim, String tarih) {
        this.id = id;
        this.isim = isim;
        this.marka_logo = marka_logo;
        this.tarih = tarih;
        this.aktuel_resim = aktuel_resim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getMarka_logo() {
        return marka_logo;
    }

    public void setMarka_logo(String marka_logo) {
        this.marka_logo = marka_logo;
    }
    public String getAktuel_resim(){
        return aktuel_resim;
    }

    public void setAktuel_resim(String aktuel_resim){
        this.aktuel_resim = aktuel_resim;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
