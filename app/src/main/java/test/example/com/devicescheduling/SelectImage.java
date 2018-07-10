package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import test.example.com.devicescheduling.adapters.ImageAdapter;
import test.example.com.devicescheduling.resourceManager.ResourceManager;

public class SelectImage extends AppCompatActivity {

    private ArrayList<Integer> imageData = new ArrayList<>(Arrays.asList(
            ResourceManager.imageResources));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        RecyclerView recyclerView = findViewById(R.id.image_recycler_view);
        ImageAdapter imageAdapter = new ImageAdapter(imageData, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

}
