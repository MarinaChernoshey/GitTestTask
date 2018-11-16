package test.support.appodeal.com.gittesttask.view.main.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.login.LoginActivity;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_tab, container, false);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_user_logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.clickLogout();
        return super.onOptionsItemSelected(item);
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
    public void closeView() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void clearSharedPreference(String name, int mode) {
        SharedPreferences.Editor editor = Objects.requireNonNull(
                getActivity()).getSharedPreferences(name, mode).edit();
        editor.clear();
        editor.apply();
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
                    return presenter.navigationUser(username);
                case 1:
                    return presenter.navigationRepositories(username);
                default:
                    return null;
            }
        }
    }
}
