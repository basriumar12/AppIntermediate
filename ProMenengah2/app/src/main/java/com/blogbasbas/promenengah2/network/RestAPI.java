package com.blogbasbas.promenengah2.network;


import com.blogbasbas.promenengah2.model.ModelMakanan;
import com.blogbasbas.promenengah2.model.ModelUser;
import com.blogbasbas.promenengah2.model.Respon;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Server on 29/08/2017.
 */

public interface RestAPI {
    @FormUrlEncoded
    @POST("loginuser.php/")
    Call<ModelUser> loginuser(
            @Field("edtusername") String vsusrname,
            @Field("edtpassword") String vspassword,
            @Field("vslevel")String vslevel
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
