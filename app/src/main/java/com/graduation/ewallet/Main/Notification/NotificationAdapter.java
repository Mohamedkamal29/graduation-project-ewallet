package com.graduation.ewallet.Main.Notification;

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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    List<Object> List = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notification, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.tvReceived.setVisibility(View.VISIBLE);
//        viewHolder.tvSent.setVisibility(View.VISIBLE);
//        viewHolder.tvAt.setTextColor(context.getColor(R.color.colorPrimary));
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

        TextView tvReceived, tvSent, tvUserName, tvAmount, tvAt, tvTime, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvReceived = itemView.findViewById(R.id.tvReceived);
            tvSent = itemView.findViewById(R.id.tvSent);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvAt = itemView.findViewById(R.id.tvAt);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);

        }
    }
}
