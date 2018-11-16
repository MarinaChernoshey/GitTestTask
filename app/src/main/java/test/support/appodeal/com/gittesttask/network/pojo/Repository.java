package test.support.appodeal.com.gittesttask.network.pojo;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("forks_count")
    private long forksCount;

    @SerializedName("watchers_count")
    private long watchersCount;

    @SerializedName("stargazers_count")
    private long stargazersCount;

    @SerializedName("type")
    private String type;

    public Repository(long id, String name, String htmlUrl) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
    }

    public Repository(long id, String name, String htmlUrl, long forksCount, long watchersCount,
                      long stargazersCount, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.htmlUrl = htmlUrl;
        this.forksCount = forksCount;
        this.watchersCount = watchersCount;
        this.stargazersCount = stargazersCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public long getForksCount() {
        return forksCount;
    }

    public long getWatchersCount() {
        return watchersCount;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public String getType() {
        return type;
    }
}
