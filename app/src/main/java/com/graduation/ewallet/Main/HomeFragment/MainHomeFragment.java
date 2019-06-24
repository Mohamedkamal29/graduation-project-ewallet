package com.graduation.ewallet.Main.HomeFragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.zxing.integration.android.IntentIntegrator;
import com.graduation.ewallet.Api.ServiceApi;
import com.graduation.ewallet.Customiztation.OnSwipeTouchListener;
import com.graduation.ewallet.Glide.RequestBuilder;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.LisnerObserv.Event;
import com.graduation.ewallet.LisnerObserv.EventBalance;
import com.graduation.ewallet.Main.MainActivity;
import com.graduation.ewallet.Main.TransactionHistroy.TransactionHistoryActivity;
import com.graduation.ewallet.Model.Auth.RegisterModel;
import com.graduation.ewallet.Model.WallerQrResponse;
import com.graduation.ewallet.Network.RetroWeb;
import com.graduation.ewallet.Network.Urls;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;
import com.graduation.ewallet.UI.ScannerActivity;
import com.graduation.ewallet.UI.ViewIdentification_Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainHomeFragment extends Fragment  {

    @BindView(R.id.poundTextView)
    TextView poundTextView;
    //    @BindView(R.id.piastreTextView) TextView piastreTextView;
    @BindView(R.id.sendCashButton)
    Button sendCashButton;
    @BindView(R.id.receiveCashButton)
    Button receiveCashButton;
    @BindView(R.id.BusinessCardMainLinear)
    LinearLayout BusinessCardMainLinear;
    @BindView(R.id.BalanceMainLinear)
    LinearLayout BalanceMainLinear;
    @BindView(R.id.IdentificationMainLinear)
    LinearLayout IdentificationMainLinear;
    @BindView(R.id.BusinessCardLinear)
    LinearLayout BusinessCardLinear;
    @BindView(R.id.BalanceLinear)
    LinearLayout BalanceLinear;
    @BindView(R.id.IdentificationLinear)
    LinearLayout IdentificationLinear;

    ProgressBar progressBar;

    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_jop)
    TextView tvJop;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btnEditBusinessCard)
    Button editBusinessCardButton;
    @BindView(R.id.btnShareBusinessCard)
    Button shareBusinessCardButton;
    @BindView(R.id.person)
            ImageView ivPerson;
    @BindView(R.id.tvName)
            TextView tvName;
    @BindView(R.id.tvAddress)
            TextView tvAddress;
    @BindView(R.id.tvSSID)
            TextView tvSSID;

    TextView confirmDialogTextView;
    Dialog dialog;
    EditText poundDialogEditText, piastreDialogEditText, pinDialogEditText;
    LinearLayout amountLayout, pinLayout;
    ImageView cashQR;

    public static String cash;
    public static String Pin;

    private SharedPrefManger mSharedPrefManager;
    public  static Event e = new Event();

    View MainHomeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainHomeFragment = inflater.inflate(R.layout.main_home_fragment, container, false);
        MainHomeFragment.setTag(1);
        mSharedPrefManager = new SharedPrefManger(getContext());
        ButterKnife.bind(this, MainHomeFragment);
        initialize();
        Swipe();
        //  Validation();

        return MainHomeFragment;
    }

    private void initialize() {



        e.setOnEventListener(new EventBalance() {
            public void eventBalance(String er) {
                // do your work.
                poundTextView.setText(er);
            }
        });

        tvEmail.setText(mSharedPrefManager.getUserData().getEmail());
        tvJop.setText(mSharedPrefManager.getUserData().getJob());
        tvPhone.setText(mSharedPrefManager.getUserData().getPhone());
        tvUserName.setText(mSharedPrefManager.getUserData().getName());
        poundTextView.setText(mSharedPrefManager.getUserData().getBalance());
        if (mSharedPrefManager.getUserIdentity() != null) {
            if (mSharedPrefManager.getUserIdentity().getImage() == null || mSharedPrefManager.getUserIdentity().getAddress() == null || mSharedPrefManager.getUserIdentity().getNationalId() == null){
                IdentificationMainLinear.setVisibility(View.GONE);
            }
            else {
                Glide.with(getContext()).load(mSharedPrefManager.getUserIdentity().getImage().toString()).into(ivPerson);
                tvName.setText(mSharedPrefManager.getUserIdentity().getName().toString());
                tvAddress.setText(mSharedPrefManager.getUserIdentity().getAddress().toString());
                tvSSID.setText(mSharedPrefManager.getUserIdentity().getNationalId().toString());
            }
        }

        sendCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashDialogs(v);
            }
        });
        receiveCashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CashDialogs(v);
            }
        });
        editBusinessCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { BusinessCardButtonClick(v);
            }
        });

        shareBusinessCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { BusinessCardButtonClick(v);
            }
        });

        BusinessCardMainLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchLinears(v);
            }
        });

        BalanceMainLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchLinears(v);
            }
        });

        IdentificationMainLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchLinears(v);
            }
        });
    }

    private void BusinessCardButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditBusinessCard:
                Intent intent = new Intent(getContext(), EditBusinessCardActivity.class);
                startActivity(intent);
                break;

            case R.id.btnShareBusinessCard:
                dialog = new Dialog(MainHomeFragment.getContext(), android.R.style.Theme_Black_NoTitleBar);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_transparent_background);
                dialog.setContentView(R.layout.dialog_send_business_card);
                ImageView ivBusinessCardQR = dialog.findViewById(R.id.ivBusinessCardQR);
                ivBusinessCardQR.setImageResource(R.color.white);
                Log.e("TAG", "BusinessCardButtonClick: " + mSharedPrefManager.getUserData().getContact_qr() );
                RequestBuilder.getRequestBuilder(getContext()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(Uri.parse(mSharedPrefManager.getUserData().getContact_qr()))
                        .into(ivBusinessCardQR);
                ivBusinessCardQR.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Button dismissDialogButton = dialog.findViewById(R.id.dismissDialogButton);
                dismissDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
                dialog.show();
                break;
        }
    }

    private void Validation() {
        int pound = 500; //initial
        int piastre = 50; //initial
        //piastre max length 2 and should NOT be less than 2


        //Some Code Here


        //  poundTextView.setText(String.valueOf(pound));
        //piastreTextView.setText(String.valueOf(piastre));
        //cashQR.setImageResource(R.drawable.qr_code);
    }

    public void CashDialogs(View view) {
        dialog = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_transparent_background);

        switch (view.getId()) {
            case R.id.sendCashButton:
                dialog.setContentView(R.layout.dialog_send_cash);
                amountLayout = dialog.findViewById(R.id.amountLayout);
                poundDialogEditText = dialog.findViewById(R.id.poundDialogEditText);
                piastreDialogEditText = dialog.findViewById(R.id.piastreDialogEditText);
                Button cancelDialogButton = dialog.findViewById(R.id.cancelDialogButton);
                cancelDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
                Button nextDialogButton = dialog.findViewById(R.id.nextDialogButton);
                nextDialogButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });

                pinLayout = dialog.findViewById(R.id.pinLayout);
                pinDialogEditText = dialog.findViewById(R.id.pinEditText);
                confirmDialogTextView = dialog.findViewById(R.id.confirmDialogTextView);
                Button backDialogButton = dialog.findViewById(R.id.backDialogButton);
                backDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
                Button okDialogButton = dialog.findViewById(R.id.okDialogButton);
                okDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
                break;

            case R.id.receiveCashButton:

                dialog.setContentView(R.layout.dialog_receive_cash);
                cashQR = dialog.findViewById(R.id.cashQR);
                progressBar=dialog.findViewById(R.id.progressBar1);
                cashQR.setImageResource(R.color.white);
                Log.e("TAG", "BusinessCardButtonClick: " + mSharedPrefManager.getUserData().getContact_qr() );
                getWalletQr();
                Button dismissDialogButton = dialog.findViewById(R.id.dismissDialogButton);
                dismissDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
                dialog.show();
                break;

        }

        dialog.show();
    }

    public void DialogButtons(View view) {
        String cashAmount, pin;

        switch (view.getId()) {

            case R.id.cancelDialogButton:
            case R.id.dismissDialogButton:
                dialog.dismiss();
                break;

            case R.id.nextDialogButton:
                String pound = poundDialogEditText.getText().toString();
                String piastre = piastreDialogEditText.getText().toString();
                cash=pound+"."+piastre;
                cashAmount = pound + (piastre.isEmpty() ? "" : "." + piastre);
                if (!pound.isEmpty() && !pound.equals("0")) {
                    amountLayout.setVisibility(View.GONE);
                    pinLayout.setVisibility(View.VISIBLE);
                    confirmDialogTextView.setText("Are you sure to sent " + cashAmount + " EGP?");
                }
                break;

            case R.id.backDialogButton:
                pinDialogEditText.setText("");
                amountLayout.setVisibility(View.VISIBLE);
                pinLayout.setVisibility(View.GONE);
                break;

            case R.id.okDialogButton:
                pin = pinDialogEditText.getText().toString();
                Pin=pin;
                if (pin.length() == 4) {
                    dialog.dismiss();

                        if (ActivityCompat
                                .checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    0);
                        } else {
                            MainActivity.mRequestCode =2;
                            new IntentIntegrator(getActivity()).setCaptureActivity(ScannerActivity.class).initiateScan();
                        }
                }

                break;
        }
    }

    public void Swipe() {
        MainHomeFragment.setOnTouchListener(new OnSwipeTouchListener(MainHomeFragment) {
            @Override
            public void onSwipeTop() {
                if (((int) MainHomeFragment.getTag()) > 0) {
                    MainHomeFragment.setTag(((int) MainHomeFragment.getTag()) - 1);
                    SwitchLinearsSwipe(((int) MainHomeFragment.getTag()));
                }
            }

            @Override
            public void onSwipeBottom() {
                if (((int) MainHomeFragment.getTag()) < 2) {
                    MainHomeFragment.setTag(((int) MainHomeFragment.getTag()) + 1);
                    SwitchLinearsSwipe(((int) MainHomeFragment.getTag()));
                }
            }
        });
    }

    public void SwitchLinearsSwipe(int i) {
        switch (i) {

            case 0:
                SwitchLinears(BusinessCardMainLinear);
                break;
            case 1:
                SwitchLinears(BalanceMainLinear);
                break;
            case 2:
                SwitchLinears(IdentificationMainLinear);
                break;
        }
    }

    public void SwitchLinears(View view) {

        BusinessCardLinear.setVisibility(View.GONE);
        BalanceLinear.setVisibility(View.GONE);
        IdentificationLinear.setVisibility(View.GONE);
        BusinessCardMainLinear.setClickable(true);
        BalanceMainLinear.setClickable(true);
        IdentificationMainLinear.setClickable(true);

        switch (view.getId()) {

            case R.id.BusinessCardMainLinear:
                BusinessCardLinear.setVisibility(View.VISIBLE);
                BusinessCardMainLinear.setClickable(false);
                MainHomeFragment.setTag(0);
                break;

            case R.id.BalanceMainLinear:
                BalanceLinear.setVisibility(View.VISIBLE);
                BalanceMainLinear.setClickable(false);
                MainHomeFragment.setTag(1);
                break;

            case R.id.IdentificationMainLinear:
                IdentificationLinear.setVisibility(View.VISIBLE);
                IdentificationMainLinear.setClickable(false);
                MainHomeFragment.setTag(2);
                break;

        }

    }

    public void AnimateVisibility(View view, int visibility) {
        switch (visibility) {
            case View.GONE:
                TranslateAnimation GoneAnimation = new TranslateAnimation(0, 0, 0, view.getHeight());
                GoneAnimation.setDuration(300);
                view.startAnimation(GoneAnimation);
                view.setVisibility(View.GONE);
                break;

            case View.VISIBLE:
                TranslateAnimation VisibleAnimation = new TranslateAnimation(0, 0, 0, view.getHeight());
                VisibleAnimation.setDuration(300);
                VisibleAnimation.setFillAfter(true);
                view.startAnimation(VisibleAnimation);
                view.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void showResultDialogue(final String result) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(getString(R.string.Scanned_result))
                .setMessage(getString(R.string.Scanned_result_is) + result)
                .setPositiveButton(getString(R.string.Show_ad), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        // continue with delete
//                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                        ClipData clip = ClipData.newPlainText("Scan Result", result);
//                        clipboard.setPrimaryClip(clip);
//                        Toast.makeText(MainActivity.this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();

                        //       loadFragmentAdsDetail(new AdsDetailFragment(),result);

                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.info)
    void showIdetity(){
        Intent intent =new Intent(getContext(), ViewIdentification_Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tvTransactionHistory)
    void openTransactionHistory(){
        Intent intent =new Intent(getContext(), TransactionHistoryActivity.class);
        startActivity(intent);
    }

    private void getWalletQr(){
        RetroWeb.getClient()
                .create(ServiceApi.class)
                .getWalletQr(Urls.Bearer+mSharedPrefManager.getUserData().getToken())
                .enqueue(new Callback<WallerQrResponse>() {
                    @Override
                    public void onResponse(Call<WallerQrResponse> call, Response<WallerQrResponse> response) {
                        progressBar.setVisibility(View.GONE);

                        if (response.isSuccessful()){
                            if (response.body().isStatus()){
                                RequestBuilder.getRequestBuilder(getContext()).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                        .load(Uri.parse(response.body().getWallet_qr()))
                                        .into(cashQR);
                                cashQR.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                cashQR.setVisibility(View.VISIBLE);

                            }else {
                                Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<WallerQrResponse> call, Throwable t) {
                        Toast.makeText(getContext(),t+"", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }
                });
    }


}
