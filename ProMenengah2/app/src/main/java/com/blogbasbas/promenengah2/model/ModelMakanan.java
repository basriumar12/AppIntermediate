
package com.blogbasbas.promenengah2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ModelMakanan {

    @SerializedName("DataMakanan")
    private List<DataMakanan> mDataMakanan;

    public List<DataMakanan> getDataMakanan() {
        return mDataMakanan;
    }

    public void setDataMakanan(List<DataMakanan> DataMakanan) {
        mDataMakanan = DataMakanan;
    }

}
