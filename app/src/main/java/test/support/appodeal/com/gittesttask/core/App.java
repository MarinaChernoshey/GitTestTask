package test.support.appodeal.com.gittesttask.core;

import android.app.Application;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.support.appodeal.com.gittesttask.core.retrofit.GitApi;
import test.support.appodeal.com.gittesttask.model.User;

public class App extends Application {

    private RxJava2CallAdapterFactory rxAdapter;
    private static GitApi gitApi;
    private Retrofit retrofit;
    private static String token;
    private static String loginUser;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        gitApi = retrofit.create(GitApi.class);
    }

    public static GitApi getApi() {
        return gitApi;
    }

    public static String getToken() {
        return token;
    }

    public static String getLoginUser() {
        return loginUser;
    }

    public static void setToken(String authenticationHeader) {
        token = authenticationHeader;
    }

    public static void setLoginUser(String login) {
        loginUser = login;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        App.user = user;
    }
}
