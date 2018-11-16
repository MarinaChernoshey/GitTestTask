package test.support.appodeal.com.gittesttask.network.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("blog")
    private String blog;

    @SerializedName("location")
    private String location;

    @SerializedName("hireable")
    private boolean hireable;

    @SerializedName("bio")
    private String bio;

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

    public User(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public User(String name, String email, String blog, String company,
                String location, boolean hireable, String bio) {
        this.name = name;
        this.email = email;
        this.blog = blog;
        this.location = location;
        this.hireable = hireable;
        this.bio = bio;
        this.company = company;
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public boolean isHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }
}
