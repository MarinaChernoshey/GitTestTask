package test.support.appodeal.com.gittesttask.view.splash;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.view.login.MvpContractAuthentication;
import test.support.appodeal.com.gittesttask.data.ModelLogin;

public class SplashPresenter implements MvpContractSplash.Presenter {

    private MvpContractSplash.ViewSplash viewSplash;
    private MvpContractAuthentication.ModelLogin modelLogin;

    SplashPresenter(MvpContractSplash.ViewSplash viewSplash) {
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
                                saveAuthHeaderInAppClass(authenticationHeader);
                                saveLoginInAppClass(user.getLogin());
                                viewSplash.startMainView();
                            },
                            e -> viewSplash.startLoginView()
                    );
        } else {
            viewSplash.startLoginView();
        }

    }

    private void saveLoginInAppClass(String login) {
        App.getInstance().setLoginUser(login);
    }

    private void saveAuthHeaderInAppClass(String authenticationHeader) {
        App.getInstance().setToken(authenticationHeader);
    }
}
