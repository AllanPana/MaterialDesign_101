package com.materialdesign.pana.allan.materialdesign_101.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.materialdesign.pana.allan.materialdesign_101.R;
import com.materialdesign.pana.allan.materialdesign_101.model.Information;

import java.util.Collections;
import java.util.List;

/**
 * Created by allan on 08/06/15.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<Information> data = Collections.EMPTY_LIST;
    private Context context;
    private  MyItemClickListener myItemClickListener;

    public MyRecycleViewAdapter(Context context,List<Information> data ){
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_raw, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position) {

        Information info = data.get(position);
        holder.textViewTitle.setText(info.title);
        holder.imageViewIcon.setImageResource(info.itemId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public synchronized void removeItem(int position){
        data.remove(position);
        notifyItemRemoved(position);
        //notifyDataSetChanged();

    }


    /**
     *
     * @param myItemClickListener
     */
    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    /**
     * subclass of ViewHolder class to use in RecycleViewAdapter
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageViewIcon;
        TextView textViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this); //===== onClickListener will respond when you click the parent view
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView_icon);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewIcon.setOnClickListener(this);   //=== onClickListener will respond when you click the image only
            textViewTitle.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(myItemClickListener != null){

                switch (v.getId()){
                    case R.id.imageView_icon:
                        myItemClickListener.myItemClicked(v,getAdapterPosition());
                        break;
                    case R.id.textViewTitle:
                        myItemClickListener.myTextViewClicked(v, getAdapterPosition(), textViewTitle);
                        break;
                }

            }
        }
    }


    /**
     * interface
     */
    public interface MyItemClickListener{
        public void myItemClicked(View view, int position);
        public void myTextViewClicked(View view, int position, TextView textView);
    }
}
