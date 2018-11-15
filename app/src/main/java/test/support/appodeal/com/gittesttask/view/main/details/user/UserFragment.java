package test.support.appodeal.com.gittesttask.details.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import test.support.appodeal.com.gittesttask.view.fragment.BaseDetailFragment;

public class UserFragment extends Fragment implements MvpContractUser.View {

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

    private MvpContractUser.Presenter presenter;

    public static UserFragment newInstance(String login) {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        args.putString(Const.KEY_PARCELABLE_USER, login);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_user, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showUser(User user) {
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
    public void attachPresenter(MvpContractUser.Presenter presenter) {
        this.presenter = presenter;
    }
}
