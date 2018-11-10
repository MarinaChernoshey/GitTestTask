package test.support.appodeal.com.gittesttask.presenter;

import android.content.SharedPreferences;
import android.util.Base64;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractAuthentication;
import test.support.appodeal.com.gittesttask.util.Const;

public class PresenterLogin implements MvpContractAuthentication.Presenter {

    private MvpContractAuthentication.ViewMainLogin viewMainLogin;
    private MvpContractAuthentication.ViewLogin viewAuthentication;
    private MvpContractAuthentication.ModelLogin modelLogin;

    public PresenterLogin(MvpContractAuthentication.ViewMainLogin viewMainLogin ) {
        this.viewMainLogin = viewMainLogin;
        modelLogin = new test.support.appodeal.com.gittesttask.model.ModelLogin();
    }

    @Override
    public void authenticationUser(String login, String password) {
        String authenticationHeader = "Basic " +
                Base64.encodeToString((login + ":" + password).getBytes(),
                        Base64.NO_WRAP);
        Disposable disposable = modelLogin.getAuthenticationUser(authenticationHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            saveTokenInAppClass(authenticationHeader);
                            saveLoginInAppClass(user.getLogin());
                            saveTokenInSharedPreferences(authenticationHeader);
                            viewAuthentication.StartMainView();
                        },
                        throwable -> viewAuthentication.showErrorAuthenticationLogin()
                );

    }

    @Override
    public void attachLoginFragment(MvpContractAuthentication.ViewLogin viewLogin) {
        this.viewAuthentication = viewLogin;
        viewAuthentication.attachPresenter(this);
    }

    @Override
    public void detachLoginFragment() {
        viewAuthentication = null;
    }

    private void saveLoginInAppClass(String login) {
        App.setLoginUser(login);
    }

    private void saveTokenInAppClass(String authenticationHeader) {
        App.setToken(authenticationHeader);
    }

    private void saveTokenInSharedPreferences(String authenticationHeader) {
        SharedPreferences.Editor editor = viewAuthentication.getSharedPreferences().edit();
        editor.putString(Const.STATE_AUTHENTICATION_USER, authenticationHeader);
        editor.commit();
    }
}
