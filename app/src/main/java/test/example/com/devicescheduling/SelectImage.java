package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import test.example.com.devicescheduling.adapter.ImageAdapter;

public class SelectImage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private ArrayList<Integer> imageData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        setImageResources();

        recyclerView = findViewById(R.id.image_recycler_view);
        imageAdapter = new ImageAdapter(imageData, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    private void setImageResources() {
        imageData.add(R.drawable.pic1);
        imageData.add(R.drawable.pic1);
    }
}
