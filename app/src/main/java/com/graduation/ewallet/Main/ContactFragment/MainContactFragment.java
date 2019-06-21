package com.graduation.ewallet.Main.ContactFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.BusinessCard.BusinessCardModel;
import com.graduation.ewallet.Model.BusinessCard.Contact;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class MainContactFragment extends Fragment {

    SharedPrefManger sharedPrefManger;
    List<Contact> contactList = new ArrayList<>();
    ContactVerticalPagerAdapter adapter;
    VerticalInfiniteCycleViewPager viewPager;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_contact_fragment, container, false);
        context = getContext();
        sharedPrefManger = new SharedPrefManger(context);
        getUsersBusinessCards();
        viewPager = view.findViewById(R.id.VerticalViewPager);

        return view;
    }

    void getUsersBusinessCards(){
        RetroWeb.getClient().create(ServiceApi.class)
                .getUsersBusinessCards(Urls.Bearer+sharedPrefManger.getUserData().getToken())
                .enqueue(new Callback<BusinessCardModel>() {
                    @Override
                    public void onResponse(Call<BusinessCardModel> call, Response<BusinessCardModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isStatus()){
                                contactList = response.body().getData().getContacts();
                                adapter = new ContactVerticalPagerAdapter(context, contactList);
                                adapter.notifyDataSetChanged();
                                viewPager.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BusinessCardModel> call, Throwable t) {
                        Log.e(">>>>>>>>>", t.getMessage());
                    }
                });
    }
}
