package com.graduation.ewallet.Main.TransactionHistroy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduation.ewallet.Model.HistoryTransAction.Sent;
import com.graduation.ewallet.Model.HistoryTransAction.receve;
import com.graduation.ewallet.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder> {

    String tag;
    List<Sent> ListSend = new ArrayList<>();
    Context context;

    LayoutInflater layoutInflater;

    public TransactionHistoryAdapter(Context context,String tag,List<Sent> ListSend) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tag=tag;
        this.ListSend=ListSend;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = new View(viewGroup.getContext());


            v = layoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transaction_history_sent, viewGroup, false);



        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Sent model = ListSend.get(i);

        viewHolder.tvUserName.setText(model.getName());
        viewHolder.tvAmount.setText(model.getAmount());
        viewHolder.tvTime.setText(timeTransform(model.getDate().substring(11)));
        viewHolder.tvDate.setText(model.getDate().substring(0,11));
        Log.e("tag",model.getSend());
        if (model.getSend().equals("send")){

            viewHolder.tvHeader.setText("Sent");
            viewHolder.tvTo.setText("To");
        }else if (model.getSend().equals("receive")){
            viewHolder.tvHeader.setText("Received");
            Log.e("tag","a7a2");

            viewHolder.tvTo.setText("From");
        }
    }

    @Override
    public int getItemCount() {
        return ListSend.size();
    }

    public void addAllSent(List<Sent> data) {
        ListSend.clear();
        ListSend.addAll(data);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeader,tvUserName, tvAmount, tvTime, tvDate,tvTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHeader=itemView.findViewById(R.id.header);
            tvTo=itemView.findViewById(R.id.to);

        }
    }

    String timeTransform(String time){

        StringBuilder timeBuilder = new StringBuilder();

        int hour = Integer.parseInt(time.substring(0,2));

        if (hour > 12 && hour < 24){
            hour -= 12;
            if (hour < 10)
                timeBuilder.append("0");
            timeBuilder.append(hour);
            timeBuilder.append(time.substring(2));
            timeBuilder.append(" pm");
        }
        else if (hour == 0){
            hour += 12;
            timeBuilder.append(hour);
            timeBuilder.append(time.substring(2));
            timeBuilder.append(" am");
        }
        else if (hour == 12){
            timeBuilder.append(time);
            timeBuilder.append(" pm");
        }
        else{
            timeBuilder.append(time);
            timeBuilder.append(" am");
        }

        return timeBuilder.toString();
    }


}
