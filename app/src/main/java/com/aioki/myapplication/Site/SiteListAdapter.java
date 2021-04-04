package com.aioki.myapplication.Site;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aioki.myapplication.DialogDeleteSite;
import com.aioki.myapplication.R;

import java.util.List;

public class SiteListAdapter extends RecyclerView.Adapter<SiteListAdapter.SiteModelViewHolder> {

    private List<SiteModel> mSiteModels;
    private FragmentManager fragmentManager;


    public static class SiteModelViewHolder extends RecyclerView.ViewHolder {
        private View SiteView;

        public SiteModelViewHolder(View v) {
            super(v);
            SiteView = v;
        }
    }

    public SiteListAdapter(List<SiteModel> siteModels, FragmentManager fragmentManager) {
        mSiteModels = siteModels;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public SiteModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_site, parent, false);
        SiteListAdapter.SiteModelViewHolder holder = new SiteListAdapter.SiteModelViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SiteModelViewHolder holder, final int position) {
        final SiteModel siteModel = mSiteModels.get(position);

        ((TextView)holder.itemView.findViewById(R.id.siteText)).setText(siteModel.getName());
        ((TextView)holder.itemView.findViewById(R.id.urlText)).setText(siteModel.getUrl());
        holder.itemView.setId(siteModel.getId());
        //((TextView)holder.SiteView.findViewById(R.id.idSiteText)).setText(Integer.toString(siteModel.getId()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                DialogDeleteSite dialog = new DialogDeleteSite();
                Bundle args = new Bundle();
                args.putInt("SiteID", holder.itemView.getId());
                dialog.setArguments(args);
                dialog.show(fragmentManager, "custom");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSiteModels.size();
    }

}
