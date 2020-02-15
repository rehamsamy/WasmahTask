package com.example.wasmahtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wasmahtask.R;
import com.example.wasmahtask.models.JeffKellyModel;
import com.example.wasmahtask.models.RefCountryCodesItem;
import com.example.wasmahtask.utils.OnRecyclerClick;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {
    Context mContext;
    List<RefCountryCodesItem> items;
    OnRecyclerClick mInterface;

    public FavoriteAdapter(Context mContext, List<RefCountryCodesItem> items) {
        this.mContext = mContext;
        this.items = items;
    }

    public void setItemClickInterface(OnRecyclerClick mInterface){
        this.mInterface=mInterface;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.jeff_kelly_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       RefCountryCodesItem model=items.get(position);
        TextView name=holder.itemView.findViewById(R.id.name_txt);
        name.setText(model.getCountry());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterface.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
