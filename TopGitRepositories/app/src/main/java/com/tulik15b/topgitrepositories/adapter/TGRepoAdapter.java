package com.tulik15b.topgitrepositories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tulik15b.topgitrepositories.R;
import com.tulik15b.topgitrepositories.model.TGRepo;

import java.util.Collections;
import java.util.List;

public class TGRepoAdapter extends RecyclerView.Adapter<TGRepoItemViewHolder> {

    List<TGRepo> mRepoList = Collections.emptyList();
    Context mContext;

    AdapterView.OnItemClickListener onItemClickListener;

    public TGRepoAdapter(Context context, AdapterView.OnItemClickListener listener){
        mContext = context;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public TGRepoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_repo, null);
        TGRepoItemViewHolder TGRepoItemViewHolder = new TGRepoItemViewHolder(view, onItemClickListener);
        return TGRepoItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TGRepoItemViewHolder holder, final int position) {
        final TGRepo repo = mRepoList.get(position);

        if(repo != null){

            Glide.with(mContext)
                    .load(repo.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.repoIcon);
            holder.name.setText(repo.name);
            holder.userName.setText(repo.username);
            holder.url.setText(repo.url);
        }

    }

    public void setTrendingList(List<TGRepo> repoList){
        mRepoList = repoList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (mRepoList == null) ? 0 : mRepoList.size();
    }
}
