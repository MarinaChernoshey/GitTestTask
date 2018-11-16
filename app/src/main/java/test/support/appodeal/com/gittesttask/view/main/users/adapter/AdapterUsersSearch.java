package test.support.appodeal.com.gittesttask.view.main.users.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import test.support.appodeal.com.gittesttask.util.CallbackSwipeSearchUser;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemUsers;

public class AdapterUsersSearch extends BaseAdapter {

    private CallbackSwipeSearchUser callbackSwipeSearchUser;

    public AdapterUsersSearch(Context context, CallbackClickItemUsers callbackClickItemUsers,
                              CallbackSwipeSearchUser callbackSwipeSearchUser) {
        super(context, callbackClickItemUsers);

        this.callbackSwipeSearchUser = callbackSwipeSearchUser;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemUser viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);

        if (users.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST
                && isNeedDownloadNewPortionData) {
            isNeedDownloadNewPortionData = false;
            callbackSwipeSearchUser.startRequestGetNewUsers();
        }
    }

    @Override
    public void setNeedDownloadNewPortionData(boolean needDownloadNewPortionData) {
        super.setNeedDownloadNewPortionData(needDownloadNewPortionData);
    }
}
