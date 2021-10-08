package com.example.ptsganjil2021reza23;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

import de.hdodenhof.circleimageview.CircleImageView;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {
    ArrayList<SportsModel> sportsList;
    private SportsAdapter.Callback callback;

    interface Callback{
        void onClick(int position);
    }

    public SportsAdapter(ArrayList<SportsModel> arrayList, Callback callback){
        this.sportsList = arrayList;
        this.callback = callback;
        Log.d("Mboh", String.valueOf(callback));
    }
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsAdapter.ViewHolder holder, int position) {
        holder.txtName.setText(sportsList.get(position).getStrSport());
        holder.txtDesc.setText(sportsList.get(position).getStrFormat());
        Picasso.get().load(sportsList.get(position).getStrSportThumb()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return (sportsList != null)? sportsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
