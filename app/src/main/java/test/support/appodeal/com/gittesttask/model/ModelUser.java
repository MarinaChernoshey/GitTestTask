package test.support.appodeal.com.gittesttask.model;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public class ModelUser implements MvpContractMain.Model.ModelDetailUser {
    @Override
    public Single<User> getUser(String login) {
        return App.getApi().getUser(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
