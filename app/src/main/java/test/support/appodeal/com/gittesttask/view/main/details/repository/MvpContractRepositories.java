package test.support.appodeal.com.gittesttask.details.repository;

import java.util.List;

import test.support.appodeal.com.gittesttask.model.Repository;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public class MvpContractRepositories {
    public interface View extends BaseViewMain<Presenter> {
        void showRepositories(List<Repository> repositories);
    }

    public interface Presenter {
        void startView(String username);

        void chooseRepositoryFromList(String username);
    }
}
