package test.example.com.devicescheduling.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import test.example.com.devicescheduling.Constants;
import test.example.com.devicescheduling.R;

/**
 * Created by Shoukhin on 7/7/2018.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private List<Integer> imageList;
    private Activity context;

    public ImageAdapter(List<Integer> imageList, Activity context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_view, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.itemImage.setImageResource(imageList.get(position));
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.IMAGE_RESOURSE_CODE, imageList.get(position));
                context.setResult(Activity.RESULT_OK, returnIntent);
                context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;

        public ItemViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.select_image_view);
        }
    }
}

