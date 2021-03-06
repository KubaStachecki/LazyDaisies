package com.theedo.kuba.lazydaisies.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.theedo.kuba.lazydaisies.R;


import com.theedo.kuba.lazydaisies.Model.Staff;
import com.squareup.picasso.Picasso;
import java.util.List;


public class ExpandableRecyclerAdapter extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {

    private List<Staff> staffList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ExpandableRecyclerAdapter(List<Staff> staffList) {
        this.staffList = staffList;
        //set initial expanded state to false
        for (int i = 0; i < staffList.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ExpandableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_card_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExpandableRecyclerAdapter.ViewHolder viewHolder, final  int i) {

        viewHolder.setIsRecyclable(false);

        viewHolder.zapowiedz.setText(staffList.get(i).getZapowiedz());
        viewHolder.opis.setText(staffList.get(i).getOpis());
        viewHolder.imie .setText(staffList.get(i).getImie());



        Picasso.with(context)
                .load(staffList.get(i).getObrazek())
                .resize(500, 500)
                .centerCrop()
                .into(viewHolder.portret);


    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView zapowiedz ,opis, imie;
        private ImageView portret;


        public ViewHolder(View view) {
            super(view);

            zapowiedz = view.findViewById(R.id.zapowiedz_text);
            opis = view.findViewById(R.id.opis_text);
            imie = view.findViewById(R.id.imie_text);
            portret = view.findViewById(R.id.portret_img);


        }
    }



}