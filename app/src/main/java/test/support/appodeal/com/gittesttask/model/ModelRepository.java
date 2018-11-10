package test.support.appodeal.com.gittesttask.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public class ModelRepository implements MvpContractMain.Model.ModelRepositories {
    @Override
    public Single<List<Repository>> getRepositories(String login) {
        return App.getApi().getRepositories(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
