
package com.training.crudmakanan.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelMakanan {

    @SerializedName("DataMakanan")
    @Expose
    private List<DataMakanan> dataMakanan = null;

    public List<DataMakanan> getDataMakanan() {
        return dataMakanan;
    }

    public void setDataMakanan(List<DataMakanan> dataMakanan) {
        this.dataMakanan = dataMakanan;
    }

}
