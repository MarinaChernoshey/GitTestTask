package test.support.appodeal.com.gittesttask.core;

import android.app.Application;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.support.appodeal.com.gittesttask.network.retrofit.GitApi;

public class App extends Application {

    private RxJava2CallAdapterFactory rxAdapter;
    private static GitApi gitApi;
    private Retrofit retrofit;
    private static String token;
    private static String loginUser;
    private static App app;

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

        app = this;

    }

    public static App getInstance() {
        return app;
    }

    public GitApi getApi() {
        return gitApi;
    }

    public String getAuthKey() {
        return token;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setToken(String authenticationHeader) {
        token = authenticationHeader;
    }

    public void setLoginUser(String login) {
        loginUser = login;
    }

    public void setConnectionListener(
            ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }



}
