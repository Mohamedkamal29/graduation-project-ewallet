package com.graduation.ewallet.Main.HomeFragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.graduation.ewallet.Customiztation.OnSwipeTouchListener;
import com.graduation.ewallet.General.ScanActivity;
import com.graduation.ewallet.R;
import com.graduation.ewallet.SharedPrefManger;
import com.graduation.ewallet.UI.ScannerActivity;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        //We will get scan results here
        Log.e("inFragment","true");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "Scan Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //show dialogue with result
                // loadFragmentAdsDetail(new AdsDetailFragment(),result.getContents());
                showResultDialogue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
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
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ActivityCompat
                                .checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    0);
                        } else {
                            new IntentIntegrator(getActivity()).setCaptureActivity(ScannerActivity.class).initiateScan();
                        }
                    }else {
                        Toast.makeText(getContext(),getString(R.string.support_this_service),Toast.LENGTH_SHORT).show();
                    }
                }

                    break;
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

}
