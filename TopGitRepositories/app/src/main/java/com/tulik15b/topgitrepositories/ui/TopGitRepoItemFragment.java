package com.tulik15b.topgitrepositories.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tulik15b.topgitrepositories.R;
import com.tulik15b.topgitrepositories.adapter.TGRepoAdapter;
import com.tulik15b.topgitrepositories.model.TGRepo;
import com.tulik15b.topgitrepositories.viewmodel.TopGitRepoViewModel;

import java.util.List;

public class TopGitRepoItemFragment extends Fragment {

    TextView userName;
    TextView name;
    TextView url;
    ImageView avatar;
    TextView repoName;
    TextView repoDesc;
    TextView repoUrl;

    TopGitRepoViewModel mViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_tg_repo_item, container, false);

        userName = parentView.findViewById(R.id.tv_username);

        name = parentView.findViewById(R.id.tv_name);
        url = parentView.findViewById(R.id.tv_url);
        avatar = parentView.findViewById(R.id.iv_avatar);

        repoName = parentView.findViewById(R.id.tv_repoName);
        repoDesc = parentView.findViewById(R.id.tv_repoDescr);
        repoUrl = parentView.findViewById(R.id.tv_repoUrl);

        final String repoName = this.getArguments().getString("REPO_NAME");

        TopGitRepoViewModel.Factory factory = new TopGitRepoViewModel.Factory(getActivity().getApplication(), "");
        mViewModel = ViewModelProviders.of(getActivity(), factory).get(TopGitRepoViewModel.class);
        mViewModel.getRepoFromRepoId(repoName).observe(this, new Observer<TGRepo>() {

            @Override
            public void onChanged(@Nullable final TGRepo repo) {
                userName.setText(repo.username);
                name.setText(repo.name);
                url.setText(repo.url);


                Glide.with(getActivity())
                        .load(repo.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .into(avatar);

            }
        });

        return parentView;

    }
}
