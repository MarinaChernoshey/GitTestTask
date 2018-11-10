package test.support.appodeal.com.gittesttask.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractAuthentication;
import test.support.appodeal.com.gittesttask.core.MvpContractSplash;
import test.support.appodeal.com.gittesttask.model.ModelLogin;

public class PresenterSplash implements MvpContractSplash.Presenter {

    private MvpContractSplash.ViewSplash viewSplash;
    private MvpContractAuthentication.ModelLogin modelLogin;

    public PresenterSplash(MvpContractSplash.ViewSplash viewSplash) {
        this.viewSplash = viewSplash;
        modelLogin = new ModelLogin();
    }

    @Override
    public void startSplash(String authenticationHeader) {
        if (authenticationHeader != null) {
            Disposable disposable = modelLogin.getAuthenticationUser(authenticationHeader)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            user -> {
                                saveTokenInAppClass(authenticationHeader);
                                saveLoginInAppClass(user.getLogin());
                                viewSplash.startMainView();
                            },
                            throwable -> viewSplash.startLoginView()
                    );
        } else {
            viewSplash.startLoginView();
        }

    }

    private void saveLoginInAppClass(String login) {
        App.setLoginUser(login);
    }

    private void saveTokenInAppClass(String authenticationHeader) {
        App.setToken(authenticationHeader);
    }
}
