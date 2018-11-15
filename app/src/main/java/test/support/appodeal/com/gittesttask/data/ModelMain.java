package test.support.appodeal.com.gittesttask.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;

public class ModelMain implements Model {
    @Override
    public Single<List<Issue>> requestGetIssues() {
        return null;
    }

    @Override
    public Single<List<User>> requestGetUsers() {
        return App.getApi().getUsers(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<User> requestGetUserDetail(String username) {
        return null;
    }

    @Override
    public Single<List<Repository>> requestGetRepositories(String username) {
        return null;
    }
}
