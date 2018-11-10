package test.support.appodeal.com.gittesttask.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.support.appodeal.com.gittesttask.R;
import test.support.appodeal.com.gittesttask.core.MvpContractAuthentication;
import test.support.appodeal.com.gittesttask.presenter.PresenterLogin;
import test.support.appodeal.com.gittesttask.view.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity implements MvpContractAuthentication.ViewMainLogin {

    MvpContractAuthentication.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_login);

        presenter = new PresenterLogin(this);
        presenter.attachLoginFragment(loginFragment);
    }
}
