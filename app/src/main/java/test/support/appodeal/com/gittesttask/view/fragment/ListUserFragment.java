package test.support.appodeal.com.gittesttask.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.activity.MainActivity;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.AdapterListUser;

public class ListUserFragment extends BaseMainFragment
        implements MvpContractMain.View.ViewUsers, SearchView.OnQueryTextListener {

    @BindView(R.id.recycle_view_users)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_users)
    ProgressBar progressBar;

    Context context;

    AdapterListUser adapterListUser;

    public ListUserFragment() {
    }

    public static ListUserFragment newInstance() {
        Bundle args = new Bundle();
        ListUserFragment fragment = new ListUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);

        adapterListUser = new AdapterListUser(getContext(),
                (view1, position) -> presenter.chooseUserFromList(
                        adapterListUser.getUsers().get(position).getLogin()),

                idTailUser -> presenter.startViewUsers(idTailUser)
        );
        adapterListUser.setItemClickListener(
                (view1, position) -> presenter.chooseUserFromList(
                        adapterListUser.getUsers().get(position).getLogin()));
        recyclerView.setAdapter(adapterListUser);
        Log.d(Const.TAG, "onCreateView: ");
        presenter.startViewUsers(0);
        return view;
    }


    @Override
    public void attachPresenter(MvpContractMain.Presenter presenter) {
        super.attachPresenter(presenter);
        Log.d(Const.TAG, "attachPresenter: ");
    }

    @Override
    public void showListUser(List<User> users) {
        Log.d(Const.TAG, "showListUser: ");
        progressBar.setVisibility(View.INVISIBLE);
        adapterListUser.updateData(users);
    }

    @Override
    public void showListSearchUser(List<User> users) {
        adapterListUser.updateSearchData(users);
    }

    @Override
    public void detachPresenter() {
        presenter.detachViewUsers();
        Log.d(Const.TAG, "detachPresenter: ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.search_users);
        SearchView searchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, searchView);
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        presenter.searchUser(s);
        return false;
    }
}
