package test.support.appodeal.com.gittesttask.view.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.util.ActivityUtil;

public class MainActivity extends ActivityUtil implements MvpContractMain.View {
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private FragmentManager fragmentManager;
    private MainPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_issue:
                presenter.navigationIssues();
                return true;
            case R.id.navigation_users:
                presenter.navigationUsers();
                return true;
            case R.id.navigation_details:
                presenter.navigationDetailsFromMainScreen(App.getInstance().getLoginUser());
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        presenter.startView();
    }

    @Override
    public void showFragmentWithAddInBackStack(Fragment fragment) {
        replaceFragmentWithAddInStack(fragmentManager, fragment);
    }

    @Override
    public void showFragmentWithoutAddInBackStack(Fragment fragment) {
        replaceFragment(fragmentManager, fragment);
    }
}
