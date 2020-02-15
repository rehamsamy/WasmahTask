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

import java.util.List;

public class JeffKellyAdapter extends RecyclerView.Adapter<JeffKellyAdapter.Holder> {
    Context mContext;
    List<JeffKellyModel> jeffModels;

    public JeffKellyAdapter(Context mContext, List<JeffKellyModel> jeffModels) {
        this.mContext = mContext;
        this.jeffModels = jeffModels;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.jeff_kelly_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JeffKellyModel model=jeffModels.get(position);
        TextView name=holder.itemView.findViewById(R.id.name_txt);
        name.setText(model.getName());


    }

    @Override
    public int getItemCount() {
        return jeffModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
