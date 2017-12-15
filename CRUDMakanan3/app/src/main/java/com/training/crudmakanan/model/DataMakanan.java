
package com.training.crudmakanan.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataMakanan {

    @SerializedName("makanan")
    @Expose
    private String makanan;
    @SerializedName("id_makanan")
    @Expose
    private String idMakanan;
    @SerializedName("foto_makanan")
    @Expose
    private String fotoMakanan;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jenkel")
    @Expose
    private String jenkel;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    public String getMakanan() {
        return makanan;
    }

    public void setMakanan(String makanan) {
        this.makanan = makanan;
    }

    public String getIdMakanan() {
        return idMakanan;
    }

    public void setIdMakanan(String idMakanan) {
        this.idMakanan = idMakanan;
    }

    public String getFotoMakanan() {
        return fotoMakanan;
    }

    public void setFotoMakanan(String fotoMakanan) {
        this.fotoMakanan = fotoMakanan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenkel() {
        return jenkel;
    }

    public void setJenkel(String jenkel) {
        this.jenkel = jenkel;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
