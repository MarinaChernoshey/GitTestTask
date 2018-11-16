package test.support.appodeal.com.gittesttask.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.view.login.LoginActivity;
import test.support.appodeal.com.gittesttask.util.Const;
import test.support.appodeal.com.gittesttask.view.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements MvpContractSplash.ViewSplash {

    MvpContractSplash.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.startSplash(getAuthHeader());
    }

    @Override
    public void startLoginView() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void startMainView() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private String getAuthHeader() {
        return getSharedPreferences(Const.FILE_SHARED_PREFERENCES_SAVE_AUTHENTICATION, MODE_PRIVATE)
                .getString(Const.STATE_AUTHENTICATION_USER, null);
    }
}