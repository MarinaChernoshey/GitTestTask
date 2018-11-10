package test.support.appodeal.com.gittesttask.model;

import com.google.gson.annotations.SerializedName;

public class Issue {
    @SerializedName("id")
    private
    int id;

    @SerializedName("project")
    String project;

    @Override
    public String toString() {
        return id + ", " + project;
    }
}
