package com.geekbrains.a1l6_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<DataClass> data = new ArrayList<>();
    private Context context;

    RecyclerViewAdapter(ArrayList<DataClass> data) {
        if(data != null) {
            this.data = data;
        }
    }

    void addItem(DataClass dataClass) {
        data.add(1, dataClass);
        notifyItemInserted(1);
    }

    void removeItem() {
        data.remove(0);
        notifyItemRemoved(0);
    }

    void moveBtn() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).text);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View view) {
            super(view);

            textView = itemView.findViewById(R.id.textView);
        }
    }

    public class CustomGridLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomGridLayoutManager(Context context) {
            super(context);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }


        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }


    }






}
