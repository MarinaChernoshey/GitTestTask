package test.support.appodeal.com.gittesttask.model;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public class ModelUsers implements MvpContractMain.Model.ModelUsers {

    @Override
    public Single<List<User>> getUsers(long idTailUser) {
        return App.getApi().getUsers(idTailUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<User>> getSearchUser(String text) {
        return App.getApi().getSearchUser(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
