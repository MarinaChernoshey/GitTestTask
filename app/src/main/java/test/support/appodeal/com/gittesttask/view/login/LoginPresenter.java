package test.support.appodeal.com.gittesttask.view.login;

import android.util.Base64;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.util.ThrowableUtil;
import test.support.appodeal.com.gittesttask.base.Presenter;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.data.ModelLogin;
import test.support.appodeal.com.gittesttask.util.Const;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenter extends Presenter implements MvpContractAuthentication.Presenter {

    private MvpContractAuthentication.View viewAuthentication;
    private MvpContractAuthentication.ModelLogin modelLogin;

    LoginPresenter(MvpContractAuthentication.View view) {
        this.viewAuthentication = view;
        modelLogin = new ModelLogin();
        viewAuthentication.attachPresenter(this);
    }

    @Override
    public void authenticationUser(String login, String password) {
        String authenticationHeader = "Basic " +
                Base64.encodeToString((login + ":" + password).getBytes(), Base64.NO_WRAP);
        disposable = modelLogin.getAuthenticationUser(authenticationHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            saveAuthHeaderInAppClass(authenticationHeader);
                            saveLoginInAppClass(user.getLogin());
                            viewAuthentication.saveAuthDataInSharedPreferences(
                                    Const.FILE_SHARED_PREFERENCES_SAVE_AUTHENTICATION,
                                    Const.STATE_AUTHENTICATION_USER,
                                    MODE_PRIVATE,
                                    authenticationHeader
                            );
                            viewAuthentication.StartMainView();
                        },
                        ThrowableUtil.getConsumerForShowThrowableScreen(viewAuthentication)

                );

    }

    private void saveLoginInAppClass(String login) {
        App.getInstance().setLoginUser(login);
    }

    private void saveAuthHeaderInAppClass(String authenticationHeader) {
        App.getInstance().setToken(authenticationHeader);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        viewAuthentication = null;
    }
}
