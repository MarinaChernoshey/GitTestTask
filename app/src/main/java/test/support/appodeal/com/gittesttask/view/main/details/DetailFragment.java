package test.support.appodeal.com.gittesttask.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.details.repository.RepositoriesFragment;
import test.support.appodeal.com.gittesttask.details.user.UserFragment;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.fragment.BaseDetailFragment;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.TabsAdapter;

public class DetailFragment extends Fragment implements MvpContractDetail.View {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private static String username;
    private static MvpContractDetail.Presenter presenter;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(final String username) {
        final DetailFragment fragment = new DetailFragment();
        final Bundle args = new Bundle();
        args.putString(Const.KEY_PARCELABLE_USER, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            username = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }

        TabsAdapter tabsAdapter = new TabsAdapter(getFragmentManager());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        setSettingForTabLayout();

        return view;
    }

    private void setSettingForTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_name_details));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_name_repositories));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void showUser() {

    }

    @Override
    public void showRepositories() {

    }

    @Override
    public void attachPresenter(MvpContractDetail.Presenter presenter) {
        DetailFragment.presenter = presenter;
    }


    public static class TabsAdapter extends FragmentStatePagerAdapter {

        TabsAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return (Fragment) presenter.navigationUser(username);
                case 1:
                    return (Fragment) presenter.navigationRepositories(username);
                default:
                    return null;
            }
        }
    }
}
