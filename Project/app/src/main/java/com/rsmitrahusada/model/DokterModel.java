package com.rsmitrahusada.model;

public class DokterModel {

    String nama, spesialis, jadwal, surl;

    DokterModel()
    {

    }
    public DokterModel(String nama, String spesialis, String jadwal, String surl) {
        this.nama = nama;
        this.spesialis = spesialis;
        this.jadwal = jadwal;
        this.surl = surl;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
