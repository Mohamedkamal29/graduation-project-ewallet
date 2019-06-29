package com.graduation.ewallet.Main.PropertyFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Main.HomeFragment.MainHomeFragment;
import com.graduation.ewallet.Main.TransactionHistroy.TransactionHistoryActivity;
import com.graduation.ewallet.Main.TransactionHistroy.TransactionHistoryAdapter;
import com.graduation.ewallet.Model.HistoryTransAction.HistoryResponse;
import com.graduation.ewallet.Model.HistoryTransAction.Sent;
import com.graduation.ewallet.Model.ItemModel;
import com.graduation.ewallet.Model.ItemRespons;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPropertyFragment extends Fragment {

    RecyclerView rcContainer;
    PropertyAdapter adapter;
    List<ItemModel> sents;
    Button btnAdd,btnEdit;

    private SharedPrefManger mSharedPrefManager;

    LinearLayout loader ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_property, container, false);
        mSharedPrefManager = new SharedPrefManger(getContext());
        loader=view.findViewById(R.id.loader);
        sents=new ArrayList<>();
        rcContainer = view.findViewById(R.id.Container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcContainer.setLayoutManager(linearLayoutManager);
        adapter = new PropertyAdapter(getContext(),sents);
        rcContainer.setAdapter(adapter);

        receved();
        return view;
    }



    private void  receved(){
        loader.setVisibility(View.VISIBLE);
        RetroWeb.getClient()
                .create(ServiceApi.class)
                .items( Urls.Bearer + mSharedPrefManager.getUserData().getToken())
                .enqueue(new Callback<ItemRespons>() {
                    @Override
                    public void onResponse(Call<ItemRespons> call, Response<ItemRespons> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isStatus()) {
                                loader.setVisibility(View.GONE);

                                        sents.clear();
                                        sents.addAll(response.body().getData().getItem());





                                }
                                adapter.notifyDataSetChanged();

                                rcContainer.setAdapter(adapter);
                            }
                        }


                    @Override
                    public void onFailure(Call<ItemRespons> call, Throwable t) {
                        Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();
                        loader.setVisibility(View.GONE);
                    }
                });
    }


}
