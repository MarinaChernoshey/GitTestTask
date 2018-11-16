package test.support.appodeal.com.gittesttask.view.main.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.base.BaseFragment;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.view.main.users.adapter.AdapterUsers;
import test.support.appodeal.com.gittesttask.view.main.users.adapter.AdapterUsersSearch;

public class ListUserFragment extends BaseFragment implements MvpContractUsers.View {

    @BindView(R.id.recycle_view_users)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_users)
    ProgressBar progressBar;
    @BindView(R.id.pull_to_refresh_users)
    SwipeRefreshLayout swipeRefreshLayout;

    private AdapterUsers adapterUsers;
    private AdapterUsersSearch adapterUsersSearch;
    private MvpContractUsers.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(android.view.View.VISIBLE);


        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.requestGetUsers();
            swipeRefreshLayout.setRefreshing(false);
        });

        adapterUsers = new AdapterUsers(getContext(),
                position -> presenter.clickUser(adapterUsers.getUsers().get(position).getLogin()),
                idTailUser -> presenter.requestGetUsers()
        );
        recyclerView.setAdapter(adapterUsers);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestGetUsers();
    }

    @Override
    public void showUsers(List<User> users) {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        adapterUsers.addData(users);
    }


    @Override
    public void showSearchUsers(List<User> users) {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        adapterUsersSearch = new AdapterUsersSearch(getContext(),
                position -> presenter.clickUser(adapterUsersSearch.getUsers().get(position).getLogin()),
                () -> presenter.requestGetNextPageSearchUser());
        adapterUsersSearch.addData(users);
        recyclerView.setAdapter(adapterUsersSearch);
    }

    @Override
    public void showSearchUsersScroll(List<User> users) {
        progressBar.setVisibility(android.view.View.INVISIBLE);
        adapterUsersSearch.addData(users);
    }

    @Override
    public void showAllUsersAfterCloseSearch() {
        if (adapterUsersSearch != null) {
            adapterUsersSearch.removeContext();
            adapterUsersSearch = null;
        }
        recyclerView.setAdapter(adapterUsers);
    }

    @Override
    public void resetSettingsAdapterSearch() {
        adapterUsersSearch.setNeedDownloadNewPortionData(true);
    }

    public void attachPresenter(MvpContractUsers.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem item = menu.findItem(R.id.search_users);
        SearchView searchView = (SearchView) item.getActionView();
        presenter.setListenerSearchViewEditChanges(RxSearchView.queryTextChanges(searchView));
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapterUsersSearch != null) {
            adapterUsersSearch.removeContext();
        }
        adapterUsers.removeContext();
        presenter.destroyView();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
