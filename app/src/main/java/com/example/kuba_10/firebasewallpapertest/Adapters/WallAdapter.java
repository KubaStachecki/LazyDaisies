package com.example.kuba_10.firebasewallpapertest.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kuba_10.firebasewallpapertest.Fragments.FragmentUtils;
import com.example.kuba_10.firebasewallpapertest.Fragments.ImageFragment;
import com.example.kuba_10.firebasewallpapertest.Model.Image;
import com.example.kuba_10.firebasewallpapertest.R;
import com.example.kuba_10.firebasewallpapertest.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Created by Kuba-10 on 13.07.2017.
 */
public class WallAdapter extends RecyclerView.Adapter<WallAdapter.ViewHolder> {

    private int imageSizePixelsW;
    private int imageSizePixelsH;
    private Context context;
    private List<Image> data_list;
    private FragmentUtils fragmentUtils;
    Utils utils;
    public ImageView progressBar2;
    private RelativeLayout imageContainer;



    public WallAdapter(Context context, List<Image> data_list) {
        this.context = context;
        this.data_list = data_list;


        imageSizePixelsW = context.getResources().getDimensionPixelSize(R.dimen.card_image_width);
        imageSizePixelsH = context.getResources().getDimensionPixelSize(R.dimen.card_image_heigth);

        fragmentUtils = (FragmentUtils) context;

        utils = new Utils(context);


    }


    @Override
    public WallAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_card, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(WallAdapter.ViewHolder holder, final int position) {


       ViewGroup.LayoutParams params = holder.imageContainer.getLayoutParams();

        params.height = utils.getScreenWidth() / 2;
        params.width = utils.getScreenWidth() / 2;
        holder.imageContainer.setLayoutParams(params);



        holder.loader.setVisibility(View.VISIBLE);
        holder.imageView.setVisibility(View.VISIBLE);
        final ProgressBar progressView = holder.loader;


        Picasso.with(context)
                .load(data_list.get(position).getUrl())
                .resize((utils.getScreenWidth() / 2) - 20, (utils.getScreenWidth() / 2) - 20)
                .centerCrop()


//                .placeholder(R.drawable.progress_animation)

                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {


                    }
                });

        holder.artist.setText(data_list.get(position).getArtist());
        holder.comment.setText(data_list.get(position).getComment());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("Image", data_list.get(position));

                fragmentUtils.openFragment(ImageFragment.newInstance(bundle));


//                Toast.makeText(context, "klik", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        public TextView artist;
        public TextView comment;
        public ImageView imageView;
        public ProgressBar  loader;
        public RelativeLayout imageContainer;


        public ViewHolder(View itemView) {
            super(itemView);

            artist = (TextView) itemView.findViewById(R.id.artist);
            comment = (TextView) itemView.findViewById(R.id.comment);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            loader = (ProgressBar) itemView.findViewById(R.id.loader);
            imageContainer = (RelativeLayout) itemView.findViewById(R.id.image_container);
        }
    }
}