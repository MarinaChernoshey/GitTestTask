package test.support.appodeal.com.gittesttask.view.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.view.fragment.DetailUserFragment;
import test.support.appodeal.com.gittesttask.view.fragment.RepositoriesFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private MvpContractMain.Presenter presenter;
    private String login;

    public TabsAdapter(FragmentManager fragmentManager,
                       MvpContractMain.Presenter presenter, String login) {
        super(fragmentManager);
        this.presenter = presenter;
        this.login = login;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailUserFragment detailUserFragment = DetailUserFragment.newInstance(login);
                presenter.attachViewDetailUser(detailUserFragment);
                return detailUserFragment;
            case 1:
                RepositoriesFragment repositoriesFragment = RepositoriesFragment.newInstance(login);
                presenter.attachViewRepositories(repositoriesFragment);
                return repositoriesFragment;
            default:
                return null;
        }
    }
}