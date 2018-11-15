package com.lilliemountain.summit;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Fade());
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.mts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Mountains> list=new ArrayList<>();
        list.add(new Mountains("Kalsubai Mountain",19.6012, 73.7092,R.drawable.kalsubai));
        list.add(new Mountains("Dhodap Mountain",19.5, 78.75,R.drawable.dhodap));
        list.add(new Mountains("Kal Bhairav Mountain",23.2181, 75.768,R.drawable.kal_bhairav));
        list.add(new Mountains("Salher Mountain",20.7247, 73.9428,R.drawable.salher));
        list.add(new Mountains("Saptashrungi Mountain",20.3908, 73.9071,R.drawable.saptashrungi));
        list.add(new Mountains("Torna Mountain",18.2420, 77.2578,R.drawable.torna));
        recyclerView.setAdapter(new MountainAdapter(list));
        SwipeHelper swipeHelper = new SwipeHelper(this, recyclerView) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Book",
                        0,
                        getResources().getColor(R.color.colorPrimaryDark),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(MainActivity.this, "RSVP Booked!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ));

            }
        };

    }
}
