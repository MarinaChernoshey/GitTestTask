package test.support.appodeal.com.gittesttask.view.main.users.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.data.User;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemUsers;
import test.support.appodeal.com.gittesttask.util.CallbackSwipeUsers;
import test.support.appodeal.com.gittesttask.util.CallbackSwipeSearchUser;

public class AdapterUsersSearch extends BaseAdapter {

    private StartRequestGetNewUsersSearch callbackSwipeSearchUser;
    private long searchPage;
    private String searchText;

    public AdapterUsersSearch(Context context, ItemUserClickListener itemClickListener,
                              StartRequestGetNewUsersSearch callbackSwipeSearchUser) {
        super(context, itemClickListener);

        this.callbackSwipeSearchUser = callbackSwipeSearchUser;
    }

    public void setData(String searchText, List<User> usersNew) {
        this.searchText = searchText;
        searchPage = 1;
        users.addAll(usersNew);
    }

    @Override
    public void addData(List<User> usersNew) {
        super.addData(usersNew);
        searchPage += 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemUser viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);

        if (isNeedDownloadNewPortionData) {
            if (users.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST) {
                isNeedDownloadNewPortionData = false;
                callbackSwipeSearchUser.startRequestGetNewUsers(searchText, searchPage);
            }
        }
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             