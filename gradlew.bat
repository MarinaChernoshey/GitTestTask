package test.support.appodeal.com.gittesttask.view.main.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.main.users.adapter.AdapterUsers;
import test.support.appodeal.com.gittesttask.view.main.users.adapter.AdapterUsersSearch;

public class ListUserFragment extends Fragment implements MvpContractUsers.View {

    @BindView(R.id.recycle_view_users)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_users)
    ProgressBar progressBar;

    private AdapterUsers adapterUsers;
    private AdapterUsersSearch adapterUsersSearch;
    private MvpContractUsers.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);

        adapterUsers = new AdapterUsers(getContext(),
                position -> presenter.clickUser(adapterUsers.getUsers().get(position).getLogin()),
                idTailUser -> presenter.requestGetUsers(idTailUser)
        );
        recyclerView.setAdapter(adapterUsers);
        return view;
    }

    @Override
    public void onResume() {
       