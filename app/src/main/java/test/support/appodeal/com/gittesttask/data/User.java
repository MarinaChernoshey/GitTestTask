package test.support.appodeal.com.gittesttask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("company")
    private String company;

    @SerializedName("public_repos")
    private int countPublicRepositories;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("following")
    private long followingCount;

    @SerializedName("followers")
    private long followersCount;

    @SerializedName("token")
    private String token;

    public User(int id, String name) {
        this.id = id;
        this.login = name;
    }

    public User(int id, String name, String company, int countPublicRepositories, String avatarUrl) {
        this.id = id;
        this.login = name;
        this.company = company;
        this.countPublicRepositories = countPublicRepositories;
        this.avatarUrl = avatarUrl;
    }


    public User(long id, String login, String company, int countPublicRepositories,
                String avatarUrl, long followingCount, long followersCount, String token) {
        this.id = id;
        this.login = login;
        this.company = company;
        this.countPublicRepositories = countPublicRepositories;
        this.avatarUrl = avatarUrl;
        this.followingCount = followingCount;
        this.followersCount = followersCount;
        this.token = token;
    }

    @Override
    public String toString() {
        return id + ", " + login + ", " + company + ", " + countPublicRepositories
                + ", " + avatarUrl + ", " + token;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getCompany() {
        return company;
    }

    public int getCountPublicRepositories() {
        return countPublicRepositories;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public long getFollowersCount() {
        return followersCount;
    }
}
