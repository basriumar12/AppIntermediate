package com.training.crudmakanan.network;

import com.training.crudmakanan.model.ModelMakanan;
import com.training.crudmakanan.model.ModelUser;
import com.training.crudmakanan.model.Respon;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Blackswan on 8/29/2017.
 */

public interface RestAPI {

    @FormUrlEncoded
    @POST("loginuser.php/")
    Call<ModelUser> loginuser(
            @Field("edtusername") String vsusername,
            @Field("edtpassword") String vspassword,
            @Field("vslevel") String vslevel
    );
    @FormUrlEncoded
    @POST("getdatamakanan.php/")
    Call<ModelMakanan> getmakanan(
            @Field("vsiduser") String iduser
    );

    @FormUrlEncoded
    @POST("deletedatamakanan.php/")
    Call<Respon> deletedata(
            @Field("vsidmakanan") String idmakanan
    );

}
