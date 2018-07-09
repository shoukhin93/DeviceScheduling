package test.example.com.devicescheduling.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import test.example.com.devicescheduling.R;
import test.example.com.devicescheduling.models.HistoryModel;

/**
 * Created by Shoukhin on 7/7/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder> {
    private List<HistoryModel> historyList;
    private Context context;

    public HistoryAdapter(List<HistoryModel> historyList, Context context) {
        this.historyList = historyList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_history_view, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.historyImageView.setImageResource(historyList.get(position).getImageResourceID());
        holder.historyMessageTextView.setText(historyList.get(position).getMessage());
        holder.historySetterNameTextView.setText(historyList.get(position).getSetterName());
        holder.historyDateTextView.setText(historyList.get(position).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView historyImageView;
        TextView historyMessageTextView;
        TextView historySetterNameTextView;
        TextView historyDateTextView;

        public ItemViewHolder(View view) {
            super(view);
            historyImageView = view.findViewById(R.id.schedule_image_view);
            historyMessageTextView = view.findViewById(R.id.schedule_message_text_view);
            historySetterNameTextView = view.findViewById(R.id.schedule_setter_name_text_view);
            historyDateTextView = view.findViewById(R.id.schedule_time_text_view);
        }
    }
}

