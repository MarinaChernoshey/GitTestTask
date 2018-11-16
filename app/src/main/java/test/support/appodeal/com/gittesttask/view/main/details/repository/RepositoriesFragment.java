package test.support.appodeal.com.gittesttask.view.main.details.repository;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.base.BaseFragment;
import test.support.appodeal.com.gittesttask.network.pojo.Repository;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.main.details.repository.adapter.AdapterListRepository;

public class RepositoriesFragment extends BaseFragment implements MvpContractRepositories.View {

    @BindView(R.id.recycle_view_repositories)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_repositories)
    ProgressBar progressBar;
    @BindView(R.id.view_user_not_have_repositories)
    View viewNotHaveRepositories;

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
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_repositories, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            username = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }

        adapter = new AdapterListRepository(
                repositoryUrl -> presenter.chooseRepositoryFromList(repositoryUrl));
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
        hideProgressBar();
        adapter.updateData(repositories);
    }

    @Override
    public void showMessageNoRepo() {
        viewNotHaveRepositories.setVisibility(View.VISIBLE);
    }

    @Override
    public void attachPresenter(MvpContractRepositories.Presenter presenter) {
        this.presenter = presenter;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(android.view.View.INVISIBLE);
    }
}
