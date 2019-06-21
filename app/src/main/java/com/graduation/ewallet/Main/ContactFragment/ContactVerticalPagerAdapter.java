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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.graduation.ewallet.Model.BusinessCard.Contact;
import com.graduation.ewallet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ContactVerticalPagerAdapter extends PagerAdapter {

    int[] ContactCardColor = {R.drawable.ic_contact_blue, R.drawable.ic_contact_red, R.drawable.ic_contact_yellow, R.drawable.ic_contact_green, R.drawable.ic_contact_purple};
    LayoutInflater inflater;
    int previousRandom = 5;

    List<Contact> contactList;

    public ContactVerticalPagerAdapter(Context context, List<Contact> contactList) {
        this.inflater = LayoutInflater.from(context);
        this.contactList = contactList;
    }

    private void setupItem(View view, Contact contact) {

        final RelativeLayout item = view.findViewById(R.id.llItemBusinessCard);
        if (contact.getColorID() == 0){
            int random = (int) Math.round(Math.random() * 4);
            if (random == previousRandom){
                random = (int) Math.round(Math.random() * 4);
            }
            previousRandom = random;
            contact.setColorID(ContactCardColor[random]);
        }
        item.setBackgroundResource(contact.getColorID());
        TextView tvName = view.findViewById(R.id.tvName);
        tvName.setText(contact.getName());
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        tvEmail.setText(contact.getEmail());
        TextView tvJob = view.findViewById(R.id.tvJob);
        tvJob.setText(contact.getJob());
        TextView tvPhone = view.findViewById(R.id.tvPhone);
        tvPhone.setText(contact.getPhone());
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
        return contactList.size();
//        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.item_business_card, container, false);
            setupItem(view, contactList.get(position));
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
