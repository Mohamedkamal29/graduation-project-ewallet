package com.graduation.ewallet.Main.PropertyFragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.graduation.ewallet.Main.TransactionHistroy.TransactionHistoryAdapter;
import com.graduation.ewallet.Model.HistoryTransAction.Sent;
import com.graduation.ewallet.Model.ItemModel;
import com.graduation.ewallet.R;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter  extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    LayoutInflater layoutInflater;


    String tag;
    List<ItemModel> ListSend = new ArrayList<>();
    Context context;


    public PropertyAdapter(Context context,List<ItemModel> ListSend) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ListSend=ListSend;
        this.context=context;
    }

    @NonNull
    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = new View(viewGroup.getContext());


        v = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_property, viewGroup, false);



        return new PropertyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapter.ViewHolder viewHolder, int i) {
        final ItemModel model = ListSend.get(i);

        viewHolder.tvName.setText(model.getItem_name());
        viewHolder.tvAmount.setText(model.getItem_price()+" EGP");
        viewHolder.tvContent.setText(model.getItem_description());
        Glide.with(context)
                .load(model.getItem_image()).asBitmap()
                .error(context.getDrawable(R.drawable.ic_problem))
                .into(viewHolder.ivAdd);


    }

    @Override
    public int getItemCount() {
        return ListSend.size();
    }

    public void addAllSent(List<ItemModel> data) {
        ListSend.clear();
        ListSend.addAll(data);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvAmount, tvContent;
                ImageView ivAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAdd=itemView.findViewById(R.id.ivAdd);
            tvName = itemView.findViewById(R.id.tvUserName);
            tvAmount = itemView.findViewById(R.id.price);
            tvContent=itemView.findViewById(R.id.content);


        }
    }
}

