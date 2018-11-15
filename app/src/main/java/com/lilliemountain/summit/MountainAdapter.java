package com.lilliemountain.summit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.MountainHolder> {
    List<Mountains> list;

    public MountainAdapter(List<Mountains> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MountainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview,viewGroup,false);
        return new MountainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MountainHolder mountainHolder, int i) {
        String btnString=list.get(i).getName()+" ("+list.get(i).getLat()+" , "+list.get(i).getLng()+")";
        mountainHolder.button.setText(btnString);
        mountainHolder.imageView.setBackgroundResource(list.get(i).getPhoto());
        mountainHolder.imageView.setTag(list.get(i).getPhoto());
        mountainHolder.lat=list.get(i).getLat();
        mountainHolder.lng=list.get(i).getLng();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MountainHolder extends RecyclerView.ViewHolder {
        double lat,lng;
        Button button;
        ImageView imageView;
        TextView textView;
        public MountainHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.location);
            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.mountain);
            button.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Context context=v.getContext();
                    Activity activity=(Activity)context;
                    Intent intent=new Intent(context,MountainDetailActivity.class);
                    Mountains mountains=new Mountains(button.getText().toString().split(" ")[0],lat,lng,Integer.parseInt(String.valueOf(imageView.getTag())));
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("mts",mountains);
                    intent.putExtras(bundle);
                    Pair<View,String> p1=Pair.create((View)button,"location");
                    Pair<View,String> p2=Pair.create((View)textView,"map");
                    Pair<View,String> p3=Pair.create((View)imageView,"mountain");
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(activity,p1,p2,p3);
                    context.startActivity(intent,options.toBundle());
                }
            });
        }
    }
}
