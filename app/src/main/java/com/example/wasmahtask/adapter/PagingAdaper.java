package com.example.wasmahtask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wasmahtask.R;
import com.example.wasmahtask.models.JeffKellyModel;

import java.util.List;

public class PagingAdaper extends PagedListAdapter<JeffKellyModel,PagingAdaper.Holder> {
 List<JeffKellyModel> modelList;
    public PagingAdaper(){
      super(JeffKellyModel.CALLBACK);
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.jeff_kelly_list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        JeffKellyModel model=getItem(position);
        TextView name=holder.itemView.findViewById(R.id.name_txt);
        name.setText(model.getName());
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
