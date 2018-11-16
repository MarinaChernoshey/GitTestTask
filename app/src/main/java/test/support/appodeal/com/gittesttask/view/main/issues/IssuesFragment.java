package test.support.appodeal.com.gittesttask.view.main.issues;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import test.support.appodeal.com.gittesttask.network.pojo.Issue;
import test.support.appodeal.com.gittesttask.view.main.issues.adapter.IssuesAdapter;

public class IssuesFragment extends BaseFragment implements MvpContractIssues.View {

    @BindView(R.id.recycler_view_issues)
    RecyclerView recyclerView;
    @BindView(R.id.view_user_not_have_issues)
    android.view.View viewUserNotHaveIssues;
    @BindView(R.id.progress_bar_issues)
    ProgressBar progressBar;

    private MvpContractIssues.Presenter presenter;
    private IssuesAdapter adapter;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_issues, container, false);
        ButterKnife.bind(this, view);
        adapter = new IssuesAdapter();
        recyclerView.setAdapter(adapter);
        SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swipe_pull_to_refresh_issues);
        pullToRefresh.setOnRefreshListener(() -> {
            presenter.startView();
            pullToRefresh.setRefreshing(false);
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startView();
    }

    @Override
    public void showIssues(List<Issue> issues) {
        adapter.setIssues(issues);
        hideProgressBar();
    }

    @Override
    public void showMessageUserNotHaveIssues() {
        viewUserNotHaveIssues.setVisibility(View.VISIBLE);
        hideProgressBar();
    }

    @Override
    public void attachPresenter(MvpContractIssues.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
        ;
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
