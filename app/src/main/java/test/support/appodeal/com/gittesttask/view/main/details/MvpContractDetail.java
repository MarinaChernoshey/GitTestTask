package test.support.appodeal.com.gittesttask.details;

import test.support.appodeal.com.gittesttask.details.repository.MvpContractRepositories;
import test.support.appodeal.com.gittesttask.details.user.MvpContractUser;
import test.support.appodeal.com.gittesttask.view.BaseViewMain;

public class MvpContractDetail {
    interface View extends BaseViewMain<Presenter> {
        void showUser();

        void showRepositories();
    }

    interface Presenter {
        MvpContractUser.View navigationUser(String username);

        MvpContractRepositories.View navigationRepositories(String username);
    }
}
