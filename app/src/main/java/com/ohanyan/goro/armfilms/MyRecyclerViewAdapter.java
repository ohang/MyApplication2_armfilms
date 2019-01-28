package com.ohanyan.goro.armfilms;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * Created by Goro on 13.03.2018.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    Context context;
    private String[] mData = new String[0];
    private LayoutInflater mInflater;
    String[] image;
    private ItemClickListener mClickListener;


    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, String[] data,String[] image) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.image=image;
        this.context=context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String animal = mData[position];
        holder.myTextView.setText(animal);

        Picasso.get()
                .load(image[position])
                .into(holder.grid, new Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.get()
                                .load(image[position])
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(holder.grid);
                    }

                    @Override
                    public void onError(Exception e) {

                    }


                });
        //Picasso.with(context).load(image[position]).into(holder.grid);
      //  Glide.with(context)
        //        .load(image[position])
          //      .into(holder.grid);




    }


    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView grid;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            grid =(ImageView)itemView.findViewById(R.id.griditem_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}