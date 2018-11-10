package test.support.appodeal.com.gittesttask.model;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.support.appodeal.com.gittesttask.core.App;
import test.support.appodeal.com.gittesttask.core.MvpContractMain;

public class ModelIssue implements MvpContractMain.Model.ModelIssues {

    @Override
    public Single<List<Issue>> getIssues() {
        return App.getApi().getIssuesUser(App.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
