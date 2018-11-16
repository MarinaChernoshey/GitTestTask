package test.support.appodeal.com.gittesttask.view.main.details.user;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.base.BaseFragment;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.util.Const;

public class UserFragment extends BaseFragment implements MvpContractUser.View {

    @BindView(R.id.iv_details_user_avatar)
    ImageView avatar;
    @BindView(R.id.tv_detail_login)
    TextView textViewUsername;
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
    @BindView(R.id.action_button_edit)
    FloatingActionButton buttonEdit;
    @BindView(R.id.ll_user_email)
    LinearLayout linearLayoutEmail;
    @BindView(R.id.ll_user_following)
    LinearLayout linearLayoutFollowing;
    @BindView(R.id.ll_user_followers)
    LinearLayout linearLayoutFollowers;
    @BindView(R.id.ll_user_organization)
    LinearLayout linearLayoutOrganization;

    private MvpContractUser.Presenter presenter;
    private String username;

    public static UserFragment newInstance(String username) {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        args.putString(Const.KEY_PARCELABLE_USER, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_detail_user, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Const.KEY_PARCELABLE_USER)) {
            username = getArguments().getString(Const.KEY_PARCELABLE_USER);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startView(username);
    }

    @OnClick(R.id.action_button_edit)
    public void onClickEdit() {
        presenter.clickEditUser(username);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void showUser(User user, boolean isCanEdit,
                         boolean isShowEmail, boolean isShowCompany) {

        progressBar.setVisibility(android.view.View.INVISIBLE);
        avatar.setVisibility(android.view.View.VISIBLE);
        linearLayoutFollowers.setVisibility(android.view.View.VISIBLE);
        linearLayoutFollowing.setVisibility(android.view.View.VISIBLE);
        if (isShowCompany) {
            linearLayoutOrganization.setVisibility(android.view.View.VISIBLE);
        }
        if (isShowEmail) {
            linearLayoutEmail.setVisibility(android.view.View.VISIBLE);
        }
        Glide.with(this)
                .load(user.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);
        avatar.setVisibility(android.view.View.VISIBLE);
        textViewUsername.setText(user.getLogin());
        textViewOrganization.setText(user.getCompany());

        textViewEmail.setText(user.getEmail());
        textViewFollowersCount.setText(String.valueOf(user.getFollowersCount()));
        textViewFollowingCount.setText(String.valueOf(user.getFollowingCount()));
        if (isCanEdit) {
            buttonEdit.setVisibility(android.view.View.VISIBLE);
        }
    }

    @Override
    public void attachPresenter(MvpContractUser.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(android.view.View.INVISIBLE);
    }
}
