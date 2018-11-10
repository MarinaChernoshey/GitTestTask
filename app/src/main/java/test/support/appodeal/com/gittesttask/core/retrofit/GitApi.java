package test.support.appodeal.com.gittesttask.core.retrofit;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.support.appodeal.com.gittesttask.model.Issue;
import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.model.User;

public interface GitApi {

    @GET("users/{username}")
    Single<User> getUser(@Path("username") String username);

    @GET("user")
    Single<User> getAuthenticationUser(@Header("Authorization") String authkey);

    @GET("{user}/issues")
    Single<List<Issue>> getIssuesUser(@Path("user") String token);

    @GET("users")
    Single<List<User>> getUsers(@Query("since") long idTailUser);

    @GET("users/{username}/repos")
    Single<List<Repository>> getRepositories(@Path("username") String username);

    @GET("search/users")
    Single<List<User>> getSearchUser(@Query("q") String q);
}
