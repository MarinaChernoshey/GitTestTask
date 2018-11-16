package test.support.appodeal.com.gittesttask.view.main.users.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import test.support.appodeal.com.gittesttask.util.CallbackSwipeUsers;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemUsers;

public class AdapterUsers extends BaseAdapter {
    private CallbackSwipeUsers callbackSwipeUsers;

    public AdapterUsers(Context context, CallbackClickItemUsers callbackClickItemUsers,
                        CallbackSwipeUsers callbackSwipeUsers) {
        super(context, callbackClickItemUsers);

        this.callbackSwipeUsers = callbackSwipeUsers;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemUser viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);

        if (users.size() - i <= Const.COUNT_USER_IN_LIST_FOR_START_REQUEST
                && isNeedDownloadNewPortionData) {
            isNeedDownloadNewPortionData = false;
            callbackSwipeUsers.startRequestGetNewUsers(users.get(users.size() - 1).getId());
        }
    }
}
