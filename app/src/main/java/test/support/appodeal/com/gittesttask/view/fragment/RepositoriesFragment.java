package test.support.appodeal.com.gittesttask.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.AdapterListRepository;

public class RepositoriesFragment extends BaseDetailFragment
        implements MvpContractMain.View.ViewRepositories {

    @BindView(R.id.recycle_view_repositories)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_repositories)
    ProgressBar progressBar;

    private AdapterListRepository adapter;

    public RepositoriesFragment() {
    }

    public static RepositoriesFragment newInstance(String login) {

        Bundle args = new Bundle();
        args.putString(Const.KEY_PARCELABLE_USER, login);
        RepositoriesFragment fragment = new RepositoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repositories, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);

        adapter = new AdapterListRepository();
        recyclerView.setAdapter(adapter);

        presenter.startViewRepositories(login);

        return view;
    }

    @Override
    public void showListRepository(List<Repository> repositories) {
        progressBar.setVisibility(View.INVISIBLE);
        adapter.updateData(repositories);
    }

    @Override
    public void detachPresenter() {
        presenter.detachViewRepositories();
    }
}
