package test.support.appodeal.com.gittesttask.issues;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.model.Issue;

public class IssuesFragment extends Fragment implements MvpContractIssues.View {

    private MvpContractIssues.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startView();
    }

    @Override
    public void showIssues(List<Issue> issues) {
    }

    @Override
    public void attachPresenter(MvpContractIssues.Presenter presenter) {
        this.presenter = presenter;
    }
}
