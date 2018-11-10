package test.support.appodeal.com.gittesttask.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.TabsAdapter;

public class MainDetailFragment extends BaseDetailFragment implements MvpContractMain.View.ViewDetailMain {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public MainDetailFragment() {
    }

    public static MainDetailFragment newInstance(final String login) {
        final MainDetailFragment fragment = new MainDetailFragment();
        final Bundle args = new Bundle();
        args.putString(Const.KEY_PARCELABLE_USER, login);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, view);

        TabsAdapter tabsAdapter = new TabsAdapter(getFragmentManager(), presenter, login);
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
    public void attachPresenter(MvpContractMain.Presenter presenter) {
        super.attachPresenter(presenter);
    }

    @Override
    public void detachPresenter() {
        presenter.detachViewDetailMain();
    }
}
