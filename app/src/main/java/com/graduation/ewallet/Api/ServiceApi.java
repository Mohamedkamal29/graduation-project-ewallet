package com.graduation.ewallet.Api;




import com.graduation.ewallet.Constant;
import com.graduation.ewallet.Model.Auth.RegisterResponse;
import com.graduation.ewallet.Model.Base.BaseResponse;
import com.graduation.ewallet.Model.BusinessCard.BusinessCardModel;
import com.graduation.ewallet.Model.ConfirmSendMonyRespons;
import com.graduation.ewallet.Model.HistoryTransAction.HistoryResponse;
import com.graduation.ewallet.Model.Identificationaninfo.IdentityModel;
import com.graduation.ewallet.Model.ItemRespons;
import com.graduation.ewallet.Model.StorItemResponse;
import com.graduation.ewallet.Model.WallerQrResponse;
import com.graduation.ewallet.Network.Urls;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @GET(Urls.account)
    Call<WallerQrResponse>  getWalletQr(
            @Header(Constant.Service.Token) String Authorization
    );


    @GET(Urls.transactions)
    Call<HistoryResponse>  transactions(
            @Header(Constant.Service.Token) String Authorization
    );

    @GET(Urls.items)
    Call<ItemRespons>  items(
            @Header(Constant.Service.Token) String Authorization
    );

    @GET(Urls.usersContact)
    Call<BusinessCardModel>  getUsersBusinessCards(
            @Header(Constant.Service.Token) String Authorization
    );

    @FormUrlEncoded
    @POST(Urls.updateUserContact)
    Call<BusinessCardModel> updateContact(
            @Header(Constant.Service.Token) String Authorization,
            @Field("name") String name,
            @Field("job") String job,
            @Field("phone") String phone,
            @Field("email") String email
    );


    @GET(Urls.identity)
    Call<IdentityModel> getIdentityInformation(
            @Header(Constant.Service.Token) String  Authorization
    );


    @Multipart
    @POST(Urls.stor)
    Call<StorItemResponse> updateImage(

            @Query(Constant.Service.item_name) String item_name,
            @Query(Constant.Service.item_price) String item_price,
            @Query(Constant.Service.quantity) String quantity,
            @Query(Constant.Service.item_link) String item_link ,
            @Query(Constant.Service.item_description) String item_description,
            @Part MultipartBody.Part item_image
    );


}
