package test.support.appodeal.com.gittesttask.data;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.view.login.MvpContractAuthentication;

public class ModelLogin implements MvpContractAuthentication.ModelLogin {
    @Override
    public Single<User> getAuthenticationUser(String authenticationHeader) {
        return App.getInstance().getApi().getAuthenticationUser(authenticationHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}