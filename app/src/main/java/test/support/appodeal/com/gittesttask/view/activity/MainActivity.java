package test.support.appodeal.com.gittesttask.view.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.presenter.PresenterMain;
import test.support.appodeal.com.gittesttask.view.fragment.IssuesFragment;
import test.support.appodeal.com.gittesttask.view.fragment.ListUserFragment;
import test.support.appodeal.com.gittesttask.view.fragment.MainDetailFragment;

public class MainActivity extends BaseActivity implements MvpContractMain.View.ViewMain {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private FragmentManager fragmentManager;
    private MvpContractMain.Presenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_issue:
                presenter.navigationIssues();
                return true;
            case R.id.navigation_dashboard:
                presenter.navigationUsers();
                return true;
            case R.id.navigation_notifications:
                presenter.navigationDetailBaseUser();
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

        presenter = new PresenterMain(this);
        presenter.startViewMain();
    }

    @Override
    public void showIssues() {
        IssuesFragment issuesFragment = new IssuesFragment();
        presenter.attachViewIssue(issuesFragment);
        replaceFragment(fragmentManager, issuesFragment);
    }

    @Override
    public void showUsers() {
        ListUserFragment listUserFragment = ListUserFragment.newInstance();
        presenter.attachViewUsers(listUserFragment);
        replaceFragment(fragmentManager, listUserFragment);
    }

    @Override
    public void showBaseUser(String login) {
        MainDetailFragment tabFragment = MainDetailFragment.newInstance(login);
        presenter.attachViewDetailMain(tabFragment);
        replaceFragment(fragmentManager, tabFragment);
    }

    @Override
    public void showSameUser(String login) {
        MainDetailFragment tabFragment = MainDetailFragment.newInstance(login);
        presenter.attachViewDetailMain(tabFragment);
        replaceFragmentWithAddInStack(fragmentManager, tabFragment);
    }
}
