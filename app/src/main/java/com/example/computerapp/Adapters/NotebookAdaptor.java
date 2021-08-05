package com.example.computerapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.computerapp.Models.Notebook;
import com.example.computerapp.Models.User;
import com.example.computerapp.R;

import java.util.List;

public class NotebookAdaptor extends RecyclerView.Adapter<NotebookAdaptor.NotebookAdaptorVH> {
    private List<Notebook> notebookList;
    private Context context;

    public NotebookAdaptor() {
    }

    public void setData(List<Notebook> notebookList) {
        this.notebookList = notebookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotebookAdaptorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NotebookAdaptorVH(LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookAdaptorVH holder, int position) {
        Notebook notebook = notebookList.get(position);
        String brand = notebook.Brand;

        holder.brand.setText(brand);
    }

    @Override
    public int getItemCount() {
        return notebookList.size();
    }

    public class NotebookAdaptorVH extends RecyclerView.ViewHolder {
        TextView brand;


        public NotebookAdaptorVH(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.model_text);
        }
    }
}
