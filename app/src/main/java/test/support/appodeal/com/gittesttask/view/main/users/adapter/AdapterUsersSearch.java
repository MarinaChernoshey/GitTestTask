package test.support.appodeal.com.gittesttask.view.main.users.adapter;

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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.data.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.ItemUserClickListener;
import test.support.appodeal.com.gittesttask.util.StartRequestGetNewUsers;
import test.support.appodeal.com.gittesttask.util.StartRequestGetNewUsersSearch;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.ViewHolderItem> {

    private List<User> users = new ArrayList<>();
    private List<User> usersSearch = new ArrayList<>();
    private ItemUserClickListener itemClickListener;
    private StartRequestGetNewUsers startRequestGetNewUsers;
    StartRequestGetNewUsersSearch startRequestGetNewUsersSearch;
    private Context context;
    private boolean showSearchUsers;
    private boolean isNeedDownloadNewPortionData;
    private long pageSearchUsers;
    private String searchText;

    public AdapterUsers(Context context, ItemUserClickListener itemClickListener,
                        StartRequestGetNewUsers startRequestGetNewUsers,
                        StartRequestGetNewUsersSearch startRequestGetNewUsersSearch) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.startRequestGetNewUsers = startRequestGetNewUsers;
        this.startRequestGetNewUsersSearch = startRequestGetNewUsersSearch;
    }

    public void updateData(String searchText, List<User> usersNew) {
        usersSearch.clear();
        usersSearch.addAll(usersNew);
        showSearchUsers = true;
        this.searchText = searchText;
        isNeedDownloadNewPortionData = true;
        pageSearchUsers = 1;
        notifyDataSetChanged();
    }

    public void updateDataAdd(String searchText, List<User> usersNew) {
        usersSearch.addAll(usersNew);
        showSearchUsers = true;
        this.searchText = searchText;
        isNeedDownloadNewPortionData = true;
        pageSearchUsers += 1;
        notifyDataSetChanged();
    }


    public void addData(List<User> usersNew) {
        usersSearch.clear();
        users.addAll(usersNew);
        showSearchUsers = false;
        isNeedDownloadNewPortionData = true;
        notifyDataSetChanged();
    }

    public void showAllUsers() {
        usersSearch.clear();
        showSearchUsers = false;
        isNeedDownloadNewPortionData = true;
        notifyDataSetChanged();
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

        viewHolder.getLogin().setText(showSearchUsers ? usersSearch.get(i).getLogin() : users.get(i).getLogin());
        viewHolder.getId().setText(showSearchUsers ? String.valueOf(usersSearch.get(i).getId()) : String.valueOf(users.get(i).getId()));

        Glide.with(context)
                .load(showSearchUsers ? usersSearch.get(i).getAvatarUrl() : users.get(i).getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.avatar);

        if (isNeedDownloadNewPortionData) {
            if (showSearchUsers) {
                if (usersSearch.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST)
                    startRequestGetNewUsersSearch
                            .startRequestGetNewUsers(searchText, pageSearchUsers += 1);
            } else {
                if (users.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST) {
                    startRequestGetNewUsers.startRequestGetNewUsers(users.get(users.size() - 1).getId());
                }
            }
            isNeedDownloadNewPortionData = false;
        }


    }

    public List<User> getUsers() {
        if (showSearchUsers) {
            return usersSearch;
        }
        return users;
    }

    @Override
    public int getItemCount() {
        if (showSearchUsers) {
            return usersSearch.size();
        }
        return users.size();
    }


}
