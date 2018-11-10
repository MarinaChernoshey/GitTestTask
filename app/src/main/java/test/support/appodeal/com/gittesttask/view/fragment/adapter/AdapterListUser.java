package test.support.appodeal.com.gittesttask.view.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.model.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.ItemClickListener;

public class AdapterListUser extends RecyclerView.Adapter<AdapterListUser.ViewHolderItem> {

    private List<User> users = new ArrayList<>();
    private List<User> usersSearch = new ArrayList<>();
    private ItemClickListener itemClickListener;
    private StartRequestGetNewUsers startRequestGetNewUsers;
    private Context context;

    public AdapterListUser(Context context, ItemClickListener itemClickListener,
                           StartRequestGetNewUsers startRequestGetNewUsers) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.startRequestGetNewUsers = startRequestGetNewUsers;
    }

    public void updateData(List<User> usersNew) {
        users.addAll(usersNew);
        notifyDataSetChanged();
    }

    public void updateSearchData(List<User> usersNew) {
        usersSearch = users;
        users.clear();
        users.addAll(usersNew);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem viewHolder, int i) {
        viewHolder.getLogin().setText(users.get(i).getLogin());
        viewHolder.getId().setText(String.valueOf(users.get(i).getId()));

        Glide.with(context)
                .load(users.get(i).getAvatarUrl())
                .into(viewHolder.avatar);

        if (users.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST) {
            startRequestGetNewUsers.startRequestGetNewUsers(users.get(users.size() - 1).getId());
        }

    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.constraint_layout_item)
        ConstraintLayout constraintLayout;
        @BindView(R.id.iv_avatar)
        ImageView avatar;
        @BindView(R.id.tv_id_user)
        TextView id;
        @BindView(R.id.tv_login_user)
        TextView login;

        ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> itemClickListener.onClick(v, getAdapterPosition()));
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
}
