package com.example.mahira2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class addDatAdapter extends RecyclerView.Adapter<addDatAdapter.myviewholder>
{

    List<fetchaddwork> data;

    public addDatAdapter(List<fetchaddwork> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_work_singal_row,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
           holder.barcode.setText(data.get(position).getBarcode());
           holder.item.setText(data.get(position).getItem());
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView barcode,item;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            barcode=itemView.findViewById(R.id.barcode);
            item=itemView.findViewById(R.id.items);
        }
    }
}
