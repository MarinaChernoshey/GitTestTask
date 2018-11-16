package test.support.appodeal.com.gittesttask.network.retrofit;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.support.appodeal.com.gittesttask.network.pojo.Issue;
import test.support.appodeal.com.gittesttask.network.pojo.Repository;
import test.support.appodeal.com.gittesttask.network.pojo.SearchUser;
import test.support.appodeal.com.gittesttask.network.pojo.User;

public interface GitApi {

    @GET("users/{username}")
    Single<User> getUser(@Path("username") String username);

    @GET("user")
    Single<User> getAuthenticationUser(@Header("Authorization") String authkey);

    @GET("users")
    Single<List<User>> getUsers(@Query("since") long idTailUser);

    @GET("users/{username}/repos")
    Single<List<Repository>> getRepositories(@Path("username") String username);

    @GET("search/users")
    Single<SearchUser> getSearchUsers(@Query("q") String q, @Query("in") String login,
                                          @Query("page") long page);

    @PATCH("user")
    Single<User> editUser(@Header("Authorization") String authkey, @Body User user);

    @GET("issues")
    Single<List<Issue>> getIssues(@Header("Authorization") String authKey,
                                  @Query("filter") String filter,
                                  @Query("state") String state);
}
