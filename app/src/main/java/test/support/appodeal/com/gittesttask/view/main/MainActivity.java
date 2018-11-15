package test.support.appodeal.com.gittesttask.view.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.issues.MvpContractIssues;
import test.support.appodeal.com.gittesttask.issues.PresenterIssues;
import test.support.appodeal.com.gittesttask.presenter.PresenterMain;
import test.support.appodeal.com.gittesttask.util.ActivityUtil;
import test.support.appodeal.com.gittesttask.issues.IssuesFragment;
import test.support.appodeal.com.gittesttask.view.fragment.ListUserFragment;
import test.support.appodeal.com.gittesttask.view.fragment.MainDetailFragment;
import test.support.appodeal.com.gittesttask.view.fragment.WebRepositoriesFragment;


public class MainActivity extends ActivityUtil implements MvpContractMain.View.ViewMain {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private FragmentManager fragmentManager; 

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_issue:
                IssuesFragment issuesFragment = new IssuesFragment();
                MvpContractIssues.Presenter presenter = new PresenterIssues(issuesFragment);
                issuesFragment.attachPresenter(presenter);
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void showIssues() {
    }

    @Override
    public void showUsers() {
    }

    @Override
    public void showBaseUser(String login) {
    }

    @Override
    public void showSameUser(String login) {
    }

    @Override
    public void showWebView(String url) {
        replaceFragmentWithAddInStack(fragmentManager, WebRepositoriesFragment.newInstance(url));
    }

    @Override
    public void showOnConnectionInternetError() {

    }
}
