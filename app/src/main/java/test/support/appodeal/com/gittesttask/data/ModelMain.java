package test.support.appodeal.com.gittesttask.data;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.network.pojo.Issue;
import test.support.appodeal.com.gittesttask.network.pojo.Repository;
import test.support.appodeal.com.gittesttask.network.pojo.SearchUser;
import test.support.appodeal.com.gittesttask.network.pojo.User;
import test.support.appodeal.com.gittesttask.view.main.MvpContractMain;

public class ModelMain implements MvpContractMain.Model {
    @Override
    public Single<List<Issue>> requestGetIssues(String authKey) {
        return App.getInstance().getApi().getIssues(authKey, "all", "all")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<User>> requestGetUsers(long idTailUser) {
        return App.getInstance().getApi().getUsers(idTailUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<User> requestGetUser(String username) {
        return App.getInstance().getApi().getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Repository>> requestGetRepositories(String username) {
        return App.getInstance().getApi().getRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<User> editUser(User user) {
        return App.getInstance().getApi().editUser(App.getInstance().getAuthKey(), user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<SearchUser> requestSearchUsers(String text, long page) {
        return App.getInstance().getApi().getSearchUsers(text, "login", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
