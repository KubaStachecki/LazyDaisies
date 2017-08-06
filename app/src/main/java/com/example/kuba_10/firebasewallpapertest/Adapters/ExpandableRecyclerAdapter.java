package com.example.kuba_10.firebasewallpapertest.Adapters;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.kuba_10.firebasewallpapertest.R;


import com.example.kuba_10.firebasewallpapertest.Model.News;
import com.squareup.picasso.Picasso;
import java.util.List;


public class ExpandableRecyclerAdapter extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {

    private List<News> newsList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ExpandableRecyclerAdapter(List<News> newsList) {
        this.newsList = newsList;
        //set initial expanded state to false
        for (int i = 0; i < newsList.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ExpandableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExpandableRecyclerAdapter.ViewHolder viewHolder, final  int i) {

        viewHolder.setIsRecyclable(false);

        viewHolder.tvName.setText(newsList.get(i).getTitle());

        viewHolder.tvOwnerLogin.setText("Owner: " +newsList.get(i).getContent());
        viewHolder.tvOwnerUrl.setText(newsList.get(i).getUrl());

        Picasso.with(context)
                .load(newsList.get(i).getUrl())
                .resize(500, 500)
                .centerCrop()
                .into(viewHolder.ivOwner);

        //check if view is expanded
        final boolean isExpanded = expandState.get(i);
        viewHolder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        viewHolder.buttonLayout.setRotation(expandState.get(i) ? 180f : 0f);
        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickButton(viewHolder.expandableLayout, viewHolder.buttonLayout,  i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvOwnerLogin, tvOwnerUrl;
        private ImageView ivOwner;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        public ViewHolder(View view) {
            super(view);

            tvName = (TextView)view.findViewById(R.id.textView_name);
            tvOwnerLogin = (TextView)view.findViewById(R.id.textView_Owner);
            tvOwnerUrl = (TextView)view.findViewById(R.id.textView_OwnerUrl);
            ivOwner = (ImageView) view.findViewById(R.id.imageView_Owner);

            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);
        }
    }

    private void onClickButton(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int i) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(i, false);
        }else{
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(i, true);
        }
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}