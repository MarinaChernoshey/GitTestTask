package test.support.appodeal.com.gittesttask.details.repository;

import android.os.Bundle;
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
import test.support.appodeal.com.gittesttask.view.fragment.BaseDetailFragment;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.AdapterListRepository;

public class RepositoriesFragment extends Fragment implements MvpContractRepositories.View {

    @BindView(R.id.recycle_view_repositories)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_repositories)
    ProgressBar progressBar;

    private AdapterListRepository adapter;
    private String username;
    private MvpContractRepositories.Presenter presenter;

    public static RepositoriesFragment newInstance(String username) {

        Bundle args = new Bundle();
        args.putString(Const.KEY_PARCELABLE_USER, username);
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

        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            username = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }

        adapter = new AdapterListRepository((view1, position) ->
                presenter.chooseRepositoryFromList(adapter.getRepositories().get(position).getHtmlUrl()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startView(username);
    }

    @Override
    public void showRepositories(List<Repository> repositories) {
        progressBar.setVisibility(View.INVISIBLE);
        adapter.updateData(repositories);
    }

    @Override
    public void attachPresenter(MvpContractRepositories.Presenter presenter) {
        this.presenter = presenter;
    }
}
