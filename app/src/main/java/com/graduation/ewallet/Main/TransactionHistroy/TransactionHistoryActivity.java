package com.graduation.ewallet.Main.TransactionHistroy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Model.Auth.RegisterModel;
import com.graduation.ewallet.Model.HistoryTransAction.HistoryResponse;
import com.graduation.ewallet.Model.HistoryTransAction.Sent;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryActivity extends AppCompatActivity {

    Button btnSent, btnReceived;
    TextView tvTotal;
    RecyclerView container;
    TransactionHistoryAdapter adapter;
    String tag;
    List<Sent> sents;
    final String SENT="sent";
    final String RECEVED="received";

    LinearLayout loader ;

    private SharedPrefManger mSharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        sents=new ArrayList<>();
        tag=SENT;
        mSharedPrefManager = new SharedPrefManger(this);
        container = findViewById(R.id.Container);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        container.setLayoutManager(linearLayoutManager);
        adapter = new TransactionHistoryAdapter(this, tag,sents);
        container.setAdapter(adapter);
        btnSent = findViewById(R.id.btnSent);

        tvTotal = findViewById(R.id.tvTotal);

        loader=findViewById(R.id.loader);




        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTransactionType(v);
            }
        });
        btnReceived = findViewById(R.id.btnReceived);
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTransactionType(v);
            }
        });



        btnSent.performClick();

    }





    void switchTransactionType(View v){

        btnSent.setTextColor(getColor(R.color.white));
        btnSent.setBackgroundResource(R.color.transparent);
        btnReceived.setTextColor(getColor(R.color.white));
        btnReceived.setBackgroundResource(R.color.transparent);

        switch (v.getId()) {
            case R.id.btnSent:
                btnSent.setTextColor(getColor(R.color.colorAccent));
                btnSent.setBackgroundResource(R.drawable.round_left_choice_selected);
                tag = SENT;

                setRecycler();
                break;
            case R.id.btnReceived:
                btnReceived.setTextColor(getColor(R.color.colorPrimary));
                btnReceived.setBackgroundResource(R.drawable.round_right_choice_selected);
                tag = RECEVED;

                setRecycler();
                break;
        }
    }

    void setRecycler(){
        receved(tag);
    }





   private void  receved(final String tag){
            loader.setVisibility(View.VISIBLE);
            RetroWeb.getClient()
                    .create(ServiceApi.class)
                    .transactions( Urls.Bearer + mSharedPrefManager.getUserData().getToken())
                    .enqueue(new Callback<HistoryResponse>() {
                        @Override
                        public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body().isStatus()) {
                                    loader.setVisibility(View.GONE);
                                    switch (tag){
                                        case SENT :
                                            adapter.notifyDataSetChanged();

                                            sents.clear();
                                            sents.addAll(response.body().getData().getSend());
                                            tvTotal.setText("Total Sent  :  "+response.body().getData().getTotal_send_amount());

                                            Log.e("tag",tag+"");
                                            Log.e("tag",response.body().getData().getTotal_send_amount()+"");

                                            //   adapter.addAllSent(sents);

                                            break;
                                            case  RECEVED :
                                                adapter.notifyDataSetChanged();

                                                sents.clear();
                                                sents.addAll(response.body().getData().getReceiver());
                                                tvTotal.setText("Total Receved  :  "+response.body().getData().getTotal_receive_amount());

                                                Log.e("tag",tag+"");
                                                Log.e("tag",response.body().getData()
                                                        .getTotal_receive_amount()+"");
                                                break;

                                    }
                                    adapter.notifyDataSetChanged();

                                    container.setAdapter(adapter);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<HistoryResponse> call, Throwable t) {
                            Toast.makeText(TransactionHistoryActivity.this, t + "", Toast.LENGTH_SHORT).show();
                            loader.setVisibility(View.GONE);
                        }
                    });
        }


}
