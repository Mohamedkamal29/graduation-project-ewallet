package com.graduation.ewallet.Main.ContactFragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.graduation.ewallet.R;


public class ContactVerticalPagerAdapter extends PagerAdapter {

//    int[] CreditCards = {R.drawable.credit_card1, R.drawable.credit_card2, R.drawable.credit_card3, R.drawable.credit_card4};
    LayoutInflater inflater;

    public ContactVerticalPagerAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    private void setupItem(View view, int resource) {

        final LinearLayout item = view.findViewById(R.id.llItemBusinessCard);
        TextView tvPhone = view.findViewById(R.id.tv_phone);
        TextView tvEmail = view.findViewById(R.id.tv_email);
        final ImageView ivCall = view.findViewById(R.id.ivCall);
        final ImageView ivEmail = view.findViewById(R.id.ivEmail);
        ImageView ivDelete = view.findViewById(R.id.ivDelete);
        item.setTag(0);

        item.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if ( (int)(item.getTag()) == 0) {
                    ivCall.setVisibility(View.VISIBLE);
                    ivEmail.setVisibility(View.VISIBLE);
                    item.setTag(1);
                }
                else{
                    ivCall.setVisibility(View.INVISIBLE);
                    ivEmail.setVisibility(View.INVISIBLE);
                    item.setTag(0);
                }
            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



    @Override
    public int getCount() {
//        return CreditCards.length;
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.item_business_card, container, false);
//            setupItem(view, CreditCards[position]);
            container.addView(view);
            return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
}
