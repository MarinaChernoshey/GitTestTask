package test.support.appodeal.com.gittesttask.view.main.details.repository;

import java.util.List;

import test.support.appodeal.com.gittesttask.base.BaseView;
import test.support.appodeal.com.gittesttask.base.BasePresenterMain;
import test.support.appodeal.com.gittesttask.base.BaseViewMain;
import test.support.appodeal.com.gittesttask.network.pojo.Repository;

public interface MvpContractRepositories {
    interface View extends BaseViewMain, BaseView<Presenter> {
        void showRepositories(List<Repository> repositories);

        void showMessageNoRepo();
    }

    interface Presenter extends BasePresenterMain {
        void startView(String username);

        void chooseRepositoryFromList(String url);
    }
}
