package test.support.appodeal.com.gittesttask.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.util.Const;

public class DetailUserFragment extends BaseDetailFragment implements MvpContractMain.View.ViewDetailUser {

    @BindView(R.id.iv_details_user_avatar)
    ImageView avatar;
    @BindView(R.id.tv_detail_login)
    TextView textViewLogin;
    @BindView(R.id.tv_detail_email)
    TextView textViewEmail;
    @BindView(R.id.tv_detail_organization)
    TextView textViewOrganization;
    @BindView(R.id.tv_detail_following_count)
    TextView textViewFollowingCount;
    @BindView(R.id.tv_detail_followers_count)
    TextView textViewFollowersCount;
    @BindView(R.id.pb_detail)
    ProgressBar progressBar;

    public DetailUserFragment() {
    }

    public static DetailUserFragment newInstance(String login) {
        Bundle args = new Bundle();
        DetailUserFragment fragment = new DetailUserFragment();
        args.putString(Const.KEY_PARCELABLE_USER, login);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_user, container, false);
        ButterKnife.bind(this, view);

        presenter.startViewUser(login);
        return view;
    }

    @Override
    public void showDetailUser(User user) {
        Glide.with(this).load(user.getAvatarUrl()).into(avatar);
        progressBar.setVisibility(View.INVISIBLE);
        avatar.setVisibility(View.VISIBLE);
        textViewLogin.setText(user.getLogin());
        textViewOrganization.setText(user.getCompany());
        textViewEmail.setText(user.getAvatarUrl());
        textViewFollowersCount.setText(String.valueOf(user.getFollowersCount()));
        textViewFollowingCount.setText(String.valueOf(user.getFollowingCount()));
    }

    @Override
    public void detachPresenter() {
        presenter.detachViewDetailMain();
    }
}
