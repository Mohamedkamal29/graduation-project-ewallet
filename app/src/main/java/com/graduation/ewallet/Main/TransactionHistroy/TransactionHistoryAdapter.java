package com.graduation.ewallet.Main.TransactionHistroy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduation.ewallet.R;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder> {

    String tag;
    List<Object> List = new ArrayList<>();
    LayoutInflater layoutInflater;

    public TransactionHistoryAdapter(Context context,String tag) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = new View(viewGroup.getContext());

        if (tag.equals("sent"))
            v = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaction_history_sent, viewGroup, false);
        else if (tag.equals("received"))
            v = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaction_history_received, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvUserName.setText("");
        viewHolder.tvAmount.setText("");
        viewHolder.tvTime.setText("");
        viewHolder.tvDate.setText("");
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public void addAll(List<Object> data) {
        List.clear();
        List.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvAmount, tvTime, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);

        }
    }
}
