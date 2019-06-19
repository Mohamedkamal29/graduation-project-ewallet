package com.graduation.ewallet.Main.HomeFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.graduation.ewallet.Customiztation.OnSwipeTouchListener;
import com.graduation.ewallet.General.ScanActivity;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeFragment extends Fragment {

    @BindView(R.id.poundTextView) TextView poundTextView;
//    @BindView(R.id.piastreTextView) TextView piastreTextView;
    @BindView(R.id.sendCashButton) Button sendCashButton;
    @BindView(R.id.receiveCashButton) Button receiveCashButton;
    @BindView(R.id.BusinessCardMainLinear) LinearLayout BusinessCardMainLinear;
    @BindView(R.id.BalanceMainLinear) LinearLayout BalanceMainLinear;
    @BindView(R.id.IdentificationMainLinear) LinearLayout IdentificationMainLinear;
    @BindView(R.id.BusinessCardLinear) LinearLayout BusinessCardLinear;
    @BindView(R.id.BalanceLinear) LinearLayout BalanceLinear;
    @BindView(R.id.IdentificationLinear) RelativeLayout IdentificationLinear;

    @BindView(R.id.tv_userName) TextView tvUserName;
    @BindView(R.id.tv_jop) TextView tvJop;
    @BindView(R.id.tv_email) TextView tvEmail;
    @BindView(R.id.tv_phone) TextView tvPhone;

    TextView confirmDialogTextView;
    Dialog dialog;
    EditText poundDialogEditText, piastreDialogEditText, pinDialogEditText;
    LinearLayout amountLayout, pinLayout;
    ImageView cashQR;

    private SharedPrefManger mSharedPrefManager;


    View MainHomeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainHomeFragment = inflater.inflate(R.layout.main_home_fragment, container, false);
        MainHomeFragment.setTag(1);
        ButterKnife.bind(this,MainHomeFragment);
        mSharedPrefManager = new SharedPrefManger(getContext());

        tvEmail.setText(mSharedPrefManager.getUserData().getEmail());
        tvJop.setText(mSharedPrefManager.getUserData().getJob());
        tvPhone.setText(mSharedPrefManager.getUserData().getPhone());
        tvUserName.setText(mSharedPrefManager.getUserData().getName());
        poundTextView.setText(mSharedPrefManager.getUserData().getBalance());

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

        Swipe();

      //  Validation();

        return MainHomeFragment;
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
                Button dismissDialogButton = dialog.findViewById(R.id.dismissDialogButton);
                dismissDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogButtons(v);
                    }
                });
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
                if (pin.length() == 4) {
                    dialog.dismiss();
                    Intent intent = new Intent(getContext(), ScanActivity.class);
                    startActivity(intent);
                    break;
                }
        }
    }

    public void Swipe(){
        MainHomeFragment.setOnTouchListener(new OnSwipeTouchListener(MainHomeFragment){
            @Override
            public void onSwipeTop() {
                if (((int)MainHomeFragment.getTag()) > 0) {
                    MainHomeFragment.setTag(((int) MainHomeFragment.getTag()) - 1);
                    SwitchLinearsSwipe(((int) MainHomeFragment.getTag()));
                }
            }

            @Override
            public void onSwipeBottom() {
                if (((int)MainHomeFragment.getTag()) < 2) {
                    MainHomeFragment.setTag(((int) MainHomeFragment.getTag()) + 1);
                    SwitchLinearsSwipe(((int) MainHomeFragment.getTag()));
                }
            }
        });
    }

    public void SwitchLinearsSwipe(int i){
        switch (i){

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
}