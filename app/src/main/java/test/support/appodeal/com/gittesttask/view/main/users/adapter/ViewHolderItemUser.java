package test.support.appodeal.com.gittesttask.view.main.users.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemUsers;

public class ViewHolderItemUser extends RecyclerView.ViewHolder {

    @BindView(R.id.constraint_layout_item)
    ConstraintLayout constraintLayout;
    @BindView(R.id.iv_avatar)
    ImageView avatar;
    @BindView(R.id.tv_id_user)
    TextView id;
    @BindView(R.id.tv_login_user)
    TextView login;

    ViewHolderItemUser(@NonNull View itemView, CallbackClickItemUsers callbackClickItemUsers) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> callbackClickItemUsers.onClick(getAdapterPosition()));
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public TextView getLogin() {
        return login;
    }

    public TextView getId() {
        return id;
    }

    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }
}
