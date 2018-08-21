package br.mobfeel.nutripet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.mobfeel.nutripet.R;
import br.mobfeel.nutripet.model.Dog;
import br.mobfeel.nutripet.model.Month;


public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static int HEADER_ITEM = 1;
    static int MONTH_ITEM = 2;

    private Dog dog;
    private List<Month> monthList;
    private Context context;
    private MonthOnClickListener monthOnClickListener;

    public DetailAdapter(Dog dog, List<Month> monthList, Context context, MonthOnClickListener monthOnClickListener) {
        this.dog = dog;
        this.monthList = monthList;
        this.context = context;
        this.monthOnClickListener = monthOnClickListener;
    }

    public void setMonthList(List<Month> monthList) {
        this.monthList = monthList;
        this.notifyDataSetChanged();
    }

    public void setDog(Dog dog) {
        this.dog = dog;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if( viewType == HEADER_ITEM ){
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.list_item_dog_detail, parent, false);
            ViewHolderHeader vhh = new ViewHolderHeader(view);
            return vhh;
        }

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_month_detail, parent, false);
        ViewHolderMonth vhm = new ViewHolderMonth(view);
        return vhm;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(position == 0 ){
            ViewHolderHeader header = (ViewHolderHeader) holder;
            header.imgProfile.setImageResource(R.drawable.ic_launcher_background);
            header.tvName.setText(dog.getName() + " - " + dog.getRace());
        }else{
            ViewHolderMonth header = (ViewHolderMonth) holder;
            Month month = monthList.get(position - 1); // - header
            header.tvMonth.setText(Integer.toString(month.getMonth()));
            header.tvValue.setText(Double.toString(month.getValue()));
            header.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monthOnClickListener.onClickMonth(view, position - 1);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_ITEM;
        }

        return MONTH_ITEM;
    }

    @Override
    public int getItemCount() {
        return 1 + this.monthList.size();
    }

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {
        public ImageView imgProfile;
        public TextView tvName;

        public ViewHolderHeader(View view) {
            super(view);
            this.imgProfile = view.findViewById(R.id.ldd_img_profile);
            this.tvName = view.findViewById(R.id.ldd_tv_name);
        }
    }

    public static class ViewHolderMonth extends RecyclerView.ViewHolder {
        public TextView tvMonth, tvValue;

        public ViewHolderMonth(View view) {
            super(view);
            this.tvMonth = view.findViewById(R.id.lmd_tv_month);
            this.tvValue = view.findViewById(R.id.lmd_tv_value);
        }
    }

    public interface MonthOnClickListener {
        public void onClickMonth(View view, int index);
    }



}
