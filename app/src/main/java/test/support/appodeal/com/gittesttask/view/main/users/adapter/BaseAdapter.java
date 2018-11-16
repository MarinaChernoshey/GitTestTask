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
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.util.CallbackClickItemUsers;

public abstract class BaseAdapter extends RecyclerView.Adapter<ViewHolderItemUser> {

    public List<User> users = new ArrayList<>();
    private CallbackClickItemUsers callbackClickItemUsers;
    public Context context;
    boolean isNeedDownloadNewPortionData;

    BaseAdapter(Context context, CallbackClickItemUsers callbackClickItemUsers) {
        this.context = context;
        this.callbackClickItemUsers = callbackClickItemUsers;
    }

    public void addData(List<User> usersNew) {
        users.addAll(usersNew);
        isNeedDownloadNewPortionData = true;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderItemUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolderItemUser(view, callbackClickItemUsers);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItemUser viewHolder, int i) {

        viewHolder.getLogin().setText(users.get(i).getLogin());
        viewHolder.getId().setText(String.valueOf(users.get(i).getId()));

        Glide.with(context)
                .load(users.get(i).getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.avatar);
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setNeedDownloadNewPortionData(boolean needDownloadNewPortionData) {
        isNeedDownloadNewPortionData = needDownloadNewPortionData;
    }

    public void removeContext() {
        context = null;
    }
}
