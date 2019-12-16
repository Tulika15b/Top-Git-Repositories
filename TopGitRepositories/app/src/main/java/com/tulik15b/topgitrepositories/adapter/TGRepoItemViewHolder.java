package com.tulik15b.topgitrepositories.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tulik15b.topgitrepositories.R;


public class TGRepoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView repoIcon;
    TextView userName;
    TextView name;
    TextView url;

    AdapterView.OnItemClickListener onItemClickListener;

    public TGRepoItemViewHolder(@NonNull View itemView, AdapterView.OnItemClickListener listener) {
        super(itemView);

        this.onItemClickListener = listener;

        repoIcon = itemView.findViewById(R.id.repo_iv);
        repoIcon.setOnClickListener(this);
        userName = itemView.findViewById(R.id.repo_username_tv);
        name = itemView.findViewById(R.id.repo_name_tv);
        url = itemView.findViewById(R.id.repo_url_tv);

    }

    @Override
    public void onClick(View view) {
        onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
    }


}
