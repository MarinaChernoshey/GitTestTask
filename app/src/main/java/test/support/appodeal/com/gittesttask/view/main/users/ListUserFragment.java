package test.support.appodeal.com.gittesttask.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.fragment.adapter.AdapterListUser;

public class ListUserFragment extends Fragment implements MvpContractUsers.View {

    @BindView(R.id.recycle_view_users)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_users)
    ProgressBar progressBar;

    private AdapterListUser adapterListUser;
    private MvpContractUsers.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        ButterKnife.bind(this, view);
        progressBar.setVisibility(View.VISIBLE);

        adapterListUser = new AdapterListUser(getContext(),
                (view1, position) -> {},
                idTailUser -> {}
        );
        adapterListUser.setItemClickListener(
                (view1, position) -> {});
        recyclerView.setAdapter(adapterListUser);

        presenter.requestGetUsers();
        return view;
    }

    @Override
    public void showUsers(List<User> users) {
        progressBar.setVisibility(View.INVISIBLE);
        adapterListUser.updateData(users);
    }

    @Override
    public void attachPresenter(MvpContractUsers.Presenter presenter) {
        this.presenter = presenter;
    }
}
