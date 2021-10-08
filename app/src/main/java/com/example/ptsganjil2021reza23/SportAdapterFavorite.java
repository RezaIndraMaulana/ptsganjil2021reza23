package com.example.ptsganjil2021reza23;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SportAdapterFavorite extends RecyclerView.Adapter<SportAdapterFavorite.ViewHolder> {

    List<SportsModel> sportsList;
    Callback callback;

    interface Callback{
        void onClick(int position);
    }

    public SportAdapterFavorite(List<SportsModel> modelList, Callback callback) {
        this.sportsList = modelList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public SportAdapterFavorite.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapterFavorite.ViewHolder holder, int position) {

        holder.txtName.setText(sportsList.get(position).getStrSport());
        holder.txtDesc.setText(sportsList.get(position).getStrFormat());
        Picasso.get().load(sportsList.get(position).getStrSportThumb()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return (sportsList != null)? sportsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName, txtDesc;
        private ImageView img;
        private CardView container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tx_sport_name);
            txtDesc = itemView.findViewById(R.id.Sport_Description);
            img = itemView.findViewById(R.id.img_sport);
            container = itemView.findViewById(R.id.container);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
    }
}
