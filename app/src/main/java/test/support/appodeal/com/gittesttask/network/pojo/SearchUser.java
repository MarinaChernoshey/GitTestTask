package test.support.appodeal.com.gittesttask.network.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import test.support.appodeal.com.gittesttask.network.pojo.User;

public class SearchUser {

    @SerializedName("total_count")
    private long totalCount;

    @SerializedName("items")
    private List<User> users;

    public SearchUser(long totalCount, List<User> users) {
        this.totalCount = totalCount;
        this.users = users;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<User> getUsers() {
        return users;
    }
}
