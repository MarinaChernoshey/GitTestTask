package test.support.appodeal.com.gittesttask.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.Issue;

public class IssuesFragment extends BaseMainFragment implements MvpContractMain.View.ViewIssues {

    public IssuesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        ButterKnife.bind(this, view);

        presenter.detachViewIssues();

        return view;
    }

    @Override
    public void showIssues(List<Issue> issues) {

    }

    @Override
    public void detachPresenter() {
        presenter.detachViewIssues();
    }
}
