package br.mobfeel.nutripet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.mobfeel.nutripet.R;
import br.mobfeel.nutripet.model.Dog;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private List<Dog> dogList;
    private Context context;
    private DogOnClickListener dogOnClickListener;


    public DogAdapter(List<Dog> dogList, Context context, DogOnClickListener dogOnClickListener) {
        this.dogList = dogList;
        this.context = context;
        this.dogOnClickListener = dogOnClickListener;
    }

    public void setDogList(List<Dog> dogList) {
        this.dogList = dogList;
        this.notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_dog,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DogAdapter.ViewHolder holder, final int position) {
        holder.imgProfile.setImageResource(R.drawable.ic_launcher_background); //@todo
        holder.lblRace.setText(this.dogList.get(position).getRace());
        holder.lblName.setText(this.dogList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogOnClickListener.onClickDog(holder.itemView, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.dogList.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProfile;
        public TextView lblName;
        public TextView lblRace;


        public ViewHolder(View view) {
            super(view);
            this.imgProfile = view.findViewById(R.id.imgProfile);
            this.lblName = view.findViewById(R.id.lblName);
            this.lblRace = view.findViewById(R.id.lblRace);

        }
    }

    public interface DogOnClickListener {
        public void onClickDog(View view, int index);
    }
}
