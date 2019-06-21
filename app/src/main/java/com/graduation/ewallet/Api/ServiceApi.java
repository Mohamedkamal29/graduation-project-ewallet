package com.graduation.ewallet.Api;




import com.graduation.ewallet.Constant;
import com.graduation.ewallet.Model.Auth.RegisterResponse;
import com.graduation.ewallet.Model.Base.BaseResponse;
import com.graduation.ewallet.Model.ConfirmSendMonyRespons;
import com.graduation.ewallet.Network.Urls;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ServiceApi {



    @POST(Urls.Register)
    Call<RegisterResponse> register(

            @Query(Constant.Service.Name) String name,
            @Query(Constant.Service.PHONE) String phone,
            @Query(Constant.Service.EMAIL) String emil,
            @Query(Constant.Service.PASSWORD) String password,
            @Query(Constant.Service.JOP) String jop,
            @Query(Constant.Service.PIN) String pin,
            @Query(Constant.Service.DEVICE_ID) String device_id
    );


    @POST(Urls.LogIn)
    Call<RegisterResponse> logIn(


            @Query(Constant.Service.EMAIL) String emil,
            @Query(Constant.Service.PASSWORD) String password,
            @Query(Constant.Service.DEVICE_ID) String device_id
    );


    @GET(Urls.Wallet)
    Call<ConfirmSendMonyRespons> confirmSend(

            @Query(Constant.Service.QR) String qr,
            @Header(Constant.Service.Token) String Authorization

    );


    @POST(Urls.TRANSACTION)
    Call<BaseResponse> sendTransAction(

            @Query(Constant.Service.receiver_qr) String receiver_qr,
            @Query(Constant.Service.PIN) String pin,
            @Query(Constant.Service.cash) String qr,

            @Header(Constant.Service.Token) String Authorization

    );








}
