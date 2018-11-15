package test.support.appodeal.com.gittesttask.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractSplash;
import test.support.appodeal.com.gittesttask.presenter.PresenterSplash;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements MvpContractSplash.ViewSplash {

    MvpContractSplash.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new PresenterSplash(this);
        presenter.startSplash(getToken());
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return getPreferences(MODE_PRIVATE);
    }

    @Override
    public void startLoginView() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void startMainView() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private String getToken() {
        return getPreferences(MODE_PRIVATE)
                .getString(Const.STATE_AUTHENTICATION_USER, null);
    }
}