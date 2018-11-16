package test.support.appodeal.com.gittesttask.network.pojo;

import com.google.gson.annotations.SerializedName;

public class Issue {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("state")
    private String state;

    @SerializedName("has_project")
    boolean project;

    @SerializedName("created_at")
    String date;

    @SerializedName("comments")
    long comments;

    public Issue(long id, String title, String state, boolean project, String date, long comments) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.project = project;
        this.date = date;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public boolean isProject() {
        return project;
    }

    public String getDate() {
        return date;
    }

    public long getComments() {
        return comments;
    }
}
